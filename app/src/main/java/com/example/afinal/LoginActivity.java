package com.example.afinal;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText KullaniciAdi, Sifre;
    Button GirisYap;
    TextView kayitol;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        KullaniciAdi = findViewById(R.id.editTextUsername);
        Sifre = findViewById(R.id.editTextPassword);
        GirisYap = findViewById(R.id.buttonLogin);
        kayitol = findViewById(R.id.textRegistered);
        kayitol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("Directed",true);
                startActivity(intent);
            }
        });
        sharedPreferences = getSharedPreferences("login", 0);

        final String registeredUserName = sharedPreferences.getString("KullaniciAdi","");
        final String registeredPassword = sharedPreferences.getString("Sifre","");


        Intent intent1 = getIntent();
        Bundle b = intent1.getExtras();


        GirisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                UserDBHelper db=new UserDBHelper(LoginActivity.this);
                if(db.passwordCorrection(KullaniciAdi.getText().toString(), Sifre.getText().toString())) {

                    Intent intent = new Intent(LoginActivity.this, ListeActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "Yanlış bilgi", Toast.LENGTH_SHORT).show();
                }

            }

        });
    }
}