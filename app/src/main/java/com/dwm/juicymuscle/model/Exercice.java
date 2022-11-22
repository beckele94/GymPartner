package com.dwm.juicymuscle.model;

public class Exercice {
    private String id;
    private String nom;
    private String muscle;
    private String description;

    public Exercice(String id, String nom, String muscle, String description){
        this.id = id;
        this.nom = nom;
        this.muscle = muscle;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public String getMuscle() {
        return muscle;
    }

    public String getNom() {
        return nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMuscle(String muscle) {
        this.muscle = muscle;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
