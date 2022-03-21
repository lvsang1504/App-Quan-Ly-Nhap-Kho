package com.nhom2.qly_nhap_kho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.nhom2.qly_nhap_kho.adapter.TableViewAdapterBaoCao1;
import com.nhom2.qly_nhap_kho.dao.TableData;
import com.nhom2.qly_nhap_kho.dao.TableDataBaoCao1;
import com.nhom2.qly_nhap_kho.model.ChiTietPhieuNhap;
import com.nhom2.qly_nhap_kho.model.VatTu;

import java.util.ArrayList;
import java.util.List;

public class ChiTietBaoCao1Activity extends AppCompatActivity {
    NhapKhoHelper nhapKhoHelper;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_bao_cao1);

        nhapKhoHelper=new NhapKhoHelper(this);
        TableViewAdapterBaoCao1 tableViewAdapterBaoCao1=new TableViewAdapterBaoCao1(getList());
        recyclerView = findViewById(R.id.recyclerViewBaoCao1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(tableViewAdapterBaoCao1);
    }

    private List<TableDataBaoCao1> getList() {
        List<TableDataBaoCao1> list = new ArrayList<>();
        Cursor dataBaoCao1 = nhapKhoHelper.GetData("SELECT  TAM.MaKho,TAM.TenKho,MAX(SoLuong)\n" +
                                                        "FROM(SELECT Kho.MaKho,Kho.TenKho,count(ChiTietPhieuNhap.MaVT) as SoLuong\n" +
                                                        "FROM Kho,PhieuNhap,ChiTietPhieuNhap\n" +
                                                        "WHERE Kho.MaKho=PhieuNhap.Makho AND\n" +
                                                        "PhieuNhap.SoPhieu=ChiTietPhieuNhap.SoPhieu\n" +
                                                        "GROUP BY Kho.MaKho) AS TAM");

        TableDataBaoCao1 tableDataBaoCao1;
        while (dataBaoCao1.moveToNext()) {
            tableDataBaoCao1 = new TableDataBaoCao1(
                    dataBaoCao1.getString(0),
                    dataBaoCao1.getString(1),
                    Integer.valueOf(dataBaoCao1.getString(2)));
            list.add(tableDataBaoCao1);
        }
        return list;
    }

    public void btnClick(View view) {
        onBackPressed();
    }
}