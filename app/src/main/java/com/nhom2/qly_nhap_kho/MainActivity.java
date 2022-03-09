package com.nhom2.qly_nhap_kho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nhom2.qly_nhap_kho.adapter.PhieuNhapAdapter;
import com.nhom2.qly_nhap_kho.listener.PhieuNhapListener;
import com.nhom2.qly_nhap_kho.model.Kho;
import com.nhom2.qly_nhap_kho.model.PhieuNhap;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    NhapKhoHelper nhapKhoHelper;
    Spinner spinnerKho;

    RecyclerView recycleView;
    PhieuNhapAdapter phieuNhapAdapter;
    TextView txtTongPhieuNhap;

    List<PhieuNhap> arrayPhieuNhap = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        spinnerKho = (Spinner) findViewById(R.id.spinner1);
        recycleView = findViewById(R.id.recycleView);
        txtTongPhieuNhap = findViewById(R.id.txtTongPhieuNhap);


        ArrayList<Kho> arrayKho = new ArrayList<Kho>();
        //arrayKho.add(new Kho("K0", "Tất cả"));
        ArrayList<String> arrayTenKho = new ArrayList<String>();
        arrayTenKho.add("Tất cả");


        //Tao database
        nhapKhoHelper = new NhapKhoHelper(this);

        //Tao bang
        nhapKhoHelper.QueryData("CREATE TABLE IF NOT EXISTS Kho(MaKho VARCHAR(5),TenKho VARCHAR(100))");

        nhapKhoHelper.QueryData("CREATE TABLE IF NOT EXISTS PhieuNhap(SoPhieu INTEGER,NgayLap VARCHAR,MaKho VARCHAR(5))");

        //Them du lieu
        nhapKhoHelper.QueryData("delete from Kho");
        nhapKhoHelper.QueryData("INSERT INTO Kho VALUES ('K1','Bình Chánh')");
        nhapKhoHelper.QueryData("INSERT INTO Kho VALUES ('K2','Tân Phú')");
        nhapKhoHelper.QueryData("INSERT INTO Kho VALUES ('K3','Thủ Đức')");

        //Them du lieu phieu nhap
        nhapKhoHelper.QueryData("delete from PhieuNhap");
        nhapKhoHelper.QueryData("INSERT INTO PhieuNhap VALUES ('1','20/06/2013', 'K1')");
        nhapKhoHelper.QueryData("INSERT INTO PhieuNhap VALUES ('2','07/07/2013', 'K2')");
        nhapKhoHelper.QueryData("INSERT INTO PhieuNhap VALUES ('3','02/01/2014', 'K1')");
        nhapKhoHelper.QueryData("INSERT INTO PhieuNhap VALUES ('4','05/03/2014', 'K3')");
        nhapKhoHelper.QueryData("INSERT INTO PhieuNhap VALUES ('5','25/05/2014', 'K1')");

        //Hien thi

        Cursor dataKho = nhapKhoHelper.GetData("SELECT * FROM Kho");

        Kho kho;
        while (dataKho.moveToNext()) {
            kho = new Kho(dataKho.getString(0), dataKho.getString(1));
            arrayKho.add(kho);
            arrayTenKho.add(kho.getTenKho());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayTenKho);
        spinnerKho.setAdapter(arrayAdapter);

        spinnerKho.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                arrayPhieuNhap.clear();

                if (spinnerKho.getSelectedItem().toString().equals("Tất cả")) {

                    Cursor dataPhieuNhap = nhapKhoHelper.GetData("SELECT * FROM PhieuNhap ");

                    PhieuNhap phieuNhap;
                    while (dataPhieuNhap.moveToNext()) {
                        phieuNhap = new PhieuNhap(Integer.valueOf(dataPhieuNhap.getString(0)), dataPhieuNhap.getString(1), dataPhieuNhap.getString(2));
                        arrayPhieuNhap.add(phieuNhap);
                    }

                    txtTongPhieuNhap.setText("Tổng số phiếu nhập: " + arrayPhieuNhap.size());
                } else {
                    Cursor dataKho = nhapKhoHelper.GetData("SELECT * FROM Kho WHERE TenKho='" + spinnerKho.getSelectedItem().toString() + "'");
                    String ma = "";
                    while (dataKho.moveToNext()) {
                        ma = dataKho.getString(0);

                    }
                    Cursor dataPhieuNhap = nhapKhoHelper.GetData("SELECT * FROM PhieuNhap WHERE MaKho = '" + ma + "'");

                    PhieuNhap phieuNhap;
                    while (dataPhieuNhap.moveToNext()) {
                        phieuNhap = new PhieuNhap(Integer.valueOf(dataPhieuNhap.getString(0)), dataPhieuNhap.getString(1), dataPhieuNhap.getString(2));
                        arrayPhieuNhap.add(phieuNhap);
                    }
                    txtTongPhieuNhap.setText("Tổng số phiếu nhập: " + arrayPhieuNhap.size());
                }


                recycleView.setHasFixedSize(true);
                recycleView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
                phieuNhapAdapter = new PhieuNhapAdapter(MainActivity.this, arrayPhieuNhap, phieuNhapListener);
                recycleView.setAdapter(phieuNhapAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        nhapKhoHelper.close();
    }

    private final PhieuNhapListener phieuNhapListener = new PhieuNhapListener() {
        @Override
        public void onPhieuNhapClicked(String id) {
            Intent intent = new Intent(MainActivity.this, ChiTietPhieuNhapActivity.class);
            startActivity(intent);
        }
    };
}