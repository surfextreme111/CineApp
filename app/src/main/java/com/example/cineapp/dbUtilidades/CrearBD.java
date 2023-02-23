package com.example.cineapp.dbUtilidades;

import android.content.Context;

import com.example.cineapp.model.Pelicula;
import com.example.cineapp.model.SalaCine;
import com.example.cineapp.model.Usuario;
import com.example.cineapp.model.types.Type;
import com.example.cineapp.model.types.TypeGeneroPelicula;
import com.example.cineapp.model.types.PegiEdad;

import java.util.LinkedList;
import java.util.List;

import io.realm.Realm;

public class CrearBD {
    public Realm con;
    Context context;
    List<Usuario> usuarios;
    List<SalaCine> salas;
    List<Pelicula> peliculas;

    public CrearBD(Context context) {
        usuarios = new LinkedList<>();
        peliculas = new LinkedList<>();
        salas = new LinkedList<>();
        con = BaseDatos.getInstance().conectar(context);
        createBD();
    }

    private boolean checkBD() {
        long count = con.where(Usuario.class).count();
        if (count == 0) {
            return true;
        }
        return true;
    }

    public void createBD() {
        if (checkBD()) {
            Usuario u = new Usuario();
            u.setUsername("admin");
            u.setDNI("54413629E");
            u.setApellido("Bustos");
            u.setNombre("Andreu");
            u.setPassword("admin");
            u.setAdmin(true);

            Usuario u2 = new Usuario();
            u2.setUsername("lego");
            u2.setDNI("19283845E");
            u2.setApellido("Bustos");
            u2.setNombre("Ernest");
            u2.setPassword("lego");
            u2.setAdmin(false);

            usuarios.add(u);

            Pelicula peli = new Pelicula();
            peli.setTitulo("Jurasic World Dominion");
            peli.setSipnosis("Jurasic Park se ha extendido y ahora los dinosaurios forman parte del mundo real");
            peli.setTiempo("2h 30m");
            peli.setCartelera("https://cdn.kinepolis.es/images/ES/65459BAD-CA99-4711-A97B-E049A5FA94D2/HO00004274/0000006106/El_Piloto.jpg");
            peli.setGenero(TypeGeneroPelicula.AVENTURA);
            peli.setPegi(PegiEdad.pegi_Ai);

            Pelicula peli2 = new Pelicula();
            peli2.setTitulo("EL GATO CON BOTAS ");
            peli2.setSipnosis("el gato mas famoso y sus intrepidas aventuras");
            peli2.setTiempo("1h 30m");
            peli2.setCartelera("https://cdn.kinepolis.es/images/ES/65459BAD-CA99-4711-A97B-E049A5FA94D2/HO00003822/0000005786/El_Gato_con_Botas:_El_%C3%9Altimo_Deseo.jpg");
            peli2.setGenero(TypeGeneroPelicula.AVENTURA);
            peli2.setPegi(PegiEdad.pegi_12);

            Pelicula peli3 = new Pelicula();
            peli3.setTitulo("Posesión Infernal");
            peli3.setSipnosis("El despertar' traslada la acción de los bosques a la ciudad para contar la retorcida historia de dos hermanas distanciadas cuyo reencuentro se ve truncado por la aparición de demonios");
            peli3.setTiempo("1h 30m");
            peli3.setCartelera("https://cdn.kinepolis.es/images/ES/65459BAD-CA99-4711-A97B-E049A5FA94D2/HO00004274/0000006106/El_Piloto.jpg");
            peli3.setGenero(TypeGeneroPelicula.AVENTURA);
            peli3.setPegi(PegiEdad.pegi_18);
            peliculas.add(peli);
            peliculas.add(peli2);
            peliculas.add(peli3);

            //salas
            SalaCine sc1 = new SalaCine();
            sc1.setY_columnas(6);
            sc1.setX_fila(6);
            sc1.setId(1);
            sc1.setTypeSala(Type.FOURDX);

            SalaCine sc2 = new SalaCine();
            sc2.setY_columnas(7);
            sc2.setX_fila(7);
            sc2.setId(2);
            sc2.setTypeSala(Type.NORMAL);

            SalaCine sc3 = new SalaCine();
            sc3.setY_columnas(12);
            sc3.setX_fila(12);
            sc3.setId(3);
            sc3.setTypeSala(Type.TRESD);

            SalaCine sc4 = new SalaCine();
            sc4.setY_columnas(6);
            sc4.setX_fila(6);
            sc4.setId(4);
            sc4.setTypeSala(Type.TRESD);

            salas.add(sc1);
            salas.add(sc2);
            salas.add(sc3);
            salas.add(sc4);

            for(Pelicula pelicula : peliculas){
                ControllerBD.getInstance(context).updatePelicula(pelicula);
            }
            for(Usuario usu : usuarios){
                ControllerBD.getInstance(context).updateUsuario(usu);
            }
            for(SalaCine sCine : salas){
                ControllerBD.getInstance(context).updateSalaCine(sCine);
            }
        }
    }
}
