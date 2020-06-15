package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText title_input, town_input, pager_input;
    Button add_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title_input = findViewById(R.id.addTitle);
        town_input = findViewById(R.id.addTown);
        pager_input = findViewById(R.id.addPager);
        add_button = findViewById(R.id.addButton);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addBook(title_input.getText().toString().trim(),
                        town_input.getText().toString().trim(),
                        pager_input.getText().toString().trim());
            }
        });

    }
}
