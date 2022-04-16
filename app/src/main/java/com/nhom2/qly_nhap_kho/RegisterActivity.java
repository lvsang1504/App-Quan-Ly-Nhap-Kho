package com.nhom2.qly_nhap_kho;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nhom2.qly_nhap_kho.model.User;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class RegisterActivity extends AppCompatActivity {

    EditText inputFirstName, inputLastName, inputEmail, inputPassword, inputConfirmPassword;
    Button buttonSignUp;
    TextView textAddImage;
    ImageView imageProfile;
    FrameLayout layoutImage;
    NhapKhoHelper helper;
    private String encodedImage = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setControl();


        helper = NhapKhoHelper.getInstance(this);
        setEvent();



    }

    private void setEvent() {
        layoutImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setType("image/*");
            pickImage.launch(intent);
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidSignUpDetails()) {
                    User user = new User(
                            0,
                            inputFirstName.getText().toString().trim(),
                            inputLastName.getText().toString().trim(),
                            inputEmail.getText().toString().trim(),
                            inputPassword.getText().toString().trim(),
                            encodedImage
                    );
                    int i = helper.addUser(user);
                    if (i == 1) {
                        showToast("Email đã tồn tại!");
                    } else if (i == 0) {
                        showToast("Đăng ký thành công!");
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }

    private void setControl() {
        inputFirstName = findViewById(R.id.inputFirstName);
        inputLastName = findViewById(R.id.inputLastName);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        inputConfirmPassword = findViewById(R.id.inputConfirmPassword);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        layoutImage = findViewById(R.id.layoutImage);
        imageProfile = findViewById(R.id.imageProfile);
        textAddImage = findViewById(R.id.textAddImage);
    }

    public void signIn(View view) {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private final ActivityResultLauncher<Intent> pickImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    if (result.getData() != null) {
                        Uri imageUri = result.getData().getData();
                        try {
                            InputStream inputStream = getContentResolver().openInputStream(imageUri);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            imageProfile.setImageBitmap(bitmap);
                            textAddImage.setVisibility(View.GONE);
                            encodedImage = encodeImage(bitmap);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );

    private String encodeImage(Bitmap bitmap) {
        int previewWidth = 150;
        int previewHeight = bitmap.getHeight() * previewWidth / bitmap.getWidth();
        Bitmap previewBitmap = Bitmap.createScaledBitmap(bitmap, previewWidth, previewHeight, false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        previewBitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    private Boolean isValidSignUpDetails() {
        if (inputFirstName.getText().toString().trim().isEmpty()) {
            showToast("Enter first name");
            return false;
        } else if (inputLastName.getText().toString().trim().isEmpty()) {
            showToast("Enter last name");
            return false;
        } else if (encodedImage.trim().isEmpty()) {
            showToast("Select image");
            return false;
        } else if (inputEmail.getText().toString().trim().isEmpty()) {
            showToast("Enter email");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(inputEmail.getText().toString().trim()).matches()) {
            showToast("Enter valid email");
            return false;
        } else if (inputPassword.getText().toString().trim().isEmpty()) {
            showToast("Enter password");
            return false;
        } else if (inputConfirmPassword.getText().toString().trim().isEmpty()) {
            showToast("Confirm your password");
            return false;
        } else if (!inputPassword.getText().toString().trim().equals(inputConfirmPassword.getText().toString().trim())) {
            showToast("Password & confirm password must be same");
            return false;
        } else return true;
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}