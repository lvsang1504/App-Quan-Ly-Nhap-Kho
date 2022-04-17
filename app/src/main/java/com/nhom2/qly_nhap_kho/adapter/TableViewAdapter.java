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

import java.util.List;

public class TableViewAdapter extends RecyclerView.Adapter {

    private List<TableData> list;

    public TableViewAdapter(List<TableData> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.table_list_item, parent, false);

        return new RowViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RowViewHolder rowViewHolder = (RowViewHolder) holder;

        int rowPos = rowViewHolder.getAdapterPosition();

        if (rowPos == 0) {

            rowViewHolder.txtMaVt.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtTenVT.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtDvt.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtSL.setBackgroundResource(R.drawable.table_header_cell_bg);

            rowViewHolder.txtMaVt.setText("Mã VT");
            rowViewHolder.txtTenVT.setText("Vat Tu");
            rowViewHolder.txtDvt.setText("Đơn vị tính");
            rowViewHolder.txtSL.setText("Số lượng");
        } else {
            TableData modal = list.get(rowPos - 1);

            rowViewHolder.txtMaVt.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtTenVT.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtDvt.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtSL.setBackgroundResource(R.drawable.table_content_cell_bg);

            rowViewHolder.txtMaVt.setText(modal.maVT + "");
            rowViewHolder.txtTenVT.setText(modal.tenVT+"");
            rowViewHolder.txtDvt.setText(modal.DVT + "");
            rowViewHolder.txtSL.setText(modal.soLuong + "");
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    public class RowViewHolder extends RecyclerView.ViewHolder {
        TextView txtMaVt;
        TextView txtTenVT;
        TextView txtDvt;
        TextView txtSL;

        RowViewHolder(View itemView) {
            super(itemView);
            txtMaVt = itemView.findViewById(R.id.txtRank);
            txtTenVT = itemView.findViewById(R.id.txtMovieName);
            txtDvt = itemView.findViewById(R.id.txtYear);
            txtSL = itemView.findViewById(R.id.txtCost);
        }
    }
}
