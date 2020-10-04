package com.example.newcus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class search<button> extends AppCompatActivity implements View.OnClickListener {


    private TextView yrs, rewards;
    private double rew, price, point;

    button button3, button2, button4, button5, button7;
    DatabaseReference dbRef;
    private Object button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
//        button = (Button) findViewById(R.id.button2):

        rewards = (Button) findViewById(R.id.button7);
        rewards.setOnClickListener(this);



//
//        button3.setOnClickListener((view) -> {
//            DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
//            dbRef.removeValue();
//            Toast.makeText(getApplicationContext(),"Successfully deleted",Toast.LENGTH_SHORT).show();
//        button3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                button = findViewById(R.id.button);
//
//
//                button2.setOnClickListener((view) ->{
//                    dbRef = FirebaseDatabase.getInstance().getReference();
//                    dbRef.child("Customer").child("cus1").child("points").setValue(button2.toString().trim());
//
//                    Toast.makeText(getApplicationContext(),"Successfully updated", Toast.LENGTH_SHORT).show();
//                    clearControls();
//
//
//                    button.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent i = new Intent(getApplicationContext(), search.class);
//
//                        startActivity(i);
//                    }
//
//                });
//
//            }
//
//    }
//});
//
//});
//
//    }
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button7:
                calPayment();
                startActivity(new Intent(this,reward.class));
                break;
        }
    }

    public double calPayment(){
        if (yrs.equals(1)){
            point = 0.1;
        }
        if (yrs.equals(2)){
            point = 0.3;
        }
        if (yrs.equals(3)) {
            point = 0.5;
        }
        else{
            point = 0.8;
        }
            rew = price * point;

            return rew;
        }
//    private void clearControls() {
//        txtID.setText("");
//        txtName.setText("");
//        txtAdd.setText("");
//        txtConNo.setText("");
//    }
}