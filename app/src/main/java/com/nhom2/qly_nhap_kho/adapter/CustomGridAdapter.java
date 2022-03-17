package com.nhom2.qly_nhap_kho.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.nhom2.qly_nhap_kho.R;
import com.nhom2.qly_nhap_kho.model.GridItem;

import java.util.List;

public class CustomGridAdapter extends BaseAdapter {
    private List<GridItem> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomGridAdapter(Context aContext, List<GridItem> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GridViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.grid_item_layout, null);
            holder = new GridViewHolder();
            holder.image_card = convertView.findViewById(R.id.image_card);
            holder.title_card = (TextView) convertView.findViewById(R.id.title_card);
            holder.cardView = convertView.findViewById(R.id.cardView);
            convertView.setTag(holder);
        } else {
            holder = (GridViewHolder) convertView.getTag();
        }

        GridItem gridItem = this.listData.get(position);
        holder.title_card.setText(gridItem.title);

        int imageId = this.getMipmapResIdByName(gridItem.image);

        holder.image_card.setImageResource(imageId);

        return convertView;
    }

    public int getMipmapResIdByName(String resName) {
        String pkgName = context.getPackageName();

        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName, "drawable", pkgName);
        Log.i("CustomGridView", "Res Name: " + resName + "==> Res ID = " + resID);
        return resID;
    }
}

class GridViewHolder {
    ImageView image_card;
    TextView title_card;
    CardView cardView;
}