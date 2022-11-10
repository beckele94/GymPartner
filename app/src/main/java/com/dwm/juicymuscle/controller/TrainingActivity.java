package com.dwm.juicymuscle.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.dwm.juicymuscle.R;

public class TrainingActivity extends AppCompatActivity {

    private Button editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        editButton = findViewById(R.id.training_button_edit);

        editButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent editTrainingActivityIntent = new Intent(TrainingActivity.this, EditTrainingActivity.class);
                startActivity(editTrainingActivityIntent);
            }
        });
    }
}