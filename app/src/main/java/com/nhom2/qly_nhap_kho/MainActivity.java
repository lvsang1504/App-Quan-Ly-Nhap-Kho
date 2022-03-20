package com.nhom2.qly_nhap_kho;

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
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nhom2.qly_nhap_kho.adapter.CustomGridAdapter;
import com.nhom2.qly_nhap_kho.adapter.PhieuNhapAdapter;
import com.nhom2.qly_nhap_kho.listener.PhieuNhapListener;
import com.nhom2.qly_nhap_kho.model.GridItem;
import com.nhom2.qly_nhap_kho.model.Kho;
import com.nhom2.qly_nhap_kho.model.PhieuNhap;
import com.nhom2.qly_nhap_kho.model.PhieuNhapActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    NhapKhoHelper nhapKhoHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createDataBase();
        List<GridItem> image_details = getListData();
        final GridView gridView = findViewById(R.id.gridView);
        gridView.setAdapter(new CustomGridAdapter(this, image_details));


        // When the user clicks on the GridItem
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                switch (position) {
                    case 0:
                        Intent intent0 = new Intent(MainActivity.this, KhoActivity.class);
                        startActivity(intent0);
                        break;
                    case 1:
                        Intent intent1 = new Intent(MainActivity.this, PhieuNhapActivity.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(MainActivity.this, VatTuActivity.class);
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(MainActivity.this, ListBaoCaoActivity.class);
                        startActivity(intent3);
                        break;
                }
            }
        });
    }

    private void createDataBase() {
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
    }

    private List<GridItem> getListData() {
        List<GridItem> list = new ArrayList<GridItem>();
        GridItem gridItem = new GridItem("Kho", "warehouse_64px");
        GridItem gridItem1 = new GridItem("Phiếu nhập", "receipt_64px");
        GridItem gridItem2 = new GridItem("Vật tư", "commodity_64px");
        GridItem gridItem3 = new GridItem("Báo cáo", "presentation_64px");

        list.add(gridItem);
        list.add(gridItem1);
        list.add(gridItem2);
        list.add(gridItem3);

        return list;
    }


}