package com.nhom2.qly_nhap_kho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nhom2.qly_nhap_kho.adapter.PhieuNhapAdapter;
import com.nhom2.qly_nhap_kho.adapter.VatTuAdapter;
import com.nhom2.qly_nhap_kho.model.Kho;
import com.nhom2.qly_nhap_kho.model.PhieuNhap;
import com.nhom2.qly_nhap_kho.model.PhieuNhapActivity;
import com.nhom2.qly_nhap_kho.model.VatTu;

import java.util.ArrayList;
import java.util.List;

public class VatTuActivity extends AppCompatActivity {
    NhapKhoHelper nhapKhoHelper;
    VatTuAdapter vatTuAdapter;
    RecyclerView recyclerViewVatTu;
    List<VatTu> arrayVatTu = new ArrayList<>();
    VatTu vatTu;
    FloatingActionButton floatingActionButtonVatTu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vat_tu);
        nhapKhoHelper=new NhapKhoHelper(this);
        anhXa();
        actionGetData();


        floatingActionButtonVatTu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogInsert();
            }
        });
    }


    private void anhXa() {
        recyclerViewVatTu = findViewById(R.id.recyclerViewVatTu);
        floatingActionButtonVatTu=findViewById(R.id.floatingActionButtonVatTu);
    }

    public void dialogInsert() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_themvattu);

        EditText editMaVT = (EditText) dialog.findViewById(R.id.editMaVatTu);
        EditText editTenVT = (EditText) dialog.findViewById(R.id.editTenVatTu);
        EditText editXuatXu = (EditText) dialog.findViewById(R.id.editXuatXu);
        Button btnThem = (Button) dialog.findViewById(R.id.btnThemVatTu);
        Button btnHuy = (Button) dialog.findViewById(R.id.btnHuyVatTu);


        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maVT = String.valueOf(editMaVT.getText());
                String tenVT = String.valueOf(editTenVT.getText());
                String xuatXu = String.valueOf(editXuatXu.getText());
                if (TextUtils.isEmpty(maVT) || TextUtils.isEmpty(tenVT) || TextUtils.isEmpty(xuatXu)) {
                    Toast.makeText(VatTuActivity.this, "Nội dung cần thêm chưa được nhập", Toast.LENGTH_SHORT).show();

                    return;
                }
                //kiem tra trung
                Cursor dataVatTu = nhapKhoHelper.GetData("SELECT * FROM VatTu");
                ArrayList<VatTu> arrayVatTu = new ArrayList<VatTu>();
                VatTu vatTuTam;
                while (dataVatTu.moveToNext()) {
                    vatTuTam = new VatTu(dataVatTu.getString(0), dataVatTu.getString(1),dataVatTu.getString(2));
                    arrayVatTu.add(vatTuTam);
                }

                for (int i = 0; i < arrayVatTu.size(); i++) {
                    if(maVT==arrayVatTu.get(i).getMaVT()){
                        Toast.makeText(VatTuActivity.this, "Mã vật tư bị trùng", Toast.LENGTH_SHORT).show();

                        return;
                    }
                }
                for (int i = 0; i < arrayVatTu.size(); i++) {
                    if(tenVT==arrayVatTu.get(i).getTenVt()){
                        Toast.makeText(VatTuActivity.this, "Tên vật tư bị trùng", Toast.LENGTH_SHORT).show();

                        return;
                    }
                }


                nhapKhoHelper.QueryData("INSERT INTO VatTu VALUES ('" + maVT + "','" + tenVT + "', '" + xuatXu + "')");
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
    public void dialogUpdate(String maVT, String tenVT, String xuatXu) {

        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_suavattu);

        //anh xa
        EditText editSuaMaVatTu = (EditText) dialog.findViewById(R.id.editSuaMaVatTu);
        EditText editSuaTenVatTu = (EditText) dialog.findViewById(R.id.editSuaTenVatTu);
        EditText editSuaXuatXu = (EditText) dialog.findViewById(R.id.editSuaXuatXu);

        Button btnSuaVatTu = (Button) dialog.findViewById(R.id.btnSuaVatTu);
        Button btnXoaVatTu = (Button) dialog.findViewById(R.id.btnXoaVatTu);


        //set du lieu
        editSuaMaVatTu.setText(maVT);
        editSuaTenVatTu.setText(tenVT);
        editSuaXuatXu.setText(xuatXu);


        //bat su kien nut bam
        btnSuaVatTu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String TenVatTuMoi = String.valueOf(editSuaTenVatTu.getText());
                String XuatXuMoi = String.valueOf(editSuaXuatXu.getText());
                if (TextUtils.isEmpty(TenVatTuMoi) || TextUtils.isEmpty(XuatXuMoi)) {
                    Toast.makeText(VatTuActivity.this, "Nội dung cần sửa chưa được nhập", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    return;
                }
                nhapKhoHelper.QueryData("UPDATE VatTu SET TenVT='" + TenVatTuMoi + "',XuatXu='" + XuatXuMoi + "' WHERE MaVT='" + maVT + "'");
                dialog.dismiss();
                actionGetData();
            }
        });

        btnXoaVatTu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nhapKhoHelper.QueryData("DELETE FROM VatTu WHERE MaVT='" + maVT + "'");
                dialog.dismiss();
                actionGetData();
            }
        });

        dialog.show();
    }
    private void actionGetData(){
        arrayVatTu.clear();

        Cursor dataVatTu = nhapKhoHelper.GetData("SELECT * FROM VatTu");

        while (dataVatTu.moveToNext()) {
            vatTu = new VatTu(dataVatTu.getString(0), dataVatTu.getString(1),dataVatTu.getString(2));
            arrayVatTu.add(vatTu);
        }



        recyclerViewVatTu.setHasFixedSize(true);
        recyclerViewVatTu.setLayoutManager(new LinearLayoutManager(VatTuActivity.this, LinearLayoutManager.VERTICAL, false));

        vatTuAdapter = new VatTuAdapter(VatTuActivity.this, arrayVatTu);
        recyclerViewVatTu.setAdapter(vatTuAdapter);
    }
}