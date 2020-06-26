package com.example.afinal;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class AddActivity extends AppCompatActivity {

    EditText title_input, town_input, pager_input;
    Button add_button;
    ImageView img;
    Uri selectedImage;
    Spinner spinner, spinner2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        spinner = findViewById(R.id.spinnerTown);
        spinner2 = findViewById(R.id.spinnerIlce);
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setTitle("Yeni Konut Ekle");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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


        title_input = findViewById(R.id.addTitle);
        add_button = findViewById(R.id.addButton);
        img = findViewById(R.id.addPicture);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedImage != null) {
                    MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                    myDB.addItem(title_input.getText().toString().trim(),
                           spinner.getSelectedItem().toString(),
                            spinner2.getSelectedItem().toString(),
                            selectedImage.toString().trim());
                }
            }
        });
    }

    public static final int PICK_IMAGE = 1;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE) {
            if (resultCode == RESULT_OK) {
                img.setImageURI(data.getData());
                selectedImage = data.getData();
            }
        }
    }
}
