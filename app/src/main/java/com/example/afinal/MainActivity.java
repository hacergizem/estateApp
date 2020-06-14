package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText KullaniciAdi, Sifre, SifreTekrar, Telefon, Eposta;
    Button KayitOl;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        KullaniciAdi = findViewById(R.id.editTextUsername);
        Sifre = findViewById(R.id.editTextPassword);
        SifreTekrar = findViewById(R.id.editTextPasswordAgain);
        Telefon = findViewById(R.id.editPhoneNumber);
        Eposta = findViewById(R.id.editMailInf);
        KayitOl = findViewById(R.id.buttonRegister);
        sharedPreferences = getSharedPreferences("login", 0);

        isLogin(this);

        KayitOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameValue = KullaniciAdi.getText().toString();
                String passwordValue = Sifre.getText().toString();
                String passwordValueAgain = SifreTekrar.getText().toString();
                String phoneValue = Telefon.getText().toString();
                String mailValue = Eposta.getText().toString();

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.putExtra("auto", "gizem");
                startActivity(intent);


                if (usernameValue.length() > 1) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("KullaniciAdi", usernameValue);
                    editor.putString("Sifre", passwordValue);
                    editor.putString("SifreTekrar", passwordValueAgain);
                    editor.putString("Telefon", phoneValue);
                    editor.putString("Email", mailValue);
                    editor.apply();
                    Toast.makeText(MainActivity.this, "User Registered", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(MainActivity.this, "Enter Value in the Field", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void isLogin(Context context) {
        final String registeredUserName = sharedPreferences.getString("KullaniciAdi", "");
        final String registeredPassword = sharedPreferences.getString("Sifre", "");
        Log.d("deneme", registeredPassword + "adadfd");

        Intent intent1 = getIntent();
        Bundle b = intent1.getExtras();

        if (!(registeredUserName.equals("") && registeredPassword.equals(""))) {
            Intent intent = new Intent(context, HomeAcitivty.class);
            startActivity(intent);
        }
    }
}
