package com.dwm.juicymuscle.model;

import android.util.Log;

public class User {
    private String email;
    private String mdp;

    public User() {
        email="";
        mdp="";
    }

    public boolean verifyCred() {
        if(email.equals("admin") && mdp.equals("password")){
            return true;
        }else {
            return false;
        }
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
