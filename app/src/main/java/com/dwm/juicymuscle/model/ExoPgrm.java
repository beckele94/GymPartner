package com.dwm.juicymuscle.model;

public class ExoPgrm {
    private String id;
    private String idPgrm;
    private String idExo;
    private String nbSerie;
    private String nbRep;
    private String tempsRepos;
    private String poids;

    public ExoPgrm(String id, String idPgrm, String idExo, String nbSerie, String nbRep, String tempsRepos, String poids) {
        this.id = id;
        this.idPgrm = idPgrm;
        this.idExo = idExo;
        this.nbSerie = nbSerie;
        this.nbRep = nbRep;
        this.tempsRepos = tempsRepos;
        this.poids = poids;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPgrm() {
        return idPgrm;
    }

    public void setIdPgrm(String idPgrm) {
        this.idPgrm = idPgrm;
    }

    public String getIdExo() {
        return idExo;
    }

    public void setIdExo(String idExo) {
        this.idExo = idExo;
    }

    public String getNbSerie() {
        return nbSerie;
    }

    public void setNbSerie(String nbSerie) {
        this.nbSerie = nbSerie;
    }

    public String getNbRep() {
        return nbRep;
    }

    public void setNbRep(String nbRep) {
        this.nbRep = nbRep;
    }

    public String getTempsRepos() {
        return tempsRepos;
    }

    public void setTempsRepos(String tempsRepos) {
        this.tempsRepos = tempsRepos;
    }

    public String getPoids() {
        return poids;
    }

    public void setPoids(String poids) {
        this.poids = poids;
    }
}
