package com.dwm.juicymuscle.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dwm.juicymuscle.R;
import com.dwm.juicymuscle.adapter.AdapterListExercices;
import com.dwm.juicymuscle.adapter.AdapterListExoPgrm;
import com.dwm.juicymuscle.adapter.AdapterModifyPgrm;
import com.dwm.juicymuscle.model.Exercice;
import com.dwm.juicymuscle.model.ExoPgrm;
import com.dwm.juicymuscle.model.PutData;
import com.dwm.juicymuscle.service.ServiceApi;

import java.io.IOException;
import java.util.ArrayList;

public class EditTrainingActivity extends AppCompatActivity {
    private ArrayList<Exercice> listeExercices = new ArrayList<Exercice>();
    private ArrayList<ExoPgrm> listeExoPgrm = new ArrayList<ExoPgrm>();

    private Button retourButton;
    private TextView titleTextView;
    private RecyclerView.Adapter adapterListeExo;
    private RecyclerView.Adapter adapterListeExoPgrm;

    public static final String SHARED_PREFS = "shared_prefs";
    public static final String IDPGRM_KEY = "idpgrm_key";
    public SharedPreferences sharedpreferences;
    private String idPgrm;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_training);

        //RecyclerView des exo du programme :
        RecyclerView recyclerViewExoPgrm = findViewById(R.id.edittraining_recyclerview_modifypgrm);
        recyclerViewExoPgrm.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManagerExoPgrm = new LinearLayoutManager(this);
        recyclerViewExoPgrm.setLayoutManager(layoutManagerExoPgrm);
        adapterListeExoPgrm = new AdapterModifyPgrm(listeExoPgrm, listeExercices, EditTrainingActivity.this);
        recyclerViewExoPgrm.setAdapter(adapterListeExoPgrm);

        //RecyclerView de tous les exo
        RecyclerView recyclerView = findViewById(R.id.edittraining_recyclerview_listexercice);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapterListeExo = new AdapterListExercices(listeExercices, this, adapterListeExoPgrm, listeExoPgrm);
        recyclerView.setAdapter(adapterListeExo);

        retourButton = findViewById(R.id.edittraining_button_retour);
        titleTextView = findViewById(R.id.edittraining_textview_title);

        //recupération des variables partagées
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        idPgrm = sharedpreferences.getString(IDPGRM_KEY, null);

        titleTextView.setText("Exercices disponibles :" );

        //bouton retour a la page de training
        retourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent trainingActivityIntent = new Intent(EditTrainingActivity.this, TrainingActivity.class);
                startActivity(trainingActivityIntent);
            }
        });

        //requetes API pour recup les exo du prgm et la liste de tous les exo dispo
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                //PRemiere requete qui recup tous les exo dispo
                String[] field = new String[0];
                String[] data = new String[0];
                PutData putData = new PutData("https://ulysseguillot.fr/apiLoginJuicyMuscle/getExercices.php", "GET", field, data);

                if (putData.startPut()) {
                    if(putData.onComplete()){
                        String result = putData.getResult();

                        ServiceApi jsonToObject = new ServiceApi();
                        try {
                            listeExercices.addAll(jsonToObject.readJsonExercice(result));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        adapterListeExo.notifyDataSetChanged();
                    }
                }

                //deuxieme requete qui recup les exo de l'entrainement
                field = new String[1];
                data = new String[1];
                field[0] = "idPgrm";
                data[0] = idPgrm;
                putData = new PutData("https://ulysseguillot.fr/apiLoginJuicyMuscle/getExoPgrm.php", "GET", field, data);

                if (putData.startPut()) {
                    if(putData.onComplete()){
                        String result = putData.getResult();

                        ServiceApi jsonToObject = new ServiceApi();
                        try {
                            listeExoPgrm.addAll(jsonToObject.readJsonExoPgrm(result));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        adapterListeExoPgrm.notifyDataSetChanged();
                    }
                }
            }
        });
    }
}