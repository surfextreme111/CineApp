package com.example.cineapp.model;

import androidx.annotation.NonNull;

import com.example.cineapp.model.types.TypeGeneroPelicula;
import com.example.cineapp.model.types.PegiEdad;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Pelicula extends RealmObject implements Serializable {
    @PrimaryKey
    String titulo;
    String tiempo;
    String sipnosis;
    String genero;
    long pegi;
    String imagenLink;
    boolean PublicadaPeli;
//alba si lees esto eres una crack =D best teacher!

    public Pelicula(String titulo, String tiempo, String sipnosis, String genero, long pegi, String imagenLink, boolean publicadaPeli) {
        this.titulo = titulo;
        this.tiempo = tiempo;
        this.sipnosis = sipnosis;
        this.genero = genero;
        this.pegi = pegi;
        this.imagenLink = imagenLink;
        this.PublicadaPeli = publicadaPeli;
    }

    public Pelicula() {
    }

    public boolean isPublicadaPeli() {
        return PublicadaPeli;
    }

    public void setPublicadaPeli(boolean publicadaPeli) {
        this.PublicadaPeli = publicadaPeli;
    }

    public void setPegi(long pegi) {
        this.pegi = pegi;
    }

    public String getImagenLink() {
        return imagenLink;
    }

    public void setImagenLink(String imagenLink) {
        this.imagenLink = imagenLink;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getSipnosis() {
        return sipnosis;
    }

    public void setSipnosis(String descripcion) {
        this.sipnosis = descripcion;
    }

    public TypeGeneroPelicula getGenero() {
        switch (genero){
            case "Aventura":
                return TypeGeneroPelicula.AVENTURA;
            case "Terror":
                return TypeGeneroPelicula.TERROR;
            case "Accion":
                return TypeGeneroPelicula.ACCION;
            case "Drama":
                return TypeGeneroPelicula.DRAMA;
            case "Romance":
                return TypeGeneroPelicula.ROMANCE;

            //spinner informarse

            default:
                return TypeGeneroPelicula.AVENTURA;
        }
    }

    public void setGenero(TypeGeneroPelicula genero) {
        this.genero = genero.getNum();
    }

    public long getPegi() {
        return pegi;
    }

    public void setPegi(PegiEdad pegi) {
        this.pegi = pegi.getNumber();
    }

    public String getCartelera() {
        return imagenLink;
    }

    public void setCartelera(String cartelera) {
        this.imagenLink = cartelera;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }

    @NonNull
    @Override
    public String toString() {
        return titulo;
    }
}
