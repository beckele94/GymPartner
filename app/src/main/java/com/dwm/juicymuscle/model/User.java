package com.dwm.juicymuscle.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import android.os.Handler;

import com.dwm.juicymuscle.controller.HomeActivity;
import com.dwm.juicymuscle.controller.MainActivity;

import java.util.logging.LogRecord;

public class User {
    private String email;
    private String mdp;
    private Context context;

    public User(Context context) {
        email="";
        mdp="";
        this.context = context;
    }

    public void Login() {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[2];
                String[] data = new String[2];
                field[0] = "email";
                field[1] = "password";
                data[0] = email;
                data[1] = mdp;
                PutData putData = new PutData("http://ulysseguillot.fr/apiLoginJuicyMuscle/login.php", "POST", field, data);
                if (putData.startPut()) {
                    if(putData.onComplete()){
                        String result = putData.getResult();
                        if(result.equals("Login Success")) {
                            Intent homeActivityIntent = new Intent(context, HomeActivity.class);
                            homeActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(homeActivityIntent);
                        }else{
                            Log.e("user", result ); // AFFICHER ICI le resultat !!!
                        }
                    }
                }
            }
        });
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}
