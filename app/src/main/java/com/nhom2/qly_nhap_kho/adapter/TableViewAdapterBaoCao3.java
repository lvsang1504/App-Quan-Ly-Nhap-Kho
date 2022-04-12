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
import com.nhom2.qly_nhap_kho.dao.TableDataBaoCao3;

import java.util.List;

public class TableViewAdapterBaoCao3 extends RecyclerView.Adapter {

    private List<TableDataBaoCao3> list;

    public TableViewAdapterBaoCao3(List<TableDataBaoCao3> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.table_list_item_bao_cao3, parent, false);

        return new RowViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RowViewHolder rowViewHolder = (RowViewHolder) holder;
        TextView tvSoPhieu = rowViewHolder.tvSoPhieu;
        TextView tvNgayLap = rowViewHolder.tvNgayLap;
        TextView tvTenKho = rowViewHolder.tvTenKho;

        int rowPos = rowViewHolder.getAdapterPosition();
        int resid;

        if (rowPos == 0) {
            resid = R.drawable.table_header_cell_bg;

            setColumnHeaderProperties(tvSoPhieu, "Số phiếu", resid);
            setColumnHeaderProperties(tvNgayLap, "Ngày lập", resid);
            setColumnHeaderProperties(tvTenKho, "Tên kho", resid);
        } else {
            TableDataBaoCao3 modal = list.get(rowPos - 1);
            resid = R.drawable.table_content_cell_bg;

            setRowProperties(tvSoPhieu, modal.getSoPhieu() + "", resid);
            setRowProperties(tvNgayLap, modal.getNgayLap() + "", resid);
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
        TextView tvSoPhieu;
        TextView tvNgayLap;
        TextView tvTenKho;

        RowViewHolder(View itemView) {
            super(itemView);
            tvSoPhieu = itemView.findViewById(R.id.txtBaoCao3SoPhieu);
            tvNgayLap = itemView.findViewById(R.id.txtBaoCao3NgayLap);
            tvTenKho = itemView.findViewById(R.id.txtBaoCao3TenKho);
        }
    }
}
