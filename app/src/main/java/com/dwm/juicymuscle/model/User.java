package com.dwm.juicymuscle.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.service.autofill.FieldClassification;
import android.util.Log;

import android.os.Handler;
import android.util.Patterns;

import com.dwm.juicymuscle.controller.HomeActivity;
import com.dwm.juicymuscle.controller.MainActivity;

import java.util.logging.LogRecord;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    public String id;
    public String email;
    private String mdp;
    public String username;

    public User() {
        email="";
        mdp="";
        username="";
    }

    public User(String id, String email, String username) {
        this.id = id;
        this.email = email;
        this.username = username;
    }

    public boolean hasAValidEmail(){
        Pattern pattern = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
