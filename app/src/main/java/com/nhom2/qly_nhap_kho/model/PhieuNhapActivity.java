package com.nhom2.qly_nhap_kho.model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nhom2.qly_nhap_kho.ChiTietPhieuNhapActivity;
import com.nhom2.qly_nhap_kho.MainActivity;
import com.nhom2.qly_nhap_kho.NhapKhoHelper;
import com.nhom2.qly_nhap_kho.R;
import com.nhom2.qly_nhap_kho.adapter.PhieuNhapAdapter;
import com.nhom2.qly_nhap_kho.listener.PhieuNhapListener;

import java.util.ArrayList;
import java.util.List;

public class PhieuNhapActivity extends AppCompatActivity {
    NhapKhoHelper nhapKhoHelper;
    Spinner spinnerKho;

    RecyclerView recycleView;
    PhieuNhapAdapter phieuNhapAdapter;
    TextView txtTongPhieuNhap;
    FloatingActionButton floatingActionButton;

    List<PhieuNhap> arrayPhieuNhap = new ArrayList<>();
    Kho kho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phieu_nhap);

        ActivityCompat.requestPermissions(this, new String[]
                        {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                PackageManager.PERMISSION_GRANTED);


        spinnerKho = (Spinner) findViewById(R.id.spinner1);
        recycleView = findViewById(R.id.recycleView);
        txtTongPhieuNhap = findViewById(R.id.txtTongPhieuNhap);
        floatingActionButton = findViewById(R.id.floatingActionButton);


        ArrayList<Kho> arrayKho = new ArrayList<Kho>();
        //arrayKho.add(new Kho("K0", "Tất cả"));
        ArrayList<String> arrayTenKho = new ArrayList<String>();
        arrayTenKho.add("Tất cả");


        //Tao database
        nhapKhoHelper = new NhapKhoHelper(this);

        //Tao bang
        nhapKhoHelper.QueryData("CREATE TABLE IF NOT EXISTS Kho(MaKho VARCHAR(5),TenKho VARCHAR(100))");

        nhapKhoHelper.QueryData("CREATE TABLE IF NOT EXISTS PhieuNhap(SoPhieu INTEGER,NgayLap VARCHAR,MaKho VARCHAR(5))");
        nhapKhoHelper.QueryData("CREATE TABLE IF NOT EXISTS ChiTietPhieuNhap(SoPhieu INTEGER,MaVT VARCHAR,DVT VARCHAR, SoLuong INTEGER)");
        nhapKhoHelper.QueryData("CREATE TABLE IF NOT EXISTS VatTu(MaVT VARCHAR, TenVT VARCHAR,XuatXu VARCHAR)");

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

        //Them du lieu chi tiet phieu nhap
        nhapKhoHelper.QueryData("delete from ChiTietPhieuNhap");
        nhapKhoHelper.QueryData("INSERT INTO ChiTietPhieuNhap VALUES ('1','GO', 'Viên', '5000')");
        nhapKhoHelper.QueryData("INSERT INTO ChiTietPhieuNhap VALUES ('1','GT', 'Viên', '2000')");
        nhapKhoHelper.QueryData("INSERT INTO ChiTietPhieuNhap VALUES ('1','XM', 'Bao', '150')");
        nhapKhoHelper.QueryData("INSERT INTO ChiTietPhieuNhap VALUES ('2','SO', 'Thùng', '75')");
        nhapKhoHelper.QueryData("INSERT INTO ChiTietPhieuNhap VALUES ('3','SA', 'Tấn', '25')");
        nhapKhoHelper.QueryData("INSERT INTO ChiTietPhieuNhap VALUES ('3','XM', 'Bao', '100')");
        nhapKhoHelper.QueryData("INSERT INTO ChiTietPhieuNhap VALUES ('4','GO', 'Viên', '10000')");
        nhapKhoHelper.QueryData("INSERT INTO ChiTietPhieuNhap VALUES ('4','SA', 'Tấn', '50')");
        nhapKhoHelper.QueryData("INSERT INTO ChiTietPhieuNhap VALUES ('5','SO', 'Thùng', '240')");
        nhapKhoHelper.QueryData("INSERT INTO ChiTietPhieuNhap VALUES ('5','XM', 'Bao', '430')");

        //Them du lieu VatTu
        nhapKhoHelper.QueryData("delete from VatTu");
        nhapKhoHelper.QueryData("INSERT INTO VatTu VALUES ('GO','Gạch ống', 'Đồng Nai')");
        nhapKhoHelper.QueryData("INSERT INTO VatTu VALUES ('GT','Gạch thẻ', 'Long An')");
        nhapKhoHelper.QueryData("INSERT INTO VatTu VALUES ('SA','Sắt tròn', 'Bình Dương')");
        nhapKhoHelper.QueryData("INSERT INTO VatTu VALUES ('SO','Sơn dầu', 'Tiền Giang')");
        nhapKhoHelper.QueryData("INSERT INTO VatTu VALUES ('XM','Xi măng', 'Hà Tiên')");

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
                actionGetData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogInsert();
            }
        });

    }

    public void actionGetData() {
        arrayPhieuNhap.clear();

        if (spinnerKho.getSelectedItem().toString().equals("Tất cả")) {

            Cursor dataPhieuNhap = nhapKhoHelper.GetData("SELECT * FROM PhieuNhap ");
            kho = new Kho("Tất cả", "Tất cả");

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
                kho = new Kho(ma, dataKho.getString(1));
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
        recycleView.setLayoutManager(new LinearLayoutManager(PhieuNhapActivity.this, LinearLayoutManager.VERTICAL, false));

        phieuNhapAdapter = new PhieuNhapAdapter(PhieuNhapActivity.this, arrayPhieuNhap, phieuNhapListener);
        recycleView.setAdapter(phieuNhapAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        nhapKhoHelper.close();
    }

    private final PhieuNhapListener phieuNhapListener = new PhieuNhapListener() {
        @Override
        public void onPhieuNhapClicked(String id, PhieuNhap phieuNhap) {

            Intent intent = new Intent(PhieuNhapActivity.this, ChiTietPhieuNhapActivity.class);

            Bundle bundle = new Bundle();

            bundle.putString("id", id);
            bundle.putSerializable("phieuNhap", phieuNhap);
            bundle.putSerializable("kho", kho);

            intent.putExtra("data", bundle);

            startActivity(intent);
        }
    };

    public void dialogUpdate(int soPhieu, String ngayLap, String maKho) {

        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_suaphieunhap);

        //anh xa
        EditText editSoPhieu = (EditText) dialog.findViewById(R.id.editSoPhieu);
        EditText editNgayLap = (EditText) dialog.findViewById(R.id.editNgayLap);
        Button btnHoanTat = (Button) dialog.findViewById(R.id.btnHoanTat);
        Button btnXoa = (Button) dialog.findViewById(R.id.btnXoa);

        //them du lieu vao spinner
        Spinner spinnerMaKho=dialog.findViewById(R.id.spinnerMaKho);
        Cursor dataKho = nhapKhoHelper.GetData("SELECT * FROM Kho");
        ArrayList<String> arrayMaKhoTam = new ArrayList<String>();
        Kho khoTam;
        while (dataKho.moveToNext()) {
            khoTam = new Kho(dataKho.getString(0), dataKho.getString(1));
            arrayMaKhoTam.add(khoTam.getMaKho());
        }
        ArrayAdapter arrayAdapterTam = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayMaKhoTam);
        spinnerMaKho.setAdapter(arrayAdapterTam);

        //set du lieu
        editSoPhieu.setText(soPhieu + "");
        editNgayLap.setText(ngayLap);
        spinnerMaKho.setSelection(arrayAdapterTam.getPosition(maKho));

        //bat su kien nut bam
        btnHoanTat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ngayLapMoi = String.valueOf(editNgayLap.getText());
                String maKhoMoi = String.valueOf(spinnerMaKho.getSelectedItem().toString());
                if (TextUtils.isEmpty(ngayLapMoi) || TextUtils.isEmpty(maKhoMoi)) {
                    Toast.makeText(PhieuNhapActivity.this, "Nội dung cần sửa chưa được nhập", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    return;
                }
                nhapKhoHelper.QueryData("UPDATE PhieuNhap SET NgayLap='" + ngayLapMoi + "',MaKho='" + maKhoMoi + "' WHERE SoPhieu='" + soPhieu + "'");
                dialog.dismiss();
                actionGetData();
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nhapKhoHelper.QueryData("DELETE FROM PhieuNhap WHERE SoPhieu='" + soPhieu + "'");
                dialog.dismiss();
                actionGetData();
            }
        });

        dialog.show();
    }

    public void dialogInsert() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_themphieunhap);

        EditText editSoPhieu2 = (EditText) dialog.findViewById(R.id.editSoPhieu2);
        EditText editNgayLap2 = (EditText) dialog.findViewById(R.id.editNgayLap2);
        Button btnThem = (Button) dialog.findViewById(R.id.btnThem);
        Button btnHuy = (Button) dialog.findViewById(R.id.btnHuy);

        //them du lieu vao spinner
        Spinner spinnerMaKho2=dialog.findViewById(R.id.spinnerMaKho2);
        Cursor dataKho = nhapKhoHelper.GetData("SELECT * FROM Kho");
        ArrayList<Kho> arrayTam = new ArrayList<Kho>();
        ArrayList<String> arrayTenKhoTam = new ArrayList<String>();
        Kho khoTam;
        while (dataKho.moveToNext()) {
            khoTam = new Kho(dataKho.getString(0), dataKho.getString(1));
            arrayTam.add(khoTam);
            arrayTenKhoTam.add(khoTam.getTenKho());
        }
        ArrayAdapter arrayAdapterTam = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayTenKhoTam);
        spinnerMaKho2.setAdapter(arrayAdapterTam);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int soPhieuMoi =0;
                String ngayLapMoi = String.valueOf(editNgayLap2.getText());
                String maKhoMoi = String.valueOf(arrayTam.get(spinnerMaKho2.getSelectedItemPosition()).getMaKho());
                if (TextUtils.isEmpty(String.valueOf(editSoPhieu2.getText())) || TextUtils.isEmpty(ngayLapMoi) || TextUtils.isEmpty(maKhoMoi)) {
                    Toast.makeText(PhieuNhapActivity.this, "Nội dung cần thêm chưa được nhập", Toast.LENGTH_SHORT).show();

                    return;
                }
                //kiem tra chu cai
                try {
                    soPhieuMoi = Integer.parseInt(String.valueOf(editSoPhieu2.getText()));
                }catch (Exception e){
                    Toast.makeText(PhieuNhapActivity.this, "Số phiếu là một số nguyên", Toast.LENGTH_SHORT).show();
                    return;
                }
                //kiem tra trung
                Cursor dataPhieuNhap = nhapKhoHelper.GetData("SELECT * FROM PhieuNhap");
                ArrayList<PhieuNhap> arrayPhieuNhap = new ArrayList<PhieuNhap>();
                PhieuNhap phieuNhapTam;
                while (dataPhieuNhap.moveToNext()) {
                    phieuNhapTam = new PhieuNhap(dataPhieuNhap.getInt(0), dataPhieuNhap.getString(1),dataPhieuNhap.getString(2));
                    arrayPhieuNhap.add(phieuNhapTam);
                }

                for (int i = 0; i < arrayPhieuNhap.size(); i++) {
                    if(soPhieuMoi==arrayPhieuNhap.get(i).getSoPhieu()){
                        Toast.makeText(PhieuNhapActivity.this, "Số phiếu bị trùng", Toast.LENGTH_SHORT).show();

                        return;
                    }
                }


                nhapKhoHelper.QueryData("INSERT INTO PhieuNhap VALUES ('" + soPhieuMoi + "','" + ngayLapMoi + "', '" + maKhoMoi + "')");
                dialog.dismiss();
                actionGetData();

            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }

    public void btnClick(View view) {
        onBackPressed();
    }
}