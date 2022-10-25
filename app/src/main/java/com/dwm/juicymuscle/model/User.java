package com.dwm.juicymuscle.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.service.autofill.FieldClassification;
import android.util.Log;

import android.os.Handler;

import com.dwm.juicymuscle.controller.HomeActivity;
import com.dwm.juicymuscle.controller.MainActivity;

import java.util.logging.LogRecord;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    private String email;
    private String mdp;

    public User() {
        email="";
        mdp="";
    }

    public boolean hasAValidEmail(){
        Pattern pattern = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
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
