package com.example.cineapp.model.types;

import androidx.annotation.NonNull;

public enum TypeGeneroPelicula {
    AVENTURA("Aventura"),ACCION("Accion"), TERROR("Terror"),DRAMA("Drama"),ROMANCE("Romance");
    private String num;

    TypeGeneroPelicula(String id){
        num = id;
    }

    public String getNum() {
        return num;
    }

    @NonNull
    @Override
    public String toString() {
        return num;
    }
}
