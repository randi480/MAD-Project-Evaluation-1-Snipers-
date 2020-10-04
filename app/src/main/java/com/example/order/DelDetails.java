package com.example.order;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DelDetails extends AppCompatActivity{
    private TextView btnUpdate, btnOk;
    private FirebaseUser user;
    private DatabaseReference reference;

    private String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del_details);


//        btnOk.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getApplicationContext(), Check.class);
//                startActivity(i);
//            }
//        });

//        btnUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getApplicationContext(), Check.class);
//                startActivity(i);
//            }
//        });


        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Order");
        userId = user.getUid();


        final TextView name = (TextView) findViewById(R.id.displayName);
        final TextView address = (TextView) findViewById(R.id.displayAdd);
        final TextView dist = (TextView) findViewById(R.id.displayDist);
        final TextView email = (TextView) findViewById(R.id.displayMail);
        final TextView phone = (TextView) findViewById(R.id.displayPhn);


        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DelUser det = snapshot.getValue(DelUser.class);
                if (det != null) {
                    String vname = det.dame;
                    String vadd = det.dada;
                    String vmail = det.detail;
                    String vcontact = det.dphn;
                    String vdict = det.dist;

                    name.setText(vname);
                    address.setText(vadd);
                    dist.setText(vdict);
                    email.setText(vmail);
                    phone.setText(vcontact);


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(DelDetails.this, "Error in displaying Data", Toast.LENGTH_LONG).show();

            }
        });

    }
}

