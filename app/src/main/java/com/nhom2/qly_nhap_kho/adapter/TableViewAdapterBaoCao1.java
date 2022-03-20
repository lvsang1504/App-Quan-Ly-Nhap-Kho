package com.nhom2.qly_nhap_kho.adapter;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom2.qly_nhap_kho.R;
import com.nhom2.qly_nhap_kho.dao.TableData;
import com.nhom2.qly_nhap_kho.dao.TableDataBaoCao1;

import java.util.List;

/**
 * Created by Azhar Rivaldi on 03/11/2019.
 */

public class TableViewAdapterBaoCao1 extends RecyclerView.Adapter {

    private List<TableDataBaoCao1> list;

    public TableViewAdapterBaoCao1(List<TableDataBaoCao1> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.table_list_item_bao_cao1, parent, false);

        return new RowViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RowViewHolder rowViewHolder = (RowViewHolder) holder;

        int rowPos = rowViewHolder.getAdapterPosition();

        if (rowPos == 0) {

            rowViewHolder.txtBaoCao1MaKho.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtBaoCao1TenKho.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtBaoCao1SoLoaiVatTu.setBackgroundResource(R.drawable.table_header_cell_bg);

            rowViewHolder.txtBaoCao1MaKho.setText("Mã Kho");
            rowViewHolder.txtBaoCao1TenKho.setText("Tên Kho");
            rowViewHolder.txtBaoCao1SoLoaiVatTu.setText("Số loại vật tư");
        } else {
            TableDataBaoCao1 modal = list.get(rowPos - 1);

            rowViewHolder.txtBaoCao1MaKho.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtBaoCao1TenKho.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtBaoCao1SoLoaiVatTu.setBackgroundResource(R.drawable.table_content_cell_bg);

            rowViewHolder.txtBaoCao1MaKho.setText(modal.maKho);
            rowViewHolder.txtBaoCao1TenKho.setText(modal.tenKho);
            rowViewHolder.txtBaoCao1SoLoaiVatTu.setText(modal.soLoaiVatTu + "");
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    public class RowViewHolder extends RecyclerView.ViewHolder {
        TextView txtBaoCao1MaKho;
        TextView txtBaoCao1TenKho;
        TextView txtBaoCao1SoLoaiVatTu;

        RowViewHolder(View itemView) {
            super(itemView);
            txtBaoCao1MaKho = itemView.findViewById(R.id.txtBaoCao1MaKho);
            txtBaoCao1TenKho = itemView.findViewById(R.id.txtBaoCao1TenKho);
            txtBaoCao1SoLoaiVatTu = itemView.findViewById(R.id.txtBaoCao1SoLoaiVatTu);
        }
    }
}
