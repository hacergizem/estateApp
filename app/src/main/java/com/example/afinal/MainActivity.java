package com.example.afinal;

import androidx.appcompat.app.ActionBar;
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

        final UserProfile userProfile = new UserProfile();

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


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
                if (usernameValue.equals("") || passwordValue.equals("") || passwordValueAgain.equals("") || phoneValue.equals("") || mailValue.equals("")) {
                    Toast.makeText(MainActivity.this, "Boş olamaz", Toast.LENGTH_SHORT).show();
                } else {
                    userProfile.setUserfullname(KullaniciAdi.getText().toString());
                    userProfile.setUserpassword(Sifre.getText().toString());
                    userProfile.setUsermail(Eposta.getText().toString());
                    userProfile.setUsernumber(KullaniciAdi.getText().toString());
                    userProfile.setUsername(KullaniciAdi.getText().toString());

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("KullaniciAdi", usernameValue);
                        editor.putString("Sifre", passwordValue);
                        editor.putString("SifreTekrar", passwordValueAgain);
                        editor.putString("Telefon", phoneValue);
                        editor.putString("Email", mailValue);
                        editor.apply();
                        Toast.makeText(MainActivity.this, "User Registered", Toast.LENGTH_SHORT).show();

//!!!!!!!!!!!!!!!!!!!!!
                        UserDBHelper db=new UserDBHelper(MainActivity.this);
                        db.insertUserDetails(usernameValue , passwordValue, mailValue,phoneValue);

                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        intent.putExtra("userInfo", userProfile);
                        startActivity(intent);
                        finish();

                        Toast.makeText(MainActivity.this, "Enter Value in the Field", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //KAYIT VAR MI, VARSA ATLIYOR O AN KAYDOLDUYSA DİREKT GİRİŞE GİDİYOR
    private void isLogin(Context context) {
        final String registeredUserName = sharedPreferences.getString("KullaniciAdi", "");
        final String registeredPassword = sharedPreferences.getString("Sifre", "");
        Log.d("deneme", registeredPassword + "adadfd");

        Intent intent1 = getIntent();
        Bundle b = new Bundle();
        b = getIntent().getExtras();
        Boolean bool = true;
        if (b != null){
            if (!(registeredUserName.equals("") && registeredPassword.equals("")) &&  ! b.getBoolean("Directed")) {
                Intent intent = new Intent(context, ListeActivity.class);
                startActivity(intent);
                finish();
            }
        }
        else{
            if (!(registeredUserName.equals("") && registeredPassword.equals(""))) {
                Intent intent = new Intent(context, ListeActivity.class);
                startActivity(intent);
                finish();
            }
        }

    }
}
