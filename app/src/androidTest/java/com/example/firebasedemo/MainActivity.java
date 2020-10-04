package com.example.firebasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText txtID, txtName, txtAdd, txtConNo;
    Button butSave, butShow, butUpdate, butDelete;
    DatabaseReference dbRef;
    Student std;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtID = findViewById(R.id.EtInputID);
        txtName = findViewById(R.id.EtInputName);
        txtAdd = findViewById(R.id.EtInputAdd);
        txtConNo = findViewById(R.id.EtInputConNo);

        butSave = findViewById(R.id.ButSave);
        butShow = findViewById(R.id.ButShow);
        butUpdate = findViewById(R.id.ButUpdate);
        butDelete = findViewById(R.id.ButDelete);

        std = new Student();

        butDelete.setOnClickListener((view) -> {
            dbRef = FirebaseDatabase.getInstance().getReference().child("Student").child("Std2");
            dbRef.removeValue();
            Toast.makeText(getApplicationContext(), "successfully deleted", Toast.LENGTH_SHORT).show();
        });

        butUpdate.setOnClickListener((view) -> {
            dbRef = FirebaseDatabase.getInstance().getReference();
            dbRef.child("Student").child("Std1").child("name").setValue(txtName.getText().toString().trim());
            dbRef.child("Student/Std1/address").setValue(txtAdd.getText().toString().trim());
            Toast.makeText(getApplicationContext(), "successfully updated", Toast.LENGTH_SHORT).show();
            clearControls();
        });

        butShow.setOnClickListener((view) -> {
            dbRef = FirebaseDatabase.getInstance().getReference().child("Student/Std1");
            dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChildren()) {
                        txtID.setText(dataSnapshot.child("id").getValue().toString());
                        txtName.setText(dataSnapshot.child("name").getValue().toString());
                        txtAdd.setText(dataSnapshot.child("address").getValue().toString());
                        txtConNo.setText(dataSnapshot.child("conNo").getValue().toString());
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Cannot find std1", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        });

        butSave.setOnClickListener((view) -> {
            dbRef = FirebaseDatabase.getInstance().getReference().child("Student");
            //try {
                if (TextUtils.isEmpty(txtID.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Empty ID", Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(txtName.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Empty Name", Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(txtAdd.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Empty Address", Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(txtConNo.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Empty Contact No", Toast.LENGTH_SHORT).show();
                else {
                    std.setId(txtID.getText().toString().trim());
                    std.setName(txtName.getText().toString().trim());
                    std.setAdd(txtAdd.getText().toString().trim());
                    std.setConNo(Integer.parseInt(txtConNo.getText().toString().trim()));
                    dbRef.child("Std1").setValue(std);
                    Toast.makeText(getApplicationContext(), "Successfully Inserted", Toast.LENGTH_SHORT).show();
                    clearControls();
                }
            //} catch (NumberFormatException nfe) {
              //  Toast.makeText(getApplicationContext(), "Invalid Contact No", Toast.LENGTH_SHORT).show();
            //}
        });
    }

    private void clearControls() {
        txtID.setText("");
        txtName.setText("");
        txtAdd.setText("");
        txtConNo.setText("");
    }
}
