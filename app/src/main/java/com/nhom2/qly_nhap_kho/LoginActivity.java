package com.nhom2.qly_nhap_kho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;



import com.google.android.material.button.MaterialButton;
import com.nhom2.qly_nhap_kho.model.User;

public class LoginActivity extends AppCompatActivity {

    NhapKhoHelper helper;
    MaterialButton buttonSignIn;
    EditText inputEmail, inputPassword;
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setControl();


        helper = NhapKhoHelper.getInstance(this);
        preferenceManager = new PreferenceManager(getApplicationContext());
        if (preferenceManager.getBoolean(Constants.KEY_IS_SIGNED_IN)) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check()) {
                    User user = helper.checkUserExist(inputEmail.getText().toString().trim(), inputPassword.getText().toString().trim());
                    if (user != null) {
                        preferenceManager.putBoolean(Constants.KEY_IS_SIGNED_IN, true);
                        preferenceManager.putString(Constants.KEY_USER_ID, user.getId() + "");
                        preferenceManager.putString(Constants.KEY_NAME, user.getFirstname() + " " + user.getLastname());
                        preferenceManager.putString(Constants.KEY_EMAIL, user.getEmail());
                        preferenceManager.putString(Constants.KEY_IMAGE, user.getImageBitmap());

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Đăng nhập thất bại!", Toast.LENGTH_LONG).show();

                    }
                }
            }
        });


    }

    private void setControl() {
        buttonSignIn = findViewById(R.id.buttonSignIn);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
    }

    private boolean check() {
        if (inputEmail.getText().toString().trim().isEmpty()) {
            Toast.makeText(LoginActivity.this, "Enter email", Toast.LENGTH_LONG).show();
            return false;
        } else if (inputPassword.getText().toString().trim().isEmpty()) {
            Toast.makeText(LoginActivity.this, "Enter password", Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }

    public void signUp(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public void forgotPassword(View view) {
        if(inputEmail.getText().toString().isEmpty()){
            Toast.makeText(LoginActivity.this, "Enter email", Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(LoginActivity.this, OTPActivity.class);
        intent.putExtra("gmail",inputEmail.getText().toString());
        Log.d("gmail",inputEmail.getText().toString());
        startActivity(intent);
    }
}