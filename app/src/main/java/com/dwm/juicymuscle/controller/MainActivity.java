package com.dwm.juicymuscle.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dwm.juicymuscle.R;
import com.dwm.juicymuscle.model.PutData;
import com.dwm.juicymuscle.model.User;

public class MainActivity extends AppCompatActivity {
    private EditText emailEditText;
    private EditText mdpEditText;
    private Button connexionButton;
    private Button sinscrireButton;
    private TextView errorMsg;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = new User(getApplicationContext());
        setContentView(R.layout.activity_main);

        emailEditText = findViewById(R.id.main_edittext_email);
        mdpEditText = findViewById(R.id.main_edittext_mdp);
        connexionButton = findViewById(R.id.main_button_connexion);
        sinscrireButton = findViewById(R.id.main_button_inscription);
        errorMsg = findViewById(R.id.main_textviex_errormsg);

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
                if(!mdpEditText.getText().toString().isEmpty() && !s.toString().isEmpty()){
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
                if(!emailEditText.getText().toString().isEmpty() && !s.toString().isEmpty()){
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
                user.setEmail(emailEditText.getText().toString());
                user.setMdp(mdpEditText.getText().toString());

                Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String[] field = new String[2];
                        String[] data = new String[2];
                        field[0] = "email";
                        field[1] = "password";
                        data[0] = user.getEmail();
                        data[1] = user.getMdp();
                        PutData putData = new PutData("http://ulysseguillot.fr/apiLoginJuicyMuscle/login.php", "POST", field, data);
                        if (putData.startPut()) {
                            if(putData.onComplete()){
                                String result = putData.getResult();
                                if(result.equals("Login Success")) { //demarrage de l'activite Home si connexion reussie
                                    Intent homeActivityIntent = new Intent(MainActivity.this, HomeActivity.class);
                                    startActivity(homeActivityIntent);
                                }else{
                                    errorMsg.setText(result); //affichage du message d'erreur si connexion echouée
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