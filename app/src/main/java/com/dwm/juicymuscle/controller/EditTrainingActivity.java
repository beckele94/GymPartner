package com.dwm.juicymuscle.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dwm.juicymuscle.R;
import com.dwm.juicymuscle.adapter.AdapterListExercices;
import com.dwm.juicymuscle.model.Exercice;
import com.dwm.juicymuscle.model.PutData;
import com.dwm.juicymuscle.service.ServiceApi;

import java.io.IOException;
import java.util.ArrayList;

public class EditTrainingActivity extends AppCompatActivity {
    private ArrayList<Exercice> listeExercices = new ArrayList<Exercice>();

    private Button retourButton;
    private TextView nomTextView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_training);

        RecyclerView recyclerView = findViewById(R.id.edittraining_recyclerview_listexercice);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        listeExercices.add(new Exercice("1", "pompe1", "test", ""));
        adapter = new AdapterListExercices(listeExercices);
        recyclerView.setAdapter(adapter);

        retourButton = findViewById(R.id.edittraining_button_retour);
        nomTextView = findViewById(R.id.edittraining_textview_nomtest);

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                //listeExercices.clear();
                String[] field = new String[0];
                String[] data = new String[0];
                PutData putData = new PutData("http://ulysseguillot.fr/apiLoginJuicyMuscle/listExercices.php", "GET", field, data);

                if (putData.startPut()) {
                    if(putData.onComplete()){
                        String result = putData.getResult();

                        ServiceApi jsonToobject = new ServiceApi();
                        try {
                            listeExercices.clear();
                            listeExercices.addAll(jsonToobject.readJsonStream(result));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });



    }
}