package com.example.cineapp.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Ticket extends RealmObject {
    @PrimaryKey
    int id;
    int id_CineSesion;
    int fila;
    int asientoX;
    int asientoY;
    int id_CineVenta;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_CineSesion() {
        return id_CineSesion;
    }

    public void setId_CineSesion(int id_CineSesion) {
        this.id_CineSesion = id_CineSesion;
    }


    public int getAsientoX() {
        return asientoX;
    }

    public void setAsientoX(int asientoX) {
        this.asientoX = asientoX;
    }

    public int getAsientoY() {
        return asientoY;
    }

    public void setAsientoY(int asientoY) {
        this.asientoY = asientoY;
    }

    public int getId_CineVenta() {
        return id_CineVenta;
    }

    public void setId_CineVenta(int id_CineVenta) {
        this.id_CineVenta = id_CineVenta;
    }
}
