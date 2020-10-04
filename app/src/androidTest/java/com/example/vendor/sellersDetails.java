package com.example.vendor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class sellersDetails extends AppCompatActivity implements View.OnClickListener {



    private TextView payment , yrs;

    Button button;
    Button buttonDisplay;
    private double pymnt, ptot, price, rate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellers_details);

        this.setTitle("Seller details");

        button = findViewById(R.id.button7);

        payment = (Button) findViewById(R.id.);
        payment.setOnClickListener(this);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ViewMore.class);
                startActivity(i);
            }
        });

//        button = findViewById(R.id.buttonDisplay);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent k = new Intent(getApplicationContext(), Display.class);
//                startActivity(k);
//            }
//        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.payment:
                calPayment();
                startActivity(new Intent(this,Payment.class));
                break;
        }
    }

    public double calPayment(){

        if (yrs.equals(1)  || ptot<10){
            rate = 10;
        }
        if (yrs.equals(1)  || ptot<10 && ptot<20){
            rate = 20;
        }
        if (yrs.equals(2)  || ptot<10){
            rate = 30;
        }
        if (yrs.equals(2)  || ptot<10 && ptot<20){
            rate = 35;
        }
	    else{
            rate = 45;
        }


        pymnt = (rate/100) * price;




        return pymnt;
    }
}