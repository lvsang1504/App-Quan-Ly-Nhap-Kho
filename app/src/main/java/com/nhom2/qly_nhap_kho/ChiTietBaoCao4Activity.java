package com.nhom2.qly_nhap_kho;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom2.qly_nhap_kho.adapter.TableViewAdapterBaoCao4;
import com.nhom2.qly_nhap_kho.dao.TableDataBaoCao4;
import com.nhom2.qly_nhap_kho.model.Kho;

import java.util.ArrayList;
import java.util.List;

public class ChiTietBaoCao4Activity extends AppCompatActivity {
    NhapKhoHelper nhapKhoHelper;
    RecyclerView recyclerView;
    LinearLayout linearLayout;
    List<Kho> khos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_bao_cao4);

        setControl();
        nhapKhoHelper = new NhapKhoHelper(this);
        setDataForKhos();
        TableViewAdapterBaoCao4 tableViewAdapterBaoCao4 = new TableViewAdapterBaoCao4(getList(), linearLayout, khos);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(tableViewAdapterBaoCao4);
    }

    private void setControl() {
        recyclerView = findViewById(R.id.recyclerViewBaoCao4);
        linearLayout = findViewById(R.id.list_item_linear_layout);
    }

    private void setDataForKhos() {
        String queryNumberOfKho = "SELECT * FROM Kho";
        Cursor crs = nhapKhoHelper.GetData(queryNumberOfKho);

        while (crs.moveToNext()) {
            khos.add(new Kho(
                    crs.getString(0),
                    crs.getString(1)
            ));
        }
    }

    private List<TableDataBaoCao4> getList() {
        ArrayList<String> crossTabKho = new ArrayList<>();
        String query = "SELECT vt.MaVT, vt.TenVT, ctpn.DVT, @#$ FROM VatTu vt LEFT JOIN ChiTietPhieuNhap ctpn ON vt.MaVT = ctpn.MaVT LEFT JOIN PhieuNhap pn ON pn.SoPhieu = ctpn.SoPhieu LEFT JOIN Kho k ON k.MaKho = pn.MaKho GROUP BY vt.MaVT";

        for (int i = 0; i < khos.size(); i++) {
            crossTabKho.add("SUM(case when k.MaKho = " + "'" + khos.get(i).getMaKho() + "'" + " then ctpn.SoLuong end) Kho" + (i + 1));
        }
        String dynamicQUery = crossTabKho.toString();
        dynamicQUery = dynamicQUery.replace("[", "").replace("]", "").replace(" ", " ");

        query = query.replace("@#$", dynamicQUery);
        List<TableDataBaoCao4> list = new ArrayList<>();

        Cursor cursor = nhapKhoHelper.GetData(query);
        int count = cursor.getCount();
        System.out.println(count);
        while (cursor.moveToNext()) {
            TableDataBaoCao4 tableDataBaoCao4 = new TableDataBaoCao4();
            tableDataBaoCao4.setMaVT(cursor.getString(0));
            tableDataBaoCao4.setTenVT(cursor.getString(1));
            tableDataBaoCao4.setDVT(cursor.getString(2));

            for (int i = 0; i < khos.size(); i++) {
                tableDataBaoCao4.addKhoData(cursor.getInt(3 + i));
            }

            list.add(tableDataBaoCao4);
        }
        return list;
    }

    public void btnClick(View view) {
        onBackPressed();
    }
}