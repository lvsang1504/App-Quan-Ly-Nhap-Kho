package com.nhom2.qly_nhap_kho.adapter;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom2.qly_nhap_kho.R;
import com.nhom2.qly_nhap_kho.dao.TableDataBaoCao4;
import com.nhom2.qly_nhap_kho.model.Kho;

import java.util.List;

public class TableViewAdapterBaoCao4 extends RecyclerView.Adapter<TableViewAdapterBaoCao4.ViewHolder> {

    private List<TableDataBaoCao4> list;
    private LinearLayout layout;
    private List<Kho> khos;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final LinearLayout linearLayout;

        ViewHolder(View view) {
            super(view);
            linearLayout = (LinearLayout) view.findViewById(R.id.list_item_linear_layout);
        }

        public LinearLayout getLinearLayout() {
             return linearLayout;
        }
    }

    public TableViewAdapterBaoCao4(List<TableDataBaoCao4> list, LinearLayout layout, List<Kho> khos) {
        this.list = list;
        this.layout = layout;
        this.khos = khos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.table_list_item_bao_cao4, viewGroup, false);

        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        int resid = 0;

        LinearLayout myLayout = viewHolder.getLinearLayout();
        myLayout.setWeightSum(9 + khos.size()*3);

        if (position == 0) {
            resid = R.drawable.table_header_cell_bg;

            TextView tvMAVT = new TextView(myLayout.getContext());
            setColumnHeaderProperties(tvMAVT, "Mã VT", resid);

            TextView tvTenVT = new TextView(myLayout.getContext());
            setColumnHeaderProperties(tvTenVT, "Tên VT", resid);

            TextView tvDVT = new TextView(myLayout.getContext());
            setColumnHeaderProperties(tvDVT, "ĐVT", resid);

            myLayout.addView(tvMAVT);
            myLayout.addView(tvTenVT);
            myLayout.addView(tvDVT);

            for (Kho kho : khos) {
                TextView tvKho = new TextView(myLayout.getContext());
                setColumnHeaderProperties(tvKho, kho.getTenKho(), resid);
                myLayout.addView(tvKho);
            }

        } else {
            TableDataBaoCao4 modal = list.get(position - 1);
            resid = R.drawable.table_content_cell_bg;

            TextView tvMAVT = new TextView(myLayout.getContext());
            setRowProperties(tvMAVT, modal.getMaVT(), resid);

            TextView tvTenVT = new TextView(myLayout.getContext());
            setRowProperties(tvTenVT, modal.getTenVT(), resid);

            TextView tvDVT = new TextView(myLayout.getContext());
            setRowProperties(tvDVT, modal.getDVT(), resid);
            myLayout.addView(tvMAVT);
            myLayout.addView(tvTenVT);
            myLayout.addView(tvDVT);

            for (Integer khoData : modal.getDanhSachKho()) {
                TextView tvTenKho = new TextView(myLayout.getContext());
                setRowProperties(tvTenKho, khoData.toString(), resid);
                myLayout.addView(tvTenKho);
            }
        }
    }

    public static void setColumnHeaderProperties(TextView textView, String text, int resid) {
        textView.setBackgroundResource(resid);
        textView.setTextColor(Color.WHITE);
        textView.setGravity(Gravity.CENTER);
        textView.setText(text);

        commonProperties(textView);
//        textView.setTextSize(16);
    }

    private static void commonProperties(TextView textView) {
        textView.setPadding(4,4,4,4);
        textView.setGravity(Gravity.CENTER);
        textView.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT,3));
        textView.setTextSize(18);
        textView.setMinLines(1);
    }

    public static void setRowProperties(TextView textView, String text, int resid) {
        textView.setBackgroundResource(resid);
        textView.setText(text);
        commonProperties(textView);
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }
}
