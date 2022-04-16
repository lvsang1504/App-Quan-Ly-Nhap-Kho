package com.nhom2.qly_nhap_kho;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;

import android.graphics.Color;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.nhom2.qly_nhap_kho.dao.TableDataBaoCao3;

import java.util.ArrayList;
import java.util.List;

public class ChartActivity extends AppCompatActivity {
    // variable for our bar chart
    BarChart barChart;

    // variable for our bar data.
    BarData barData;

    // variable for our bar data set.
    BarDataSet barDataSet;

    // array list for storing entries.
    ArrayList barEntriesArrayList;

    NhapKhoHelper nhapKhoHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        nhapKhoHelper = new NhapKhoHelper(this);
        // initializing variable for bar chart.
        barChart = findViewById(R.id.idBarChart);


        // calling method to get bar entries.
        getBarEntries();

        // creating a new bar data set.
        barDataSet = new BarDataSet(barEntriesArrayList, "");

        // creating a new bar data and
        // passing our bar data set.
        barData = new BarData(barDataSet);

        // below line is to set data
        // to our bar chart.
        barChart.setData(barData);

        // adding color to our bar data set.
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        // setting text color.
        barDataSet.setValueTextColor(Color.BLACK);

        // setting text size
        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(false);
    }
    private void getBarEntries() {
        // creating a new array list
        barEntriesArrayList = new ArrayList<>();

        // adding new entry to our array list with bar
        // entry and passing x and y axis value to it.
        barEntriesArrayList.add(new BarEntry(1f, 4));
        barEntriesArrayList.add(new BarEntry(2f, 6));
        barEntriesArrayList.add(new BarEntry(3f, 8));
        barEntriesArrayList.add(new BarEntry(4f, 2));
        barEntriesArrayList.add(new BarEntry(5f, 4));
        barEntriesArrayList.add(new BarEntry(6f, 1));

        String query = "SELECT  COUNT(pn.SoPhieu),pn.NgayLap,k.TenKho\n" +
                "                    FROM PhieuNhap pn, ChiTietPhieuNhap ctpn,Kho k\n" +
                "                    WHERE k.MaKho=pn.MaKho AND pn.SoPhieu=ctpn.SoPhieu AND ctpn.MaVT ='GO'\n" +
                "\n" +
                "                    INTERSECT\n" +
                "\n" +
                "                    SELECT distinct pn.SoPhieu,pn.NgayLap,k.TenKho\n" +
                "                    FROM PhieuNhap pn, ChiTietPhieuNhap ctpn,Kho k\n" +
                "                    WHERE k.MaKho=pn.MaKho AND pn.SoPhieu=ctpn.SoPhieu AND ctpn.MaVT ='GT'";

        Cursor cursor = nhapKhoHelper.GetData(query);

        while (cursor.moveToNext()) {
            list.add(new TableDataBaoCao3(
                    Integer.valueOf(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2)));
        }
        return list;
    }
}