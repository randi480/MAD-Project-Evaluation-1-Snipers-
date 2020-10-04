package com.example.order;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class Delivery extends AppCompatActivity implements View.OnClickListener {
//    Button button;

    private FirebaseAuth mAuth;

    private TextView banner, save;
    private EditText name, address, phn, email, district;
    private double delFee, tot, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        // Initialize Firebase Auth00
        mAuth = FirebaseAuth.getInstance();

        this.setTitle("Delivery Info :) ");

        banner = findViewById(R.id.banner);
        banner.setOnClickListener(this);

        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(this);

        name = findViewById(R.id.EtName);
        address = findViewById(R.id.EtAddress);
        phn = findViewById(R.id.EtContact);
        email = findViewById(R.id.EtMail);
        district = findViewById(R.id.EtDist);



//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getApplicationContext(),Check.class);
//                startActivity(i);
//            }
//        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.banner:
                startActivity(new Intent(this,Check.class));
                save();
                break;
            case R.id.save:
                save();
                calPayment();
                startActivity(new Intent(this,DelDetails.class));
                break;
        }
    }



    public double calPayment(){
        if (district.equals("Colombo")  || district.equals("colombo")){
            delFee = 100.00;
        }
        if (district.equals("Kurunegala")  || district.equals("kurunegala")){
            delFee = 150.00;
        }
        if (district.equals("Negombo")  || district.equals("negombo")){
            delFee = 200.00;
        }

        else {
            delFee = 300.00;
        }

        tot = delFee + price;

        return tot;
    }

    private void save() {
        final String dname = name.getText().toString().trim();
        final String dadd = address.getText().toString().trim();
        final String dphn = phn.getText().toString().trim();
        final String demail = email.getText().toString().trim();
        final String ddist = district.getText().toString().trim();


        if (dname.isEmpty()){
            name.setError("Name can not be empty!");
            name.requestFocus();
            return;
        }
        if (dadd.isEmpty()){
            address.setError("Address can not be empty!");
            address.requestFocus();
            return;
        }
        if (ddist.isEmpty()){
            address.setError("District can not be empty!");
            address.requestFocus();
            return;
        }
        if (dphn.isEmpty()){
            phn.setError("Contact No can not be empty!");
            phn.requestFocus();
            return;
        }
        if (dphn.length() != 10){
            phn.setError("Please Enter Valid Phone No");
            phn.requestFocus();
            return;
        }
        if (demail.isEmpty()){
            email.setError("Email can not be empty!");
            email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(demail).matches()){
            email.setError("Email can not be empty!");
            email.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(demail, dphn)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            DelUser users = new DelUser(dname, dadd, demail , dphn , ddist);

                            FirebaseDatabase.getInstance().getReference("Order")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(Delivery.this, "Saved Successfully!", Toast.LENGTH_LONG).show();
                                    }
                                    else{
                                        Toast.makeText(Delivery.this, "Error!", Toast.LENGTH_LONG).show();
                                    }

                                }
                            });

                        }
                        else {
                            Toast.makeText(Delivery.this, "Error in Save!", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }


}