package com.nhom2.qly_nhap_kho;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class NhapKhoHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "NhapKho.sqlite";

    private static final int DB_VERSION = 1;


    public NhapKhoHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //Truy van khong tra ket qua
    public void QueryData(String sql){
        SQLiteDatabase database=getWritableDatabase();
        database.execSQL(sql);
    }

    //Truy van tra ket qua
    public Cursor GetData(String sql){
        SQLiteDatabase database=getReadableDatabase();
        return database.rawQuery(sql,null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
