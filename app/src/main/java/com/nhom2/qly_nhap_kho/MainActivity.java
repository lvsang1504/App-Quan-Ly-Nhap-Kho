package com.nhom2.qly_nhap_kho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.nhom2.qly_nhap_kho.adapter.CustomGridAdapter;
import com.nhom2.qly_nhap_kho.model.GridItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PreferenceManager preferenceManager;
    TextView textHeader, textEmail;
    ImageButton btnSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textHeader = findViewById(R.id.textHeader);
        textEmail = findViewById(R.id.textEmail);
        btnSignOut = findViewById(R.id.btnSignOut);

        preferenceManager = new PreferenceManager(MainActivity.this);

        loadUserDetails();

        btnSignOut.setOnClickListener(v -> signOut());
    }

    private void loadUserDetails() {
        textHeader.setText("Xin chào " + preferenceManager.getString(Constants.KEY_NAME) + "!");
        textEmail.setText(preferenceManager.getString(Constants.KEY_EMAIL));

    }

    private void signOut() {
        Toast.makeText(MainActivity.this, "Đăng xuất...", Toast.LENGTH_LONG).show();
        preferenceManager.clear();
        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }


    public void baoCaoCard(View view) {
        Intent intent = new Intent(MainActivity.this, ListBaoCaoActivity.class);
        startActivity(intent);
    }

    public void khoCard(View view) {
        Intent intent = new Intent(MainActivity.this, KhoActivity.class);
        startActivity(intent);
    }

    public void phieuNhapCard(View view) {
        Intent intent = new Intent(MainActivity.this, PhieuNhapActivity.class);
        startActivity(intent);
    }

    public void vatTuCard(View view) {
        Intent intent = new Intent(MainActivity.this, VatTuActivity.class);
        startActivity(intent);
    }
}