package com.example.cineapp.model.types;

public enum Type {
    //private String tresD = "3D";
    //private String kuatoD = "4DX";
    //private String normal = "normal";

    TRESD(1), FOURDX(2), NORMAL(3);

    private int num;
    Type(int i){
        this.num = i;
    }

    public int getTipo(){
        return num;
    }
}
