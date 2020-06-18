package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    MyDatabaseHelper myDB;
    ArrayList<String> input_id, input_title, input_town, input_pager;
    CustomAdapter customAdapter;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.exitItem) {
            SharedPreferences prefer = getSharedPreferences("login",0);
            prefer.edit().remove("KullaniciAdi").apply();
            prefer.edit().remove("Sifre").apply();

            Intent intent = new Intent(ListeActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        else if (item.getItemId() == R.id.profileItem){
            Intent intent = new Intent(ListeActivity.this,ProfileActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);

        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setTitle("Ana Sayfa");

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.floatingActionButton);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListeActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(ListeActivity.this);
        input_id = new ArrayList<>();
        input_title = new ArrayList<>();
        input_town = new ArrayList<>();
        input_pager = new ArrayList<>();

        storeDataInArray();

        customAdapter = new CustomAdapter(ListeActivity.this, this, input_id, input_title, input_town, input_pager);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListeActivity.this));
    }

    void storeDataInArray() {
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                input_id.add(cursor.getString(0));
                input_title.add(cursor.getString(1));
                input_town.add(cursor.getString(2));
                input_pager.add(cursor.getString(3));
            }
        }
    }

}
