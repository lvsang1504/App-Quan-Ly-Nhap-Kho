package com.nhom2.qly_nhap_kho;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

/**
 * Created by Dytstudio.
 */

public class BaseActivity extends AppCompatActivity {
    Toolbar toolbar;

    public void setupToolbar(int toolbarId, String title, @ColorRes int titleColor, @ColorRes int colorBg, @DrawableRes int burger){
        toolbar = (Toolbar) findViewById(toolbarId);
        toolbar.setBackgroundColor(getResources().getColor(colorBg));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeAsUpIndicator(burger);
    }
//    public void changeStatusBarColor() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
//        }
//    }


}
