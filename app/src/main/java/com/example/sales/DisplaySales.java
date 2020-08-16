package com.example.sales;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DisplaySales extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_sales);

        this.setTitle("Mega sale :)");
    }
}