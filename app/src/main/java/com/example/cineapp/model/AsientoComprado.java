package com.example.cineapp.model;

import java.io.Serializable;

public class AsientoComprado implements Serializable {
    int asientoX;
    int asientoY;
    int idSala;

    public AsientoComprado(int asientoX, int asientoY, int idSala){
        this.asientoX =asientoX;
        this.asientoY =asientoY;
        this.idSala = idSala;
    }

    public int getAsientoX() {
        return asientoX;
    }

    public int getAsientoY(){
        return asientoY;
    }

    public int getIdSala() {
        return idSala;
    }
}
