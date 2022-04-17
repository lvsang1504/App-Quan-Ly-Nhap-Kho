package com.nhom2.qly_nhap_kho;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;

import android.graphics.Color;
import android.util.Log;


import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.nhom2.qly_nhap_kho.dao.TableDataBaoCao3;

import java.util.ArrayList;

public class ChartActivity extends AppCompatActivity {
    // variable for our bar chart
    BarChart barChart;

    // variable for our bar data.
    BarData barData;

    // variable for our bar data set.
    BarDataSet barDataSet;

    // array list for storing entries.
    ArrayList barEntriesArrayList;

    ArrayList<String> labels=new ArrayList<>();

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
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));

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
//        barEntriesArrayList.add(new BarEntry(1f, 4));
//        barEntriesArrayList.add(new BarEntry(2f, 6));
//        barEntriesArrayList.add(new BarEntry(3f, 8));
//        barEntriesArrayList.add(new BarEntry(4f, 2));
//        barEntriesArrayList.add(new BarEntry(5f, 4));
//        barEntriesArrayList.add(new BarEntry(6f, 1));

        String query = "SELECT MaKho, COUNT(SoPhieu) FROM  PhieuNhap " +
                "WHERE NgayLap = DATE()" +
                "GROUP BY MaKho";

        Cursor cursor = nhapKhoHelper.GetData(query);
        float i = 0;

        while (cursor.moveToNext()) {

            Log.d("DDD", cursor.getInt(1) + "");
            Log.d("DDD0", cursor.getString(0) + "");


            barEntriesArrayList.add(new BarEntry(i, cursor.getInt(1)));
            labels.add(cursor.getString(0));
            i++;
        }

    }
}