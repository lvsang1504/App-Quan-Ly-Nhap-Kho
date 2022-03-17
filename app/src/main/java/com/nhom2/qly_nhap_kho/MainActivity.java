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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<GridItem> image_details = getListData();
        final GridView gridView = findViewById(R.id.gridView);
        gridView.setAdapter(new CustomGridAdapter(this, image_details));

        // When the user clicks on the GridItem
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                switch (position) {
                    case 1:
                        Intent intent = new Intent(MainActivity.this, PhieuNhapActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                }
            }
        });
    }

    private List<GridItem> getListData() {
        List<GridItem> list = new ArrayList<GridItem>();
        GridItem gridItem = new GridItem("Kho", "ic_store");
        GridItem gridItem1 = new GridItem("Phiếu nhập", "ic_phieu_nhap");
        GridItem gridItem2 = new GridItem("Vật tư", "ic_vat_tu");
        GridItem gridItem3 = new GridItem("Báo cáo", "ic_bao_cao");

        list.add(gridItem);
        list.add(gridItem1);
        list.add(gridItem2);
        list.add(gridItem3);

        return list;
    }


}