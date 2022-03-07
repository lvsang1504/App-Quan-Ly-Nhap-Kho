package com.nhom2.qly_nhap_kho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    NhapKhoHelper nhapKhoHelper;
    Spinner spinnerKho;
    Button buttonSelect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        spinnerKho=(Spinner) findViewById(R.id.spinner1);
        buttonSelect=(Button) findViewById(R.id.Select);

        ArrayList<Kho> arrayKho=new ArrayList<Kho>();
        ArrayList<String> arrayTenKho=new ArrayList<String>();


        //Tao database
        nhapKhoHelper=new NhapKhoHelper(this,"NhapKho.sqlite",null,1);

        //Tao bang
        nhapKhoHelper.QueryData("CREATE TABLE IF NOT EXISTS Kho(MaKho VARCHAR(5),TenKho VARCHAR(100))");

        nhapKhoHelper.QueryData("CREATE TABLE IF NOT EXISTS PhieuNhap(SoPhieu INTEGER,NgayLap VARCHAR,MaKho VARCHAR(5))");

        //Them du lieu
        nhapKhoHelper.QueryData("delete from Kho");
        nhapKhoHelper.QueryData("INSERT INTO Kho VALUES ('K1','Bình Chánh')");
        nhapKhoHelper.QueryData("INSERT INTO Kho VALUES ('K2','Tân Phú')");
        nhapKhoHelper.QueryData("INSERT INTO Kho VALUES ('K3','Thủ Đức')");

        //Hien thi

        Cursor dataKho= nhapKhoHelper.GetData("SELECT * FROM Kho");
        Kho kho;
        while(dataKho.moveToNext()){
            kho=new Kho(dataKho.getString(0),dataKho.getString(1));
            arrayKho.add(kho);
            arrayTenKho.add(kho.getTenKho());
        }
        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayTenKho);
        spinnerKho.setAdapter(arrayAdapter);

        buttonSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,MainActivity2.class);

                Cursor dataKho= nhapKhoHelper.GetData("SELECT * FROM Kho WHERE TenKho='"+spinnerKho.getSelectedItem().toString()+"'");
                String ma="";
                while(dataKho.moveToNext()){
                    ma=dataKho.getString(0);

                }

                i.putExtra("key1",ma);
                startActivity(i);
            }
        });
    }
}