package com.nhom2.qly_nhap_kho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.nhom2.qly_nhap_kho.adapter.BaoCaoAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListBaoCaoActivity extends AppCompatActivity {

    BaoCaoAdapter baoCaoAdapter;
    ListView listViewBaoCao;
    ArrayList<String> arrayBaoCao = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bao_cao);
        //khoitao
        arrayBaoCao.add("Danh sách kho nhập nhiều loại vật tư nhất");
        arrayBaoCao.add("Danh sách các kho chưa nhập vật tư năm 2014");
        arrayBaoCao.add("Danh sách các phiếu nhập có cả Gạch ống và Gạch thẻ");


        setControl();
        actionGetData();
        setEvent();
    }

    private void setEvent() {
        listViewBaoCao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {//bao cao so 1
                    Intent intent = new Intent(ListBaoCaoActivity.this, ChiTietBaoCao1Activity.class);
                    startActivity(intent);
                }
                if (i == 1) {//bao cao so 2
                    Intent intent = new Intent(ListBaoCaoActivity.this, ChiTietBaoCao2Activity.class);
                    startActivity(intent);
                }
                if (i == 2) {//bao cao so 3
                    Intent intent = new Intent(ListBaoCaoActivity.this, ChiTietBaoCao3Activity.class);
                    startActivity(intent);

                }

            }
        });
    }

    private void actionGetData() {
        baoCaoAdapter = new BaoCaoAdapter(this, R.layout.list_bao_cao, arrayBaoCao);
        listViewBaoCao.setAdapter(baoCaoAdapter);
    }

    private void setControl() {
        listViewBaoCao = findViewById(R.id.listViewBaoBao);
    }

    public void btnClick(View view) {
        onBackPressed();
    }
}