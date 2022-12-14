package com.dwm.juicymuscle.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dwm.juicymuscle.R;
import com.dwm.juicymuscle.model.PutData;
import com.dwm.juicymuscle.model.User;
import com.dwm.juicymuscle.service.GetUser;

public class MainActivity extends AppCompatActivity {
    private EditText emailEditText;
    private EditText mdpEditText;
    private Button connexionButton;
    private Button sinscrireButton;
    private TextView errorMsg;
    private User user;

    public static final String SHARED_PREFS = "shared_prefs";
    public static final String EMAIL_KEY = "email_key";
    public static final String IDUSER_KEY = "iduser_key";
    public static final String USERNAME_KEY = "username_key";
    public SharedPreferences sharedpreferences;
    //String email, idUser, username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = new User();
        setContentView(R.layout.activity_main);

        emailEditText = findViewById(R.id.main_edittext_email);
        mdpEditText = findViewById(R.id.main_edittext_mdp);
        connexionButton = findViewById(R.id.main_button_connexion);
        sinscrireButton = findViewById(R.id.main_button_inscription);
        errorMsg = findViewById(R.id.main_textviex_errormsg);

        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        /*email = sharedpreferences.getString(EMAIL_KEY, null);
        email = sharedpreferences.getString(EMAIL_KEY, null);
        email = sharedpreferences.getString(EMAIL_KEY, null);*/

        connexionButton.setEnabled(false);
        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                errorMsg.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {
                user.setEmail(emailEditText.getText().toString());
                if(!mdpEditText.getText().toString().isEmpty() && !s.toString().isEmpty() && user.hasAValidEmail()){
                    connexionButton.setEnabled(true);
                    connexionButton.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.green));

                }else{
                    connexionButton.setEnabled(false);
                    connexionButton.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.greyBackground));
                }
            }
        });

        mdpEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                errorMsg.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {
                user.setMdp(mdpEditText.getText().toString());
                if(!emailEditText.getText().toString().isEmpty() && !s.toString().isEmpty() && user.hasAValidEmail()){
                    connexionButton.setEnabled(true);
                    connexionButton.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.green));
                }else{
                    connexionButton.setEnabled(false);
                    connexionButton.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.greyBackground));
                }
            }
        });

        connexionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler();
                Intent homeActivityIntent = new Intent(MainActivity.this, TrainingActivity.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String[] field = new String[2];
                        String[] data = new String[2];
                        field[0] = "email";
                        field[1] = "password";
                        data[0] = user.getEmail();
                        data[1] = user.getMdp();
                        PutData putData = new PutData("https://ulysseguillot.fr/apiLoginJuicyMuscle/login.php", "POST", field, data);
                        if (putData.startPut()) {
                            if(putData.onComplete()){
                                String result = putData.getResult();
                                if(result.equals("Login Success")) { //demarrage de l'activite Home si connexion reussie

                                    //recuperation de l'user a partir de la bdd (recup de son id):
                                    user = GetUser.get(user.getEmail());

                                    SharedPreferences.Editor editor = sharedpreferences.edit();
                                    editor.putString(EMAIL_KEY, user.getEmail());
                                    editor.putString(IDUSER_KEY, user.getId());
                                    editor.putString(USERNAME_KEY, user.getUsername());
                                    editor.apply();

                                    Intent homeActivityIntent = new Intent(MainActivity.this, HomeActivity.class);
                                    startActivity(homeActivityIntent);
                                }else{
                                    errorMsg.setText(result); //affichage du message d'erreur si connexion echouée
                                    Vibrator vib=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                                    vib.vibrate(1000);
                                }
                            }
                        }
                    }
                });
            }
        });
        sinscrireButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupActivityIntent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(signupActivityIntent);
            }
        });
    }
}