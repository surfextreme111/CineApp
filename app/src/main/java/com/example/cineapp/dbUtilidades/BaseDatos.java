package com.example.cineapp.dbUtilidades;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;
public class BaseDatos {
    private static BaseDatos instance=new BaseDatos();
    public static BaseDatos getInstance() { return instance; }
    private Realm con;
    public Realm conectar(Context context) {
        if (con == null) {
            Realm.init(context);
            String nombre = "BUSTOPOLIS";
            RealmConfiguration rc = new RealmConfiguration.Builder().name(nombre).schemaVersion(4)
                    .deleteRealmIfMigrationNeeded().build();
            con = Realm.getInstance(rc);
        }
        return con;
    }
}
