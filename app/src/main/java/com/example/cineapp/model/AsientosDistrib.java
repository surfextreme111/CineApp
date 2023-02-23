package com.example.cineapp.model;

import java.io.Serializable;
import java.util.List;

public class AsientosDistrib implements Serializable {
    List<AsientoComprado> asientos;

    public AsientosDistrib(List<AsientoComprado> asientosDistrib){
        asientos = asientosDistrib;
    }

    public List<AsientoComprado> getAsientos() {
        return asientos;
    }
}
