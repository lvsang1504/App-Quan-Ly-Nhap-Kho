package com.nhom2.qly_nhap_kho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.nhom2.qly_nhap_kho.adapter.TableViewAdapterBaoCao1;
import com.nhom2.qly_nhap_kho.dao.TableDataBaoCao1;

import java.util.ArrayList;
import java.util.List;

public class ChiTietBaoCao1Activity extends AppCompatActivity {
    NhapKhoHelper nhapKhoHelper;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_bao_cao1);

        nhapKhoHelper = new NhapKhoHelper(this);
        TableViewAdapterBaoCao1 tableViewAdapterBaoCao1 = new TableViewAdapterBaoCao1(getList());
        setControl();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(tableViewAdapterBaoCao1);
    }

    private void setControl() {
        recyclerView = findViewById(R.id.recyclerViewBaoCao1);
    }

    private List<TableDataBaoCao1> getList() {
        List<TableDataBaoCao1> list = new ArrayList<>();

        String query = "SELECT final.MaKho, final.TenKho, MAX(final.TongSoVatTu)\n" +
                "FROM (\n" +
                "\tSELECT k.MaKho, k.TenKho, SUM(ctpn.SoVatTu) as TongSoVatTu FROM Kho k\n" +
                "\tJOIN (SELECT MaKho, SoPhieu FROM PhieuNhap) pn \n" +
                "\t\tON pn.MaKho = k.MaKho \n" +
                "\tJOIN (SELECT SoPhieu, COUNT(SoPhieu) as SoVatTu FROM ChiTietPhieuNhap GROUP BY SoPhieu) ctpn \n" +
                "\t\tON ctpn.SoPhieu = pn.SoPhieu GROUP BY k.MaKho, k.TenKho\n" +
                ")as final";

        Cursor cursor = nhapKhoHelper.GetData(query);

        while (cursor.moveToNext()) {
            list.add(new TableDataBaoCao1(
                    cursor.getString(0),
                    cursor.getString(1),
                    Integer.valueOf(cursor.getString(2))));
        }
        return list;
    }

    public void btnClick(View view) {
        onBackPressed();
    }
}