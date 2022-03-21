package com.nhom2.qly_nhap_kho;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class NhapKhoHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "NhapKho.sqlite";

    private static final int DB_VERSION = 1;

    private static NhapKhoHelper sInstance;


    public NhapKhoHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static synchronized NhapKhoHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new NhapKhoHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    //Truy van khong tra ket qua
    public void QueryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    //Truy van tra ket qua
    public Cursor GetData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tao bang
        db.execSQL("CREATE TABLE IF NOT EXISTS Kho(MaKho VARCHAR(5),TenKho VARCHAR(100))");

        db.execSQL("CREATE TABLE IF NOT EXISTS PhieuNhap(SoPhieu INTEGER,NgayLap VARCHAR,MaKho VARCHAR(5))");
        db.execSQL("CREATE TABLE IF NOT EXISTS ChiTietPhieuNhap(SoPhieu INTEGER,MaVT VARCHAR,DVT VARCHAR, SoLuong INTEGER)");
        db.execSQL("CREATE TABLE IF NOT EXISTS VatTu(MaVT VARCHAR, TenVT VARCHAR,XuatXu VARCHAR)");

        //Them du lieu
        db.execSQL("INSERT INTO Kho VALUES ('K1','Bình Chánh')");
        db.execSQL("INSERT INTO Kho VALUES ('K2','Tân Phú')");
        db.execSQL("INSERT INTO Kho VALUES ('K3','Thủ Đức')");

        //Them du lieu phieu nhap
        db.execSQL("INSERT INTO PhieuNhap VALUES ('1','20/06/2013', 'K1')");
        db.execSQL("INSERT INTO PhieuNhap VALUES ('2','07/07/2013', 'K2')");
        db.execSQL("INSERT INTO PhieuNhap VALUES ('3','02/01/2014', 'K1')");
        db.execSQL("INSERT INTO PhieuNhap VALUES ('4','05/03/2014', 'K3')");
        db.execSQL("INSERT INTO PhieuNhap VALUES ('5','25/05/2014', 'K1')");

        //Them du lieu chi tiet phieu nhap
        db.execSQL("INSERT INTO ChiTietPhieuNhap VALUES ('1','GO', 'Viên', '5000')");
        db.execSQL("INSERT INTO ChiTietPhieuNhap VALUES ('1','GT', 'Viên', '2000')");
        db.execSQL("INSERT INTO ChiTietPhieuNhap VALUES ('1','XM', 'Bao', '150')");
        db.execSQL("INSERT INTO ChiTietPhieuNhap VALUES ('2','SO', 'Thùng', '75')");
        db.execSQL("INSERT INTO ChiTietPhieuNhap VALUES ('3','SA', 'Tấn', '25')");
        db.execSQL("INSERT INTO ChiTietPhieuNhap VALUES ('3','XM', 'Bao', '100')");
        db.execSQL("INSERT INTO ChiTietPhieuNhap VALUES ('4','GO', 'Viên', '10000')");
        db.execSQL("INSERT INTO ChiTietPhieuNhap VALUES ('4','SA', 'Tấn', '50')");
        db.execSQL("INSERT INTO ChiTietPhieuNhap VALUES ('5','SO', 'Thùng', '240')");
        db.execSQL("INSERT INTO ChiTietPhieuNhap VALUES ('5','XM', 'Bao', '430')");

        //Them du lieu VatTu
        db.execSQL("INSERT INTO VatTu VALUES ('GO','Gạch ống', 'Đồng Nai')");
        db.execSQL("INSERT INTO VatTu VALUES ('GT','Gạch thẻ', 'Long An')");
        db.execSQL("INSERT INTO VatTu VALUES ('SA','Sắt tròn', 'Bình Dương')");
        db.execSQL("INSERT INTO VatTu VALUES ('SO','Sơn dầu', 'Tiền Giang')");
        db.execSQL("INSERT INTO VatTu VALUES ('XM','Xi măng', 'Hà Tiên')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
