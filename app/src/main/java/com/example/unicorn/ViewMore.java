package com.example.unicorn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ViewMore extends AppCompatActivity {

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_more);

        this.setTitle("View more :)");

        button = findViewById(R.id.btnr1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

        button = findViewById(R.id.btnr2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog2();
            }
        });
    }

    private void openDialog() {
        com.example.order.Exdialog1 dg = new com.example.order.Exdialog1();
        dg.show(getSupportFragmentManager() , "Dialog");

    }


    private void openDialog2() {
        com.example.order.Exdialog2 dg2 = new com.example.order.Exdialog2();
        dg2.show(getSupportFragmentManager() , "Dialog");
    }
}