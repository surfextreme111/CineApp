package com.example.cineapp.model.types;

public enum PegiEdad {
    pegi_A(0), pegi_Ai(1), pegi_7(7), pegi_7i(7), pegi_12(12), pegi_16(16), pegi_18(18);

    long number;

    PegiEdad(int nums){
        this.number = nums;
    }

    public long getNumber() {
        return number;
    }
}
