package com.dwm.juicymuscle.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.dwm.juicymuscle.R;
import com.dwm.juicymuscle.adapter.AdapterListExercices;
import com.dwm.juicymuscle.adapter.AdapterListExoPgrm;
import com.dwm.juicymuscle.model.Exercice;

import java.util.ArrayList;

public class TrainingActivity extends AppCompatActivity {
    private ArrayList<Exercice> listeExoPgrm = new ArrayList<Exercice>();

    private Button editButton;
    private Button accueilButton;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        RecyclerView recyclerView = findViewById(R.id.edittraining_recyclerview_listexoprgm);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdapterListExoPgrm(listeExoPgrm);
        recyclerView.setAdapter(adapter);

        editButton = findViewById(R.id.training_button_edit);
        accueilButton = findViewById(R.id.training_button_accueil);


        editButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent editTrainingActivityIntent = new Intent(TrainingActivity.this, EditTrainingActivity.class);
                startActivity(editTrainingActivityIntent);
            }
        });
        accueilButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent editTrainingActivityIntent = new Intent(TrainingActivity.this, HomeActivity.class);
                startActivity(editTrainingActivityIntent);
            }
        });


        listeExoPgrm.add(new Exercice("1","Pompes","Pecs","Faire pompes"));
        adapter.notifyDataSetChanged();

    }
}