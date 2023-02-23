package com.example.cineapp.model;

import androidx.annotation.NonNull;


import com.example.cineapp.model.types.Type;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class SalaCine extends RealmObject {
    @PrimaryKey
    int id;
    int typeSala;
    int y_columnas;

    int x_fila;

    public SalaCine() {

    }

    public SalaCine(int id, int x_fila, int y_columnas, int typeSala) {
        this.id = id;
        this.x_fila = x_fila;
        this.y_columnas = y_columnas;
        this.typeSala = typeSala;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX_fila() {
        return x_fila;
    }

    public void setX_fila(int x_fila) {
        this.x_fila = x_fila;
    }

    public int getY_columnas() {
        return y_columnas;
    }

    public void setY_columnas(int y_columnas) {
        this.y_columnas = y_columnas;
    }

    public Type getTypeSala() {
        switch (typeSala){
            case 1:
                return Type.TRESD;
            case 2:
                return Type.FOURDX;
            default:
                return Type.NORMAL;
        }
    }

    public void setTypeSala(Type typeSala) {
        this.typeSala = typeSala.getTipo();
    }

    @NonNull
    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
