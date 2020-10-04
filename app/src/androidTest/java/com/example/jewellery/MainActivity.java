package com.example.jewellery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button button;
    TextView amount, discount, totPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totPrice = findViewById(R.id.textView4);
        amount = findViewById(R.id.textView);
        discount = findViewById(R.id.textView2);

        this.setTitle("Single Product");

        button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });
    }

    public void openActivity2() {
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }

    public void calculateTotalPrice(View view){
        double n1, n2, totalPrice;
        n1 = Double.parseDouble(amount.getText().toString());
        n2 = Double.parseDouble(discount.getText().toString());

        totalPrice = n1 - (n1 - n1* (n2 / 100));

        totPrice.setText(String.valueOf(totalPrice));
    }
}