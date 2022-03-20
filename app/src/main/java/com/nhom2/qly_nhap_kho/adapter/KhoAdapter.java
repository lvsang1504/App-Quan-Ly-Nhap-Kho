package com.nhom2.qly_nhap_kho.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom2.qly_nhap_kho.KhoActivity;
import com.nhom2.qly_nhap_kho.R;
import com.nhom2.qly_nhap_kho.VatTuActivity;
import com.nhom2.qly_nhap_kho.model.Kho;
import com.nhom2.qly_nhap_kho.model.VatTu;

import java.util.List;

public class KhoAdapter extends RecyclerView.Adapter<KhoViewHolder>  {
    KhoActivity context;
    List<Kho> list;

    public KhoAdapter(KhoActivity context, List<Kho> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public KhoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new KhoViewHolder(LayoutInflater.from(context).inflate(R.layout.list_kho, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull KhoViewHolder holder, int position) {
        holder.txtMaKho.setText( list.get(position).getMaKho());
        holder.txtTenKho.setText( list.get(position).getTenKho());
        holder.cardViewKho.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                context.dialogUpdate(
                        String.valueOf(list.get(holder.getAdapterPosition()).getMaKho()),
                        String.valueOf(list.get(holder.getAdapterPosition()).getTenKho()));

                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class KhoViewHolder extends RecyclerView.ViewHolder {

    TextView txtMaKho, txtTenKho;
    CardView cardViewKho;

    public KhoViewHolder(@NonNull View itemView) {
        super(itemView);

        txtMaKho = itemView.findViewById(R.id.txtMaKho);
        txtTenKho = itemView.findViewById(R.id.txtTenKho);
        cardViewKho = itemView.findViewById(R.id.cardViewKho);
    }
}