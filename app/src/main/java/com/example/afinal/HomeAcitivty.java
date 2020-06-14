package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeAcitivty extends AppCompatActivity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_acitivty);

        btn = findViewById(R.id.cikis);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefer = getSharedPreferences("login",0);
                prefer.edit().remove("KullaniciAdi").commit();
                prefer.edit().remove("Sifre").commit();

                Intent intent = new Intent(HomeAcitivty.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
