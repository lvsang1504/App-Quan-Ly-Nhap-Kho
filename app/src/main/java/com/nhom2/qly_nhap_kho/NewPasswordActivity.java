package com.nhom2.qly_nhap_kho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewPasswordActivity extends AppCompatActivity {
    EditText editTextNewPassword,editTextNewPassword2;
    Button buttonConfirmNewPassword;
    String gmail="";
    NhapKhoHelper nhapKhoHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);
        nhapKhoHelper =  NhapKhoHelper.getInstance(this);
        gmail=getIntent().getStringExtra("gmail");
        setControl();
        setEvent();
    }

    private void setEvent() {
        buttonConfirmNewPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(editTextNewPassword.getText())||TextUtils.isEmpty(editTextNewPassword2.getText())) {
                    Toast.makeText(NewPasswordActivity.this, "Nhập mật khẩu", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!editTextNewPassword.getText().toString().trim().equals(editTextNewPassword2.getText().toString().trim())) {
                    Toast.makeText(NewPasswordActivity.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                    return;
                }
                nhapKhoHelper.QueryData("UPDATE User SET PASSWORD='" + String.valueOf(editTextNewPassword.getText().toString().trim()) + "' WHERE EMAIL='" + gmail + "'");
                Toast.makeText(NewPasswordActivity.this,"Send Email successfully",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(NewPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setControl() {
        editTextNewPassword=findViewById(R.id.editTextNewPassword);
        editTextNewPassword2=findViewById(R.id.editTextNewPassword2);
        buttonConfirmNewPassword=findViewById(R.id.buttonConfirmNewPassword);
    }
}