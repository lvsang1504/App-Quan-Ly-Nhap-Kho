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
import com.nhom2.qly_nhap_kho.dao.TableDataBaoCao3;

import java.util.List;

/**
 * Created by Azhar Rivaldi on 03/11/2019.
 */

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

        int rowPos = rowViewHolder.getAdapterPosition();

        if (rowPos == 0) {

            rowViewHolder.txtBaoCao3SoPhieu.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtBaoCao3NgayLap.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtBaoCao3TenKho.setBackgroundResource(R.drawable.table_header_cell_bg);

            rowViewHolder.txtBaoCao3SoPhieu.setText("Số phiếu");
            rowViewHolder.txtBaoCao3NgayLap.setText("Ngày lập");
            rowViewHolder.txtBaoCao3TenKho.setText("Tên kho");
        } else {
            TableDataBaoCao3 modal = list.get(rowPos - 1);

            rowViewHolder.txtBaoCao3SoPhieu.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtBaoCao3NgayLap.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtBaoCao3TenKho.setBackgroundResource(R.drawable.table_content_cell_bg);

            rowViewHolder.txtBaoCao3SoPhieu.setText(modal.soPhieu+"");
            rowViewHolder.txtBaoCao3NgayLap.setText(modal.ngayLap);
            rowViewHolder.txtBaoCao3TenKho.setText(modal.tenKho);
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    public class RowViewHolder extends RecyclerView.ViewHolder {
        TextView txtBaoCao3SoPhieu;
        TextView txtBaoCao3NgayLap;
        TextView txtBaoCao3TenKho;

        RowViewHolder(View itemView) {
            super(itemView);
            txtBaoCao3SoPhieu = itemView.findViewById(R.id.txtBaoCao3SoPhieu);
            txtBaoCao3NgayLap = itemView.findViewById(R.id.txtBaoCao3NgayLap);
            txtBaoCao3TenKho = itemView.findViewById(R.id.txtBaoCao3TenKho);
        }
    }
}
