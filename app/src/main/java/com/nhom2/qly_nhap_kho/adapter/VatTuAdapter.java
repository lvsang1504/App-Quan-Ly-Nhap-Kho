package com.nhom2.qly_nhap_kho.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom2.qly_nhap_kho.R;
import com.nhom2.qly_nhap_kho.VatTuActivity;
import com.nhom2.qly_nhap_kho.model.VatTu;

import java.util.List;

public class VatTuAdapter extends RecyclerView.Adapter<VatTuViewHolder> {
    VatTuActivity context;
    List<VatTu> list;

    public VatTuAdapter(VatTuActivity context, List<VatTu> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public VatTuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VatTuViewHolder(LayoutInflater.from(context).inflate(R.layout.list_vat_tu, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VatTuViewHolder holder, int position) {
        holder.txtMaVatTu.setText( list.get(position).MaVT);
        holder.txtTenVatTu.setText( list.get(position).TenVt);
        holder.txtXuatXu.setText( list.get(position).XuatXu);
        holder.cardViewVatTu.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                context.dialogUpdate(
                        String.valueOf(list.get(holder.getAdapterPosition()).getMaVT()),
                        String.valueOf(list.get(holder.getAdapterPosition()).getTenVt()),
                        String.valueOf(list.get(holder.getAdapterPosition()).getXuatXu()));

                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class VatTuViewHolder extends RecyclerView.ViewHolder {

    TextView txtMaVatTu, txtTenVatTu, txtXuatXu;
    CardView cardViewVatTu;

    public VatTuViewHolder(@NonNull View itemView) {
        super(itemView);

        txtMaVatTu = itemView.findViewById(R.id.txtMaVatTu);
        txtTenVatTu = itemView.findViewById(R.id.txtTenVatTu);
        txtXuatXu = itemView.findViewById(R.id.txtXuatXu);
        cardViewVatTu = itemView.findViewById(R.id.cardViewVatTu);
    }
}