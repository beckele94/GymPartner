package com.dwm.juicymuscle.model;

public class User {
    private String email;
    private String mdp;

    public boolean verifyCred() {
        if(email == "admin" && mdp == "password"){
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
