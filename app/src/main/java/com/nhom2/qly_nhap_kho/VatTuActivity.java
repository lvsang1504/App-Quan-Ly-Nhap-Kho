package com.nhom2.qly_nhap_kho;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
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
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nhom2.qly_nhap_kho.adapter.VatTuAdapter;
import com.nhom2.qly_nhap_kho.model.VatTu;

import java.util.ArrayList;
import java.util.List;

public class VatTuActivity extends AppCompatActivity {
    NhapKhoHelper nhapKhoHelper;
    VatTuAdapter vatTuAdapter;
    RecyclerView recyclerViewVatTu;
    List<VatTu> arrayVatTu = new ArrayList<>();
    VatTu vatTu;
    FloatingActionButton floatingActionButtonVatTu;
    EditText editTextSearchVatTu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vat_tu);
        nhapKhoHelper = NhapKhoHelper.getInstance(this);
        setControl();
        actionGetData();
        setEvent();
    }
    private void setEvent() {
        floatingActionButtonVatTu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogInsert();
            }
        });
        editTextSearchVatTu.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                System.out.println(keyEvent.getKeyCode());
                actionGetData();
                return false;
            }
        });
    }
    private void setControl() {
        recyclerViewVatTu = findViewById(R.id.recyclerViewVatTu);
        floatingActionButtonVatTu=findViewById(R.id.floatingActionButtonVatTu);
        editTextSearchVatTu=findViewById(R.id.editTextSearchVatTu);
    }

    public void dialogInsert() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_themvattu);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        EditText editMaVT = dialog.findViewById(R.id.editMaVatTu);
        EditText editTenVT = dialog.findViewById(R.id.editTenVatTu);
        EditText editXuatXu = dialog.findViewById(R.id.editXuatXu);
        TextView btnThem = dialog.findViewById(R.id.btnThemVatTu);
        TextView btnHuy = dialog.findViewById(R.id.btnHuyVatTu);


        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maVT = String.valueOf(editMaVT.getText());
                String tenVT = String.valueOf(editTenVT.getText());
                String xuatXu = String.valueOf(editXuatXu.getText());
                if (TextUtils.isEmpty(maVT) || TextUtils.isEmpty(tenVT) || TextUtils.isEmpty(xuatXu)) {
                    Toast.makeText(VatTuActivity.this, "Nội dung cần thêm chưa được nhập", Toast.LENGTH_SHORT).show();

                    return;
                }
                //kiem tra trung
                Cursor dataVatTu = nhapKhoHelper.GetData("SELECT * FROM VatTu");
                ArrayList<VatTu> arrayVatTu = new ArrayList<VatTu>();
                VatTu vatTuTam;
                while (dataVatTu.moveToNext()) {
                    vatTuTam = new VatTu(dataVatTu.getString(0), dataVatTu.getString(1),dataVatTu.getString(2));
                    arrayVatTu.add(vatTuTam);
                }

                for (int i = 0; i < arrayVatTu.size(); i++) {
                    if(maVT.equals(arrayVatTu.get(i).getMaVT())){
                        Toast.makeText(VatTuActivity.this, "Mã vật tư bị trùng", Toast.LENGTH_SHORT).show();

                        return;
                    }
                }
                for (int i = 0; i < arrayVatTu.size(); i++) {
                    if(tenVT==arrayVatTu.get(i).getTenVt()){
                        Toast.makeText(VatTuActivity.this, "Tên vật tư bị trùng", Toast.LENGTH_SHORT).show();

                        return;
                    }
                }


                nhapKhoHelper.QueryData("INSERT INTO VatTu VALUES ('" + maVT + "','" + tenVT + "', '" + xuatXu + "')");
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
    public void dialogUpdate(String maVT, String tenVT, String xuatXu) {

        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_suavattu);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        //anh xa
        EditText editSuaMaVatTu = dialog.findViewById(R.id.editSuaMaVatTu);
        EditText editSuaTenVatTu = dialog.findViewById(R.id.editSuaTenVatTu);
        EditText editSuaXuatXu = dialog.findViewById(R.id.editSuaXuatXu);

        TextView btnSuaVatTu = dialog.findViewById(R.id.btnSuaVatTu);
        TextView btnXoaVatTu = dialog.findViewById(R.id.btnXoaVatTu);


        //set du lieu
        editSuaMaVatTu.setText(maVT);
        editSuaTenVatTu.setText(tenVT);
        editSuaXuatXu.setText(xuatXu);


        //bat su kien nut bam
        btnSuaVatTu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String TenVatTuMoi = String.valueOf(editSuaTenVatTu.getText());
                String XuatXuMoi = String.valueOf(editSuaXuatXu.getText());
                if (TextUtils.isEmpty(TenVatTuMoi) || TextUtils.isEmpty(XuatXuMoi)) {
                    Toast.makeText(VatTuActivity.this, "Nội dung cần sửa chưa được nhập", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    return;
                }
                nhapKhoHelper.QueryData("UPDATE VatTu SET TenVT='" + TenVatTuMoi + "',XuatXu='" + XuatXuMoi + "' WHERE MaVT='" + maVT + "'");
                dialog.dismiss();
                actionGetData();
            }
        });

        btnXoaVatTu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor data = nhapKhoHelper.GetData("SELECT * FROM ChiTietPhieuNhap WHERE MaVT='" + maVT + "'");
                while (data.moveToNext()) {
                    Toast.makeText(VatTuActivity.this, "Vật tư đã có trong chi tiết không thể xóa", Toast.LENGTH_SHORT).show();
                    return;
                }
                nhapKhoHelper.QueryData("DELETE FROM VatTu WHERE MaVT='" + maVT + "'");
                dialog.dismiss();
                actionGetData();
            }
        });

        dialog.show();
    }

    ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT ) {
        private final Paint paint = new Paint();
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            Toast.makeText(VatTuActivity.this, "on Move", Toast.LENGTH_SHORT).show();
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
            int position = viewHolder.getAdapterPosition();
            Cursor data = nhapKhoHelper.GetData("SELECT * FROM ChiTietPhieuNhap WHERE MaVT='" + arrayVatTu.get(position).getMaVT() + "'");
            while (data.moveToNext()) {
                Toast.makeText(VatTuActivity.this, "Vật tư đã có trong chi tiết không thể xóa", Toast.LENGTH_SHORT).show();
                vatTuAdapter.notifyDataSetChanged();
                return;
            }
            //Remove swiped item from list and notify the RecyclerView
            nhapKhoHelper.QueryData("DELETE FROM VatTu WHERE MaVT='" + arrayVatTu.get(position).getMaVT() + "'");
            arrayVatTu.remove(position);
            vatTuAdapter.notifyDataSetChanged();
            Toast.makeText(VatTuActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

            float translationX = dX;
            View itemView = viewHolder.itemView;
            float height = (float)itemView.getBottom() - (float)itemView.getTop();

            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE && dX <= 0) // Swiping Left
            {
                translationX = -Math.min(-dX, height * 2);
                paint.setColor(Color.RED);
                RectF background = new RectF((float)itemView.getRight() + translationX, (float)itemView.getTop(), (float)itemView.getRight(), (float)itemView.getBottom());
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
            }
            else if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE && dX > 0) // Swiping Right
            {
                translationX = Math.min(dX, height * 2);
                paint.setColor(Color.RED);

                RectF background = new RectF((float)itemView.getRight() + translationX, (float)itemView.getTop(), (float)itemView.getRight(), (float)itemView.getBottom());
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

    private void actionGetData(){
        arrayVatTu.clear();
        String searchVatTu=editTextSearchVatTu.getText().toString();
        Cursor dataVatTu;
        if(searchVatTu!=""){
            dataVatTu = nhapKhoHelper.GetData("SELECT * FROM VatTu WHERE TenVT LIKE '%"+searchVatTu+"%' OR MaVT LIKE '%"+searchVatTu+"%' OR XuatXu LIKE '%"+searchVatTu+"%'");
        }else{
            dataVatTu = nhapKhoHelper.GetData("SELECT * FROM VatTu");
        }

        while (dataVatTu.moveToNext()) {
            vatTu = new VatTu(dataVatTu.getString(0), dataVatTu.getString(1),dataVatTu.getString(2));
            arrayVatTu.add(vatTu);
        }

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerViewVatTu);

        recyclerViewVatTu.setHasFixedSize(true);
        recyclerViewVatTu.setLayoutManager(new LinearLayoutManager(VatTuActivity.this, LinearLayoutManager.VERTICAL, false));

        vatTuAdapter = new VatTuAdapter(VatTuActivity.this, arrayVatTu);
        recyclerViewVatTu.setAdapter(vatTuAdapter);
    }

    public void btnClick(View view) {
        onBackPressed();
    }
}