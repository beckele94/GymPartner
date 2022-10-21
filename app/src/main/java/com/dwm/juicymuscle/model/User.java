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
