package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText title_input, town_input, pager_input;
    Button update_button;
    String id, title, town, pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title_input = findViewById(R.id.addTitle2);
        town_input = findViewById(R.id.addTown2);
        pager_input = findViewById(R.id.addPager2);
        update_button = findViewById(R.id.updateButton);
        getAndSetIntentData();

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.updateData(id,title,town,pager);
            }
        });

    }

    void getAndSetIntentData(){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("town") && getIntent().hasExtra("pager")){
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            town = getIntent().getStringExtra("town");
            pager = getIntent().getStringExtra("pager");

            title_input.setText(title);
            town_input.setText(town);
            pager_input.setText(pager);
        }
        else{
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }
}
