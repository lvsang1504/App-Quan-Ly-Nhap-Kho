package com.nhom2.qly_nhap_kho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.nhom2.qly_nhap_kho.adapter.TableViewAdapter;
import com.nhom2.qly_nhap_kho.dao.TableData;
import com.nhom2.qly_nhap_kho.model.ChiTietPhieuNhap;
import com.nhom2.qly_nhap_kho.model.VatTu;

import java.util.ArrayList;
import java.util.List;

public class ChiTietPhieuNhapActivity extends AppCompatActivity {

    NhapKhoHelper nhapKhoHelper;
    TextView txtTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_phieu_nhap);

        String id = getIntent().getStringExtra("id");

        txtTitle = findViewById(R.id.txtTitle);
        txtTitle.setText("Thông tin vật tư nhập kho của phiếu số " + id);

        nhapKhoHelper = new NhapKhoHelper(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewDeliveryProductList);

        TableViewAdapter adapter = new TableViewAdapter(getList(id));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapter);

    }


    private List getList(String id) {
        List<TableData> list = new ArrayList<>();

        Cursor dataCTPN = nhapKhoHelper.GetData("SELECT * FROM ChiTietPhieuNhap WHERE SoPhieu = " + id);

        ChiTietPhieuNhap chiTietPhieuNhap;
        while (dataCTPN.moveToNext()) {
            chiTietPhieuNhap = new ChiTietPhieuNhap(
                    Integer.valueOf(dataCTPN.getString(0)),
                    dataCTPN.getString(1),
                    dataCTPN.getString(2),
                    Integer.valueOf(dataCTPN.getString(3)));
            Log.d("aaa",chiTietPhieuNhap.MaVT);
            if (chiTietPhieuNhap != null) {
                Cursor dataVT = nhapKhoHelper.GetData("SELECT * FROM VatTu WHERE MaVT = '" + chiTietPhieuNhap.MaVT + "'");
                VatTu vatTu;
                if (dataVT.moveToNext()) {
                    vatTu = new VatTu(dataVT.getString(0), dataVT.getString(1), dataVT.getString(2));
                    TableData tableData = new TableData(
                            vatTu.MaVT, vatTu.TenVt, chiTietPhieuNhap.DVT, chiTietPhieuNhap.SoLuong
                    );

                    list.add(tableData);
                }
            }
        }

        return list;
    }

    public void btnClick(View view) {
        onBackPressed();
    }
}