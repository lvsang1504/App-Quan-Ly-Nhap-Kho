package com.nhom2.qly_nhap_kho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.nhom2.qly_nhap_kho.adapter.TableViewAdapterBaoCao1;
import com.nhom2.qly_nhap_kho.adapter.TableViewAdapterBaoCao3;
import com.nhom2.qly_nhap_kho.dao.TableDataBaoCao1;
import com.nhom2.qly_nhap_kho.dao.TableDataBaoCao3;

import java.util.ArrayList;
import java.util.List;

public class ChiTietBaoCao3Activity extends AppCompatActivity {
    NhapKhoHelper nhapKhoHelper;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_bao_cao3);

        nhapKhoHelper=new NhapKhoHelper(this);
        TableViewAdapterBaoCao3 tableViewAdapterBaoCao3=new TableViewAdapterBaoCao3(getList());
        recyclerView = findViewById(R.id.recyclerViewBaoCao3);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(tableViewAdapterBaoCao3);
    }
    private List<TableDataBaoCao3> getList() {
        List<TableDataBaoCao3> list = new ArrayList<>();
        Cursor dataBaoCao3 = nhapKhoHelper.GetData("SELECT distinct pn.SoPhieu,pn.NgayLap,k.TenKho\n" +
                "                    FROM PhieuNhap pn, ChiTietPhieuNhap ctpn,Kho k\n" +
                "                    WHERE k.MaKho=pn.MaKho AND pn.SoPhieu=ctpn.SoPhieu AND ctpn.MaVT ='GO'\n" +
                "\n" +
                "                    INTERSECT\n" +
                "\n" +
                "                    SELECT distinct pn.SoPhieu,pn.NgayLap,k.TenKho\n" +
                "                    FROM PhieuNhap pn, ChiTietPhieuNhap ctpn,Kho k\n" +
                "                    WHERE k.MaKho=pn.MaKho AND pn.SoPhieu=ctpn.SoPhieu AND ctpn.MaVT ='GT'");

        TableDataBaoCao3 tableDataBaoCao3;
        while (dataBaoCao3.moveToNext()) {
            tableDataBaoCao3 = new TableDataBaoCao3(
                    Integer.valueOf(dataBaoCao3.getString(0)),
                    dataBaoCao3.getString(1),
                    dataBaoCao3.getString(2));
            list.add(tableDataBaoCao3);
        }
        return list;
    }

    public void btnClick(View view) {
        onBackPressed();
    }
}