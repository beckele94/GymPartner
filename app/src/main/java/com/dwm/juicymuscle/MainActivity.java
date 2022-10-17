package com.dwm.juicymuscle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText emailEditText;
    private EditText mdpEditText;
    private Button connexionButton;
    private Button sinscrireButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEditText = findViewById(R.id.main_edittext_email);
        mdpEditText = findViewById(R.id.main_edittext_mdp);
        connexionButton = findViewById(R.id.main_button_connexion);
        sinscrireButton = findViewById(R.id.main_button_inscription);

        connexionButton.setEnabled(false);
        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                connexionButton.setEnabled(!s.toString().isEmpty());
            }
        });

        connexionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}