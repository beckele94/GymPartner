package com.dwm.juicymuscle.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.dwm.juicymuscle.R;
import com.dwm.juicymuscle.adapter.AdapterListProgrammes;
import com.dwm.juicymuscle.model.Programme;
import com.dwm.juicymuscle.model.PutData;
import com.dwm.juicymuscle.service.ServiceApi;

import java.io.IOException;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private ArrayList<Programme> listeProgrammes = new ArrayList<>();
    private AdapterListProgrammes adapter;

    public static final String SHARED_PREFS = "shared_prefs";
    public static final String IDUSER_KEY = "iduser_key";
    public SharedPreferences sharedpreferences;
    private String idUser;

    private Button newPgrmBtn;
    private Button creerPopupBtn;
    private PopupWindow popupWindow;
    private EditText nomExoEdit;
    private String nomExo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        idUser = sharedpreferences.getString(IDUSER_KEY, null);

        RecyclerView recyclerView = findViewById(R.id.home_recyclerview_listprogrammes);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdapterListProgrammes(listeProgrammes, HomeActivity.this);
        recyclerView.setAdapter(adapter);

        newPgrmBtn = findViewById(R.id.home_button_new);
        newPgrmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater layoutInflater = (LayoutInflater) HomeActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout._popup_nom_pgrm, null);
                creerPopupBtn =  customView.findViewById(R.id.popup_button_creer);
                nomExoEdit = customView.findViewById(R.id.popup_edittext_nompgrm);

                popupWindow = new PopupWindow(customView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
                popupWindow.showAtLocation(customView, Gravity.CENTER, 0, 0);

                nomExoEdit.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        nomExo = nomExoEdit.getText().toString();
                    }
                });

                creerPopupBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Handler handler = new Handler();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                String[] field = new String[2];
                                String[] data = new String[2];
                                field[0] = "idUser";
                                data[0] = idUser;
                                field[1] = "nom";
                                data[1] = nomExo;
                                PutData putData = new PutData("https://ulysseguillot.fr/apiLoginJuicyMuscle/addProgramme.php", "GET", field, data);

                                if (putData.startPut()) {
                                    if(putData.onComplete()){
                                        Intent HomeActivityIntent = new Intent(HomeActivity.this, HomeActivity.class);
                                        startActivity(HomeActivityIntent);
                                    }
                                }
                            }
                        });
                    }
                });
            }
        });

        Handler handler = new Handler(); //recuperation des exercices et appel du recycler
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[1];
                String[] data = new String[1];
                field[0] = "idUser";
                data[0] = idUser;
                PutData putData = new PutData("https://ulysseguillot.fr/apiLoginJuicyMuscle/getProgrammes.php", "GET", field, data);

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
            }
        });
    }
}