package com.example.afinal;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText title_input, town_input, pager_input;
    Button update_button, delete_button;
    String id, title, town, pager;
    Spinner spinner,spinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        spinner = findViewById(R.id.spinnerTown);
        spinner2 = findViewById(R.id.spinnerIlce);



        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setTitle("Güncelleme Ekranı");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        title_input = findViewById(R.id.addTitle2);
        spinner2 = findViewById(R.id.spinnerPager2);
        update_button = findViewById(R.id.updateButton);
        delete_button = findViewById(R.id.deleteButton);
        spinner = findViewById(R.id.spinnerTown1);

        String[] iller = {"Ankara","Istanbul","İzmir"};
        ArrayAdapter<String> aa = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, iller);
        aa.setDropDownViewResource(
                android.R.layout.simple_spinner_item);
        spinner.setAdapter(aa);

        String[] ilceler = {"Keçiören","Dikmen","Eryaman","Bağcılar","Fatih","Zeytinburnu","Balçova","Bornova","Gaziemir",};
        ArrayAdapter<String> bb = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ilceler);
        bb.setDropDownViewResource(
                android.R.layout.simple_spinner_item);
        spinner2.setAdapter(bb);

        getAndSetIntentData();

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                title = title_input.getText().toString().trim();
                town = spinner.getSelectedItem().toString().trim();
                pager = spinner2.getSelectedItem().toString().trim();
                myDB.updateData(id, title, town, pager);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });
    }

    void getAndSetIntentData(){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("town") && getIntent().hasExtra("pager")){
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            town = getIntent().getStringExtra("town");
            pager = getIntent().getStringExtra("pager");

            spinner.setSelection(getIndex(spinner, town));
            spinner2.setSelection(getIndex(spinner, pager));

            title_input.setText(title);

            Log.d("deneme",id);
        }
        else{
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Kolonu sil: "+ title + "?");
        builder.setMessage(title +  " kolonunun silmek istediğinize emin misiniz?" );
        builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }



    //private method of your class
    private int getIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }

        return 0;
    }
}
