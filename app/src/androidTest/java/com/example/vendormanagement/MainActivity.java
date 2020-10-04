package com.example.vendormanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnSave;
    EditText edtName;
    DatabaseReference databaseReference;
    ListView listViewUsers;
    List<User> users;
    public static  String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        users = new ArrayList<User>();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        btnSave = (Button) findViewById(R.id.btnSave);
        edtName = (EditText) findViewById(R.id.edtName);
        listViewUsers = (ListView) findViewById(R.id.listViewUsers);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();

                if (TextUtils.isEmpty(userId)){
                    //save
                    String id = databaseReference.push().getKey();
                    User user = new User(id, name);
                    databaseReference.child(id).setValue(user);

                    Toast.makeText(MainActivity.this, "User created successfully", Toast.LENGTH_LONG).show();
                }else {
                    //update
                    databaseReference.child(userId).child("name").setValue(name);

                    Toast.makeText(MainActivity.this, "User updated successfully", Toast.LENGTH_LONG).show();
                }

                edtName.setText(null);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                users.clear();

                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    User user = postSnapshot.getValue(User.class);
                }

                UserList userAdapter = new UserList(MainActivity.this, users, databaseReference, edtName);
                listViewUsers.setAdapter(userAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}