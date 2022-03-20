package com.nhom2.qly_nhap_kho.adapter;


import android.annotation.SuppressLint;
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

/**
 * Created by Azhar Rivaldi on 03/11/2019.
 */

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

        int rowPos = rowViewHolder.getAdapterPosition();

        if (rowPos == 0) {

            rowViewHolder.txtBaoCao2MaKho.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtBaoCao2TenKho.setBackgroundResource(R.drawable.table_header_cell_bg);

            rowViewHolder.txtBaoCao2MaKho.setText("Mã Kho");
            rowViewHolder.txtBaoCao2TenKho.setText("Tên Kho");
        } else {
            TableDataBaoCao2 modal = list.get(rowPos - 1);

            rowViewHolder.txtBaoCao2MaKho.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtBaoCao2TenKho.setBackgroundResource(R.drawable.table_content_cell_bg);

            rowViewHolder.txtBaoCao2MaKho.setText(modal.maKho);
            rowViewHolder.txtBaoCao2TenKho.setText(modal.tenKho);
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    public class RowViewHolder extends RecyclerView.ViewHolder {
        TextView txtBaoCao2MaKho;
        TextView txtBaoCao2TenKho;

        RowViewHolder(View itemView) {
            super(itemView);
            txtBaoCao2MaKho = itemView.findViewById(R.id.txtBaoCao2MaKho);
            txtBaoCao2TenKho = itemView.findViewById(R.id.txtBaoCao2TenKho);
        }
    }
}
