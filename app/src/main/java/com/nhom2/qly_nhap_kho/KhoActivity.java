package com.nhom2.qly_nhap_kho;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nhom2.qly_nhap_kho.adapter.KhoAdapter;
import com.nhom2.qly_nhap_kho.adapter.VatTuAdapter;
import com.nhom2.qly_nhap_kho.model.Kho;
import com.nhom2.qly_nhap_kho.model.VatTu;

import java.util.ArrayList;
import java.util.List;

public class KhoActivity extends AppCompatActivity {
    NhapKhoHelper nhapKhoHelper;
    KhoAdapter khoAdapter;
    RecyclerView recyclerViewKho;
    List<Kho> arrayKho = new ArrayList<>();
    Kho kho;
    FloatingActionButton floatingActionButtonKho;
    EditText editTextSearchKho;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kho);
        nhapKhoHelper = NhapKhoHelper.getInstance(this);
        setControl();
        actionGetData();
        setEvent();

    }

    private void setEvent() {
        floatingActionButtonKho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogInsert();
            }
        });
        editTextSearchKho.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                actionGetData();
                return false;
            }
        });
    }

    ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        private final Paint paint = new Paint();

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            Toast.makeText(KhoActivity.this, "on Move", Toast.LENGTH_SHORT).show();
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
            Toast.makeText(KhoActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
            //Remove swiped item from list and notify the RecyclerView
            int position = viewHolder.getAdapterPosition();

            Cursor data = nhapKhoHelper.GetData("SELECT * FROM PhieuNhap WHERE MaKho='" + arrayKho.get(position).getMaKho() + "'");
            while (data.moveToNext()) {
                Toast.makeText(KhoActivity.this, "Kho có phiếu nhập không thể xóa", Toast.LENGTH_SHORT).show();
                khoAdapter.notifyDataSetChanged();
                return;
            }

            nhapKhoHelper.QueryData("DELETE FROM Kho WHERE MaKho='" + arrayKho.get(position).getMaKho() + "'");
            arrayKho.remove(position);
            khoAdapter.notifyDataSetChanged();
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

            float translationX = dX;
            View itemView = viewHolder.itemView;
            float height = (float) itemView.getBottom() - (float) itemView.getTop();

            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE && dX <= 0) // Swiping Left
            {
                translationX = -Math.min(-dX, height * 2);
                paint.setColor(Color.RED);
                RectF background = new RectF((float) itemView.getRight() + translationX, (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom());
                c.drawRect(background, paint);

                paint.setColor(Color.WHITE);
                paint.setTextSize(50);
                paint.setTypeface(Typeface.DEFAULT_BOLD);
                paint.setTextAlign(Paint.Align.LEFT);
                Rect titleBounds = new Rect();
                String title = "Delete";
                paint.getTextBounds(title, 0, title.length(), titleBounds);

                double y = background.height() / 2 + titleBounds.height() / 2 - titleBounds.bottom;
                c.drawText(title, background.left + 80, (float) (background.top + y), paint);
                //viewHolder.ItemView.TranslationX = translationX;
            } else if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE && dX > 0) // Swiping Right
            {
                translationX = Math.min(dX, height * 2);
                paint.setColor(Color.RED);

                RectF background = new RectF((float) itemView.getRight() + translationX, (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom());
                c.drawRect(background, paint);

                paint.setColor(Color.WHITE);
                paint.setTextSize(50);
                paint.setTypeface(Typeface.DEFAULT_BOLD);
                paint.setTextAlign(Paint.Align.LEFT);
                Rect titleBounds = new Rect();
                String title = "Delete";
                paint.getTextBounds(title, 0, title.length(), titleBounds);

                double y = background.height() / 2 + background.height() / 2 - titleBounds.bottom;
                c.drawText(title, background.left + 50, (float) (background.top + y), paint);


            }

            super.onChildDraw(c, recyclerView, viewHolder, translationX, dY, actionState, isCurrentlyActive);

        }
    };

    private void setControl() {
        editTextSearchKho = findViewById(R.id.editTextSearchKho);
        recyclerViewKho = findViewById(R.id.recyclerViewKho);
        floatingActionButtonKho = findViewById(R.id.floatingActionButtonKho);
    }

    public void dialogInsert() {

        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_themkho);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        EditText editMaKho = dialog.findViewById(R.id.editMaKho);
        EditText editTenKho = dialog.findViewById(R.id.editTenKho);
        TextView btnThem = dialog.findViewById(R.id.btnThemKho);
        TextView btnHuy = dialog.findViewById(R.id.btnHuyKho);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maKho = String.valueOf(editMaKho.getText());
                String tenKho = String.valueOf(editTenKho.getText());
                if (TextUtils.isEmpty(maKho) || TextUtils.isEmpty(tenKho)) {
                    Toast.makeText(KhoActivity.this, "Nội dung cần thêm chưa được nhập", Toast.LENGTH_SHORT).show();

                    return;
                }
                //kiem tra trung
                Cursor dataKho = nhapKhoHelper.GetData("SELECT * FROM Kho");
                ArrayList<Kho> arrayKho = new ArrayList<Kho>();
                Kho khoTam;
                while (dataKho.moveToNext()) {
                    khoTam = new Kho(dataKho.getString(0), dataKho.getString(1));
                    arrayKho.add(khoTam);
                }

                for (int i = 0; i < arrayKho.size(); i++) {
                    if (maKho == arrayKho.get(i).getMaKho()) {
                        Toast.makeText(KhoActivity.this, "Mã kho bị trùng", Toast.LENGTH_SHORT).show();

                        return;
                    }
                }
                for (int i = 0; i < arrayKho.size(); i++) {
                    if (tenKho == arrayKho.get(i).getTenKho()) {
                        Toast.makeText(KhoActivity.this, "Tên kho bị trùng", Toast.LENGTH_SHORT).show();

                        return;
                    }
                }


                nhapKhoHelper.QueryData("INSERT INTO Kho VALUES ('" + maKho + "','" + tenKho + "')");
                dialog.dismiss();
                actionGetData();

            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }

    public void dialogUpdate(String maKho, String tenKho) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_suakho);
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        //anh xa
        EditText editSuaMaKho = dialog.findViewById(R.id.editSuaMaKho);
        EditText editSuaTenKho = dialog.findViewById(R.id.editSuaTenKho);

        TextView btnSuaKho = dialog.findViewById(R.id.btnHuyKho);
        TextView btnXoaKho = dialog.findViewById(R.id.btnCapNhat);


        //set du lieu
        editSuaMaKho.setText(maKho);
        editSuaTenKho.setText(tenKho);


        //bat su kien nut bam
        btnSuaKho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String TenKhoMoi = String.valueOf(editSuaTenKho.getText());
                if (TextUtils.isEmpty(TenKhoMoi)) {
                    Toast.makeText(KhoActivity.this, "Nội dung cần sửa chưa được nhập", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    return;
                }
                nhapKhoHelper.QueryData("UPDATE Kho SET TenKho='" + TenKhoMoi + "' WHERE MaKho='" + maKho + "'");
                dialog.dismiss();
                actionGetData();
            }
        });

        btnXoaKho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Kiem tra kho co phieu nhap khong
                //kiem tra trung
                Cursor data = nhapKhoHelper.GetData("SELECT * FROM PhieuNhap WHERE MaKho='" + maKho + "'");
                while (data.moveToNext()) {
                    Toast.makeText(KhoActivity.this, "Kho có phiếu nhập không thể xóa", Toast.LENGTH_SHORT).show();

                    return;
                }

                nhapKhoHelper.QueryData("DELETE FROM Kho WHERE MaKho='" + maKho + "'");
                dialog.dismiss();
                actionGetData();
            }
        });

        dialog.show();
    }

    private void actionGetData() {
        arrayKho.clear();
        String searchKho = editTextSearchKho.getText().toString();
        Cursor dataKho;
        if (searchKho != "") {
            dataKho = nhapKhoHelper.GetData("SELECT * FROM Kho WHERE TenKho LIKE '%" + searchKho + "%' OR MaKho LIKE '%" + searchKho + "%'");
        } else {
            dataKho = nhapKhoHelper.GetData("SELECT * FROM Kho");
        }

        while (dataKho.moveToNext()) {
            kho = new Kho(dataKho.getString(0), dataKho.getString(1));
            arrayKho.add(kho);
        }

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerViewKho);

        recyclerViewKho.setHasFixedSize(true);
        recyclerViewKho.setLayoutManager(new LinearLayoutManager(KhoActivity.this, LinearLayoutManager.VERTICAL, false));

        khoAdapter = new KhoAdapter(KhoActivity.this, arrayKho);
        recyclerViewKho.setAdapter(khoAdapter);
    }

    public void btnClick(View view) {
        onBackPressed();
    }

}