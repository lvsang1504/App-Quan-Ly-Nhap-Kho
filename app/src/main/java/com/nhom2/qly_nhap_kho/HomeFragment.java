package com.nhom2.qly_nhap_kho;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.material.imageview.ShapeableImageView;

public class HomeFragment extends Fragment {

    private PreferenceManager preferenceManager;
    TextView textHeader, textEmail;
    ImageButton btnSignOut;
    ShapeableImageView profileImage;
    CardView khoCard, phieuNhapCard, vatTuCard, baoCaoCard;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        setControl(view);


        preferenceManager = new PreferenceManager(getContext());

        loadUserDetails();
        setEvent();


        return view;
    }

    private void setEvent() {
        btnSignOut.setOnClickListener(v -> signOut());

        khoCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), KhoActivity.class);
                startActivity(intent);
            }
        });
        phieuNhapCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PhieuNhapActivity.class);
                startActivity(intent);
            }
        });
        vatTuCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), VatTuActivity.class);
                startActivity(intent);
            }
        });
        baoCaoCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ListBaoCaoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setControl(View view) {
        textHeader = view.findViewById(R.id.textHeader);
        textEmail = view.findViewById(R.id.textEmail);
        btnSignOut = view.findViewById(R.id.btnSignOut);
        profileImage = view.findViewById(R.id.profileImage);
        khoCard = view.findViewById(R.id.khoCard);
        phieuNhapCard = view.findViewById(R.id.phieuNhapCard);
        vatTuCard = view.findViewById(R.id.vatTuCard);
        baoCaoCard = view.findViewById(R.id.baoCaoCard);
    }

    private void loadUserDetails() {
        textHeader.setText("Xin chào " + preferenceManager.getString(Constants.KEY_NAME) + "!");
        textEmail.setText(preferenceManager.getString(Constants.KEY_EMAIL));

        if (preferenceManager.getString(Constants.KEY_IMAGE) != null) {
            byte[] bytes = Base64.decode(preferenceManager.getString(Constants.KEY_IMAGE), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            profileImage.setImageBitmap(bitmap);
        }


    }

    private void signOut() {
        Toast.makeText(getContext(), "Đăng xuất...", Toast.LENGTH_LONG).show();
        preferenceManager.clear();
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}