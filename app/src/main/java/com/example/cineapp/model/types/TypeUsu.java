package com.example.cineapp.model.types;

public enum TypeUsu {
    EPECTADOR(1), ADMIN(2), EMPLEADO(3);
    private int num;
    TypeUsu(int id){
        num=id;
    }

    public int getNum() {
        return num;
    }

}
