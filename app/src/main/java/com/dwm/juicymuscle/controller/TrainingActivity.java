package com.dwm.juicymuscle.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.dwm.juicymuscle.R;
import com.dwm.juicymuscle.adapter.AdapterListExercices;
import com.dwm.juicymuscle.adapter.AdapterListExoPgrm;
import com.dwm.juicymuscle.model.Exercice;
import com.dwm.juicymuscle.model.ExoPgrm;
import com.dwm.juicymuscle.model.PutData;
import com.dwm.juicymuscle.service.ServiceApi;

import java.io.IOException;
import java.util.ArrayList;

public class TrainingActivity extends AppCompatActivity {
    private ArrayList<ExoPgrm> listeExoPgrm = new ArrayList<ExoPgrm>();
    private ArrayList<Exercice> listeExercices = new ArrayList<Exercice>();

    private Button editButton;
    private Button accueilButton;
    private AdapterListExoPgrm adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        RecyclerView recyclerView = findViewById(R.id.edittraining_recyclerview_listexoprgm);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdapterListExoPgrm(listeExoPgrm, listeExercices);
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

        //TODO : FACTORISER les requete api en creant une methode dans serviceApi
        Handler handler = new Handler(); //recuperation des exercices et appel du recycler
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[1];
                String[] data = new String[1];
                field[0] = "idPgrm";
                data[0] = "5"; //TODO : recuper la valeur de l'idPgrm a partir de la selection de l'entrainement (dans la page home)
                PutData putData = new PutData("https://ulysseguillot.fr/apiLoginJuicyMuscle/getExoPgrm.php", "GET", field, data);

                if (putData.startPut()) {
                    if(putData.onComplete()){
                        String result = putData.getResult();

                        ServiceApi jsonToObject = new ServiceApi();
                        try {
                            listeExoPgrm.addAll(jsonToObject.readJsonExoPgrm(result));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                field = new String[0];
                data = new String[0];
                putData = new PutData("https://ulysseguillot.fr/apiLoginJuicyMuscle/getExercices.php", "GET", field, data);

                if (putData.startPut()) {
                    if(putData.onComplete()){
                        String result = putData.getResult();

                        ServiceApi jsonToObject = new ServiceApi();
                        try {
                            listeExercices.addAll(jsonToObject.readJsonExercice(result));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                adapter.notifyDataSetChanged();
                //adapter.updateData(listeExoPgrm); //TODO: pq cela vide la liste ???
            }
        });
    }
}