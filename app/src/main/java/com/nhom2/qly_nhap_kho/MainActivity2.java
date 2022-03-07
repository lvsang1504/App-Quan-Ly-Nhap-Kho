package com.nhom2.qly_nhap_kho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv1= (TextView) findViewById(R.id.textView);

        Intent i=getIntent();
        String value1=i.getStringExtra("key1");
        tv1.setText(value1);
    }
}