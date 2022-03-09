package com.nhom2.qly_nhap_kho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.nhom2.qly_nhap_kho.adapter.TableViewAdapter;
import com.nhom2.qly_nhap_kho.model.MovieModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChiTietPhieuNhapActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_phieu_nhap);


        RecyclerView recyclerView = findViewById(R.id.recyclerViewDeliveryProductList);

        TableViewAdapter adapter = new TableViewAdapter(getMovieList());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapter);

    }


    private List getMovieList() {
        List movieList = new ArrayList<>();

        movieList.add(new MovieModel(1, "Pirates of the Caribbean: On Stranger Tides", 2011, 378));
        movieList.add(new MovieModel(2, "Avengers: Age of Ultron", 2015, 365));
        movieList.add(new MovieModel(3, "Avengers: Infinity War", 2018, 316));
        movieList.add(new MovieModel(4, "Pirates of the Caribbean: At World's End", 2007, 300));
        movieList.add(new MovieModel(5, "Justice League", 2017, 300));
        movieList.add(new MovieModel(6, "Solo: A Star Wars Story", 2018, 275));
        movieList.add(new MovieModel(7, "John Carter", 2012, 264));
        movieList.add(new MovieModel(8, "Batman v Superman: Dawn of Justice", 2016, 263));
        movieList.add(new MovieModel(9, "Star Wars: The Last Jedi", 2017, 263));
        movieList.add(new MovieModel(10, "Tangled", 2010, 260));

        return movieList;
    }
}