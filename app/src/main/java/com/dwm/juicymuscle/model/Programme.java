package com.dwm.juicymuscle.model;

public class Programme {
    private String id;
    private String idUser;
    private String nom;

    public Programme(String id, String idUser, String nom) {
        this.id = id;
        this.idUser = idUser;
        this.nom = nom;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
