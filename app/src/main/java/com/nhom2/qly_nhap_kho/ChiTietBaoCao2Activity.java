package com.nhom2.qly_nhap_kho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.nhom2.qly_nhap_kho.adapter.TableViewAdapterBaoCao1;
import com.nhom2.qly_nhap_kho.adapter.TableViewAdapterBaoCao2;
import com.nhom2.qly_nhap_kho.dao.TableDataBaoCao1;
import com.nhom2.qly_nhap_kho.dao.TableDataBaoCao2;

import java.util.ArrayList;
import java.util.List;

public class ChiTietBaoCao2Activity extends AppCompatActivity {
    NhapKhoHelper nhapKhoHelper;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_bao_cao2);

        setControl();
        nhapKhoHelper = new NhapKhoHelper(this);
        TableViewAdapterBaoCao2 tableViewAdapterBaoCao2 = new TableViewAdapterBaoCao2(getList());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(tableViewAdapterBaoCao2);
    }



    private void setControl() {
        recyclerView = findViewById(R.id.recyclerViewBaoCao2);
    }

    private List<TableDataBaoCao2> getList() {
        List<TableDataBaoCao2> list = new ArrayList<>();

        String query = "select k.MaKho,k.TenKho  \n" +
                "\tFROM Kho k\n" +
                "\t\tWHERE k.MaKho NOT IN \n" +
                "\t\t\t(SELECT k.MaKho FROM Kho k LEFT JOIN PhieuNhap pn ON pn.MaKho = k.MaKho " +
                "WHERE replace(NgayLap, rtrim(NgayLap, replace(NgayLap, '/', '')), '') = '2014' " +
                "GROUP BY k.MaKho)";

        Cursor cursor = nhapKhoHelper.GetData(query);

        while (cursor.moveToNext()) {
            list.add(new TableDataBaoCao2(
                    cursor.getString(0),
                    cursor.getString(1)));
        }
        return list;
    }

    public void btnClick(View view) {
        onBackPressed();
    }
}