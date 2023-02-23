package com.example.cineapp.dbUtilidades;

import android.content.Context;

import com.example.cineapp.model.Ticket;
import com.example.cineapp.model.Pelicula;
import com.example.cineapp.model.SalaCine;
import com.example.cineapp.model.Sesion;
import com.example.cineapp.model.Usuario;
import com.example.cineapp.model.VentaProvisionalSinTerminar;
import com.example.cineapp.model.types.Type;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import io.realm.Realm;

public class ControllerBD {
    public static Realm con;
    public static String userApp;

    private static ControllerBD instance=new ControllerBD();
    public static ControllerBD getInstance(Context context){
        con = BaseDatos.getInstance().conectar(context);
        return instance;
    }

    public List<Sesion> getAllHoursSesionsFromPeliculasHoy(Pelicula f){
        Calendar now = new GregorianCalendar();
        Date nowDate = now.getTime();
        List<Sesion> mostrarSesiones = new LinkedList<>();
        List<Sesion> sesions = getAllSesion();
        for(Sesion s : sesions){
            if(s.getIdPelicula().equals(f.getTitulo()) && s.getSesionHora().getDay() == nowDate.getDay()){
                mostrarSesiones.add(s);
            }
        }
        return mostrarSesiones;
    }

    public Type getTypeFromSala(SalaCine s){
        SalaCine sas = con.where(SalaCine.class).equalTo("id", s.getId()).findFirst();
        return sas.getTypeSala();
    }

    public List<Pelicula> getAllPeliculas(){
        return con.where(Pelicula.class).findAll();
    }
    public List<Pelicula> getPeliculasCartelera(){
        return con.where(Pelicula.class).equalTo("PublicadaPeli", true).findAll();
    }

    public List<Sesion> getAllSesion(){
        return con.where(Sesion.class).findAll();
    }

    public List<Usuario> getUsers(){
        return con.where(Usuario.class).findAll();
    }
    public List<SalaCine> getAllSalasCine(Type type){
        return con.where(SalaCine.class).equalTo("typeSala", type.getTipo()).findAll();
    }
    public int getLastIndexSesion(){
        return (int)con.where(Sesion.class).count();
    }
    public int getLastIndexVenta(){
        return (int)con.where(VentaProvisionalSinTerminar.class).count();
    }
    public int getLastIndexEntrada(){
        return (int)con.where(Ticket.class).count();
    }

    public void setUsuarioApp(Usuario u){
        userApp = u.getUsername();
    }

    public String getUsuarioApp(){
        return userApp;
    }

    //CREATE UPDATE GETS DELETES

    public Usuario getUsuario(String username){
        return con.where(Usuario.class).equalTo("username", username).findFirst();
    }
    public Ticket getEntrada(int id){
        return con.where(Ticket.class).equalTo("id", id).findFirst();
    }
    public Pelicula getPelicula(String titulo){
        return con.where(Pelicula.class).equalTo("titulo", titulo).findFirst();
    }
    public SalaCine getSalaCine(int id){
        return con.where(SalaCine.class).equalTo("id", id).findFirst();
    }
    public Sesion getSesion(int id){
        return con.where(Sesion.class).equalTo("id", id).findFirst();
    }
    public VentaProvisionalSinTerminar getVenta(int id){
        return con.where(VentaProvisionalSinTerminar.class).equalTo("id", id).findFirst();
    }

    public void updateUsuario(Usuario u){
        con.beginTransaction();
        con.copyToRealmOrUpdate(u);
        con.commitTransaction();
    }
    public void updateEntrada(Ticket e){
        con.beginTransaction();
        con.copyToRealmOrUpdate(e);
        con.commitTransaction();
    }
    public void updatePelicula(Pelicula f){
        con.beginTransaction();
        con.copyToRealmOrUpdate(f);
        con.commitTransaction();
    }
    public void updateSalaCine(SalaCine s){
        con.beginTransaction();
        con.copyToRealmOrUpdate(s);
        con.commitTransaction();
    }
    public void updateSesion(Sesion s){
        con.beginTransaction();
        con.copyToRealmOrUpdate(s);
        con.commitTransaction();
    }
    public void updateVenta(VentaProvisionalSinTerminar v){
        con.beginTransaction();
        con.copyToRealmOrUpdate(v);
        con.commitTransaction();
    }

    public void deleteUser(String username){
        Usuario u;
        if((u = getUsuario(username)) != null){
            con.beginTransaction();
            u.deleteFromRealm();
            con.commitTransaction();
        }
    }
    public void deleteEntrada(int id){
        Ticket e;
        if((e = getEntrada(id)) != null){
            con.beginTransaction();
            e.deleteFromRealm();
            con.commitTransaction();
        }
    }
    public void deleteFilm(String titulo){
        Pelicula f;
        if((f = getPelicula(titulo)) != null){
            con.beginTransaction();
            f.deleteFromRealm();
            con.commitTransaction();
        }
    }
    public void deleteSala(int id){
        SalaCine s;
        if((s = getSalaCine(id)) != null){
            con.beginTransaction();
            s.deleteFromRealm();
            con.commitTransaction();
        }
    }
    public void deleteSesion(int id){
        Sesion s;
        if((s = getSesion(id)) != null){
            con.beginTransaction();
            s.deleteFromRealm();
            con.commitTransaction();
        }
    }
    public void deleteVenta(int id){
        VentaProvisionalSinTerminar v;
        if((v = getVenta(id)) != null){
            con.beginTransaction();
            v.deleteFromRealm();
            con.commitTransaction();
        }
    }
}