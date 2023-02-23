package com.example.cineapp.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class VentaProvisionalSinTerminar extends RealmObject {
    @PrimaryKey
    int id;
    float precio;
    Date hora;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }
}
