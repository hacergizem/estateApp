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

    public class LoginActivity extends AppCompatActivity {

        EditText KullaniciAdi, Sifre;
        Button GirisYap;

        SharedPreferences sharedPreferences;

        UserProfile userProfile;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            ActionBar actionBar = getSupportActionBar();
            actionBar.hide();

            KullaniciAdi = findViewById(R.id.editTextUsername);
            Sifre = findViewById(R.id.editTextPassword);
            GirisYap = findViewById(R.id.buttonLogin);
            sharedPreferences = getSharedPreferences("login", 0);

            final String registeredUserName = sharedPreferences.getString("KullaniciAdi","");
            final String registeredPassword = sharedPreferences.getString("Sifre","");
            Log.d("deneme",registeredPassword+"adadfd");

            Intent intent1 = getIntent();
            Bundle b = intent1.getExtras();


            GirisYap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (KullaniciAdi.getText().toString().equals(registeredUserName) && Sifre.getText().toString().equals(registeredPassword)) {
                        Log.d("deneme", KullaniciAdi.getText().toString() + "kullanici adi");
                        Intent intent = new Intent(LoginActivity.this, ListeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }

            });
        }
    }