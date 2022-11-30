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
import com.dwm.juicymuscle.adapter.AdapterListProgrammes;
import com.dwm.juicymuscle.model.Exercice;
import com.dwm.juicymuscle.model.ExoPgrm;
import com.dwm.juicymuscle.model.Programme;
import com.dwm.juicymuscle.model.PutData;
import com.dwm.juicymuscle.service.ServiceApi;

import java.io.IOException;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private ArrayList<Programme> listeProgrammes = new ArrayList<>();
    private AdapterListProgrammes adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        RecyclerView recyclerView = findViewById(R.id.edittraining_recyclerview_listprogrammes);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdapterListProgrammes(listeProgrammes);
        recyclerView.setAdapter(adapter);





        //TODO : FACTORISER les requete api en creant une methode dans serviceApi
        Handler handler = new Handler(); //recuperation des exercices et appel du recycler
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[1];
                String[] data = new String[1];
                field[0] = "nom";
                data[0] = "5"; //TODO : recuper la valeur de l'idPgrm a partir de la selection de l'entrainement (dans la page home)
                PutData putData = new PutData("http://ulysseguillot.fr/apiLoginJuicyMuscle/getExoPgrm.php", "GET", field, data);

                if (putData.startPut()) {
                    if(putData.onComplete()){
                        String result = putData.getResult();

                        ServiceApi jsonToObject = new ServiceApi();
                        try {
                            listeProgrammes.addAll(jsonToObject.readJsonProgramme(result));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                field = new String[0];
                data = new String[0];
                putData = new PutData("http://ulysseguillot.fr/apiLoginJuicyMuscle/getExercices.php", "GET", field, data);

                if (putData.startPut()) {
                    if(putData.onComplete()){
                        String result = putData.getResult();

                        ServiceApi jsonToObject = new ServiceApi();
                        try {
                            listeProgrammes.addAll(jsonToObject.readJsonProgramme(result));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                adapter.notifyDataSetChanged();
                //adapter.updateData(listeProgrammes); //TODO: pq cela vide la liste ???
            }
        });
    }
}