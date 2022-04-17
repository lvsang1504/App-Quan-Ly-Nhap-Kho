package com.nhom2.qly_nhap_kho;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.nhom2.qly_nhap_kho.model.Kho;
import com.nhom2.qly_nhap_kho.model.User;

import java.util.ArrayList;
import java.util.List;

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

        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                "VatTu" +
                "(MaVT VARCHAR PRIMARY KEY" +
                ", TenVT VARCHAR" +
                ",XuatXu VARCHAR)" +
                "");
        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                "User(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "FIRSTNAME VARCHAR(100)," +
                "LASTNAME VARCHAR(100)," +
                "EMAIL VARCHAR(100), " +
                "PASSWORD VARCHAR(100), I" +
                "MAGE VARCHAR(5000))");
        db.execSQL("CREATE TABLE IF NOT EXISTS Kho(" +
                "MaKho VARCHAR(5) PRIMARY KEY" +
                ",TenKho VARCHAR(100))" +
                "");

        db.execSQL("CREATE TABLE IF NOT EXISTS PhieuNhap" +
                "(SoPhieu INTEGER PRIMARY KEY" +
                ",NgayLap DATE" +
                ",MaKho VARCHAR(5)" +
                ", FOREIGN KEY (MaKho)" +
                " REFERENCES Kho (MaKho)" +
                ")");
        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                "ChiTietPhieuNhap" +
                "(SoPhieu INTEGER " +
                ",MaVT VARCHAR" +
                ",DVT VARCHAR" +
                ",SoLuong INTEGER" +

                ",FOREIGN KEY (SoPhieu)" +
                " REFERENCES PhieuNhap(SoPhieu)" +
                ",FOREIGN KEY (MaVT)" +
                " REFERENCES VatTu (MaVT) "+
                ",PRIMARY KEY(SoPhieu, MaVT))" );



        //Them du lieu VatTu
        db.execSQL("INSERT INTO VatTu VALUES ('GO','Gạch ống', 'Đồng Nai')");
        db.execSQL("INSERT INTO VatTu VALUES ('GT','Gạch thẻ', 'Long An')");
        db.execSQL("INSERT INTO VatTu VALUES ('SA','Sắt tròn', 'Bình Dương')");
        db.execSQL("INSERT INTO VatTu VALUES ('SO','Sơn dầu', 'Tiền Giang')");
        db.execSQL("INSERT INTO VatTu VALUES ('XM','Xi măng', 'Hà Tiên')");

        //Them du lieu
        db.execSQL("INSERT INTO Kho VALUES ('K1','Bình Chánh')");
        db.execSQL("INSERT INTO Kho VALUES ('K2','Tân Phú')");
        db.execSQL("INSERT INTO Kho VALUES ('K3','Thủ Đức')");

        //user

        db.execSQL("INSERT INTO User(FIRSTNAME,LASTNAME, EMAIL, PASSWORD) VALUES ('admin','1','a','1')");

        //Them du lieu phieu nhap
        db.execSQL("INSERT INTO PhieuNhap VALUES (1,'2022-04-17', 'K1')");
        db.execSQL("INSERT INTO PhieuNhap VALUES (2,'2022-04-17', 'K2')");
        db.execSQL("INSERT INTO PhieuNhap VALUES (3,'2022-04-17', 'K1')");
        db.execSQL("INSERT INTO PhieuNhap VALUES (4,'2022-04-17', 'K3')");
        db.execSQL("INSERT INTO PhieuNhap VALUES (5,'2022-04-17', 'K1')");
        db.execSQL("INSERT INTO PhieuNhap VALUES (6,'2022-04-17', 'K1')");
        db.execSQL("INSERT INTO PhieuNhap VALUES (7,'2022-04-17', 'K2')");
        db.execSQL("INSERT INTO PhieuNhap VALUES (8,'2022-04-17', 'K1')");
        db.execSQL("INSERT INTO PhieuNhap VALUES (9,'2022-04-17', 'K3')");
        db.execSQL("INSERT INTO PhieuNhap VALUES (10,'2022-04-17', 'K1')");
        db.execSQL("INSERT INTO PhieuNhap VALUES (11,'2022-04-17', 'K1')");
        db.execSQL("INSERT INTO PhieuNhap VALUES (12,'2022-04-17', 'K3')");


        //Them du lieu chi tiet phieu nhap
        db.execSQL("INSERT INTO ChiTietPhieuNhap VALUES (1,'GO', 'Viên', '5000')");
        db.execSQL("INSERT INTO ChiTietPhieuNhap VALUES (1,'GT', 'Viên', '2000')");
        db.execSQL("INSERT INTO ChiTietPhieuNhap VALUES (1,'XM', 'Bao', '150')");
        db.execSQL("INSERT INTO ChiTietPhieuNhap VALUES (2,'SO', 'Thùng', '75')");
        db.execSQL("INSERT INTO ChiTietPhieuNhap VALUES (3,'SA', 'Tấn', '25')");
        db.execSQL("INSERT INTO ChiTietPhieuNhap VALUES (3,'XM', 'Bao', '100')");
        db.execSQL("INSERT INTO ChiTietPhieuNhap VALUES (4,'GO', 'Viên', '10000')");
        db.execSQL("INSERT INTO ChiTietPhieuNhap VALUES (4,'SA', 'Tấn', '50')");
        db.execSQL("INSERT INTO ChiTietPhieuNhap VALUES (5,'SO', 'Thùng', '240')");
        db.execSQL("INSERT INTO ChiTietPhieuNhap VALUES (5,'XM', 'Bao', '430')");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public int addUser(User user) {
        SQLiteDatabase db = getWritableDatabase();

        String selection = "WHERE EMAIL='" + user.getEmail() + "'";

        Cursor cursor = db.rawQuery("SELECT * FROM User " + selection, null);
        int count = cursor.getCount();

        if (count>1) {
            return 1;
        }

        db.execSQL("INSERT INTO User(FIRSTNAME,LASTNAME, EMAIL, PASSWORD, IMAGE) VALUES ('" + user.getFirstname()
                + "','" + user.getLastname()
                + "','" + user.getEmail()
                + "','" + user.getPassword()
                + "','" + user.getImageBitmap()
                + "')");
        return 0;
    }

    public User checkUserExist(String username, String password) {
        User user = null;
        SQLiteDatabase db = getReadableDatabase();

        String selection = "WHERE EMAIL='" + username + "' and PASSWORD = '" + password + "'";

        Cursor cursor = db.rawQuery("SELECT * FROM User " + selection, null);
        int count = cursor.getCount();
        System.out.println(count + "");
        while (cursor.moveToNext()) {
            user = new User(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5)
            );
        }

        cursor.close();
        close();

        if (count > 0) {
            return user;
        } else {
            return null;
        }
    }


}
