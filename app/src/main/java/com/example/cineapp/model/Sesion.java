package com.example.cineapp.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Sesion extends RealmObject {
    @PrimaryKey
    int id;
    int idSalaCine;
    String idPelicula;
    Date sesionHora;

    public int getIdSalaCine() {
        return idSalaCine;
    }

    public void setIdSalaCine(int idSalaCine) {
        this.idSalaCine = idSalaCine;
    }

    public String getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(String idPelicula) {
        this.idPelicula = idPelicula;
    }

    public Date getSesionHora() {
        return sesionHora;
    }

    public void setSesionHora(Date sesionHora) {
        this.sesionHora = sesionHora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
