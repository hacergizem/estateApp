package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ProfileActivity extends AppCompatActivity {

    Button btn;
    EditText username, password, telefon, eposta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btn = findViewById(R.id.updateButtonProfile);
        username = findViewById(R.id.updateUsername);
        password = findViewById(R.id.updSifre);
        telefon = findViewById(R.id.updTelefon);
        eposta = findViewById(R.id.updEposta);
        SharedPreferences prefs1 = getSharedPreferences("login", 0);
        username.setText(prefs1.getString("KullaniciAdi",""));
        password.setText(prefs1.getString("Sifre",""));
        telefon.setText(prefs1.getString("Telefon",""));
        eposta.setText(prefs1.getString("Email",""));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getSharedPreferences("login", 0);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("KullaniciAdi", username.getText().toString());
                editor.putString("Sifre", password.getText().toString());
                editor.putString("Telefon", telefon.getText().toString());
                editor.putString("Eposta", eposta.getText().toString());
                editor.apply();

            }
        });

    }
}
