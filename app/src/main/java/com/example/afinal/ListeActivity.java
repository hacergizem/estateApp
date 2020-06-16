package com.example.afinal;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    Button btn;

    MyDatabaseHelper myDB;
    ArrayList<String> input_id, input_title, input_town, input_pager;
    CustomAdapter customAdapter;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            recreate();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);


        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.floatingActionButton);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListeActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(ListeActivity.this);
        input_id = new ArrayList<>();
        input_title = new ArrayList<>();
        input_town = new ArrayList<>();
        input_pager = new ArrayList<>();

        storeDataInArray();

        customAdapter = new CustomAdapter(ListeActivity.this,this,input_id,input_title,input_town,input_pager);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListeActivity.this));
    }

    void storeDataInArray(){
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount()==0){
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor.moveToNext()){
                input_id.add(cursor.getString(0));
                input_title.add(cursor.getString(1));
                input_town.add(cursor.getString(2));
                input_pager.add(cursor.getString(3));

            }
        }
    }
}
