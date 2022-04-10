package com.nhom2.qly_nhap_kho.adapter;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom2.qly_nhap_kho.R;
import com.nhom2.qly_nhap_kho.dao.TableDataBaoCao1;
import com.nhom2.qly_nhap_kho.dao.TableDataBaoCao2;

import java.util.List;

public class TableViewAdapterBaoCao2 extends RecyclerView.Adapter {

    private List<TableDataBaoCao2> list;

    public TableViewAdapterBaoCao2(List<TableDataBaoCao2> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.table_list_item_bao_cao2, parent, false);

        return new RowViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RowViewHolder rowViewHolder = (RowViewHolder) holder;
        TextView tvMaKho = rowViewHolder.tvMaKho;
        TextView tvTenKho = rowViewHolder.tvTenKho;
        int rowPos = rowViewHolder.getAdapterPosition();

        int resid;
        if (rowPos == 0) {
            resid = R.drawable.table_header_cell_bg;
            setColumnHeaderProperties(tvMaKho, "Mã Kho", resid);
            setColumnHeaderProperties(tvTenKho, "Tên Kho", resid);
        } else {
            TableDataBaoCao2 modal = list.get(rowPos - 1);
            resid = R.drawable.table_content_cell_bg;

            setRowProperties(tvMaKho, modal.getMaKho(), resid);
            setRowProperties(tvTenKho, modal.getTenKho(), resid);
        }
    }

    public static void setColumnHeaderProperties(TextView textView, String text, int resid) {
        textView.setBackgroundResource(resid);
        textView.setTextColor(Color.WHITE);
        textView.setGravity(Gravity.CENTER);
        textView.setText(text);
    }

    public static void setRowProperties(TextView textView, String text, int resid) {
        textView.setBackgroundResource(resid);
        textView.setText(text);
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    public class RowViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaKho;
        TextView tvTenKho;

        RowViewHolder(View itemView) {
            super(itemView);
            tvMaKho = itemView.findViewById(R.id.txtBaoCao2MaKho);
            tvTenKho = itemView.findViewById(R.id.txtBaoCao2TenKho);
        }
    }
}
