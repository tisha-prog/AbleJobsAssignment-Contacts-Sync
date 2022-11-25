package com.example.contactssync;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button sync;
    Button add;
    Button delete;
    EditText name;
    EditText number;

    List<String> l = new ArrayList<>();

    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sync = findViewById(R.id.button);
        add = findViewById(R.id.button3);
        delete = findViewById(R.id.button2);
        name = findViewById(R.id.editTextTextPersonName);
        number = findViewById(R.id.editTextPhone);

        db = FirebaseDatabase.getInstance().getReference();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,android.R.id.text1,l);

        db.child("List").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                l.clear();
                for (DataSnapshot shot : dataSnapshot.getChildren()){
                    l.add(shot.getValue(String.class));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void syc(View view) {
        //the sync function will syc the data = contacts retrieved from firebase realtime database

    }

    public void delete(View view) {
    }

    public void add(View view) {
        // this add button will add contacts to Firebase
        // Write a message to the database

        db.setValue("Hello, World!");
        db.child("List")
                .child("item" + l.size())
                .setValue(name.getText().toString(), number.getText().toString());

    }
}