package com.nhom2.qly_nhap_kho.adapter;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom2.qly_nhap_kho.MainActivity;
import com.nhom2.qly_nhap_kho.R;
import com.nhom2.qly_nhap_kho.listener.PhieuNhapListener;
import com.nhom2.qly_nhap_kho.model.Kho;
import com.nhom2.qly_nhap_kho.model.PhieuNhap;

import java.util.List;

public class PhieuNhapAdapter extends RecyclerView.Adapter<PhieuNhapViewHolder> {

    MainActivity context;
    List<PhieuNhap> list;
    PhieuNhapListener listener;

    public PhieuNhapAdapter(MainActivity context, List<PhieuNhap> list, PhieuNhapListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PhieuNhapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PhieuNhapViewHolder(LayoutInflater.from(context).inflate(R.layout.list_phieu_nhap, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PhieuNhapViewHolder holder, int position) {
        holder.txtName.setText("Phiếu nhập số: " + list.get(position).SoPhieu);
        holder.txtMa.setText("Mã kho: " + list.get(position).MaKho);
        holder.txtNgay.setText("Ngày lập: " + list.get(position).NgayLap);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ddd", String.valueOf(list.get(holder.getAdapterPosition()).SoPhieu));
                listener.onPhieuNhapClicked(String.valueOf(list.get(holder.getAdapterPosition()).SoPhieu), list.get(holder.getAdapterPosition()));
            }
        });
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                context.dialogUpdate(Integer.parseInt(String.valueOf(list.get(holder.getAdapterPosition()).SoPhieu)),
                        String.valueOf(list.get(holder.getAdapterPosition()).NgayLap),
                        String.valueOf(list.get(holder.getAdapterPosition()).MaKho));

                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class PhieuNhapViewHolder extends RecyclerView.ViewHolder {

    TextView txtName, txtMa, txtNgay;
    CardView cardView;

    public PhieuNhapViewHolder(@NonNull View itemView) {
        super(itemView);

        txtName = itemView.findViewById(R.id.txtName);
        txtMa = itemView.findViewById(R.id.txtMa);
        txtNgay = itemView.findViewById(R.id.txtNgay);
        cardView = itemView.findViewById(R.id.cardView);
    }
}
