package com.nhom2.qly_nhap_kho.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.nhom2.qly_nhap_kho.model.Kho;

import java.util.List;

public class KhoAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Kho> khoList;

    public KhoAdapter(Context context, int layout, List<Kho> khoList) {
        this.context = context;
        this.layout = layout;
        this.khoList = khoList;
    }

//    private class ViewHolder()

    @Override
    public int getCount() {
        return khoList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
