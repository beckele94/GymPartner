package com.dwm.juicymuscle.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dwm.juicymuscle.R;
import com.dwm.juicymuscle.model.User;

public class SignUpActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText mdpEditText;
    private EditText mdpConfirmEditText;
    private Button sinscrireButton;
    private TextView errorMsg;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = new User();
        setContentView(R.layout.activity_sign_up);

        usernameEditText = findViewById(R.id.signup_edittext_username);
        emailEditText = findViewById(R.id.signup_edittext_email);
        mdpEditText = findViewById(R.id.signup_edittext_mdp);
        mdpConfirmEditText = findViewById(R.id.signup_edittext_mdpconfirm);
        errorMsg = findViewById(R.id.signup_textviex_errormsg);
        sinscrireButton = findViewById(R.id.signup_button_inscription);

        sinscrireButton.setEnabled(false);
        usernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                errorMsg.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {
                verifChampsRemplis();
            }
        });

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
                verifChampsRemplis();
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
                verifChampsRemplis();
            }
        });

        mdpConfirmEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                errorMsg.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {
                verifChampsRemplis();
            }
        });

        sinscrireButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //TODO: integrer l'API d'inscription ici !!!
                    }
                });
            }
        });
    }

    public void verifChampsRemplis(){
        if(!emailEditText.getText().toString().isEmpty() && !usernameEditText.getText().toString().isEmpty() && !mdpEditText.getText().toString().isEmpty() && !mdpConfirmEditText.getText().toString().isEmpty()){
            if(user.hasAValidEmail()){
                if (mdpEditText.getText().toString().equals(mdpConfirmEditText.getText().toString())){
                    sinscrireButton.setEnabled(true);
                    sinscrireButton.setTextColor(ContextCompat.getColor(SignUpActivity.this, R.color.green));
                }else{
                    errorMsg.setText("Les mots de passe ne correspondent pas !");
                    sinscrireButton.setEnabled(false);
                    sinscrireButton.setTextColor(ContextCompat.getColor(SignUpActivity.this, R.color.greyBackground));
                }
            }else{
                errorMsg.setText("Veuillez saisir une adresse email valide !");
                sinscrireButton.setEnabled(false);
                sinscrireButton.setTextColor(ContextCompat.getColor(SignUpActivity.this, R.color.greyBackground));
            }
        }else{
            errorMsg.setText("Veuillez remplir tous les champs !");
            sinscrireButton.setEnabled(false);
            sinscrireButton.setTextColor(ContextCompat.getColor(SignUpActivity.this, R.color.greyBackground));
        }
    }
}