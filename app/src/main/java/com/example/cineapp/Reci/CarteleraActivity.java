package com.example.cineapp.Reci;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cineapp.CrearPeliculaCine;
import com.example.cineapp.CrearSesionCine;
import com.example.cineapp.InfoPeliCine;
import com.example.cineapp.R;
import com.example.cineapp.RegisterActivity;
import com.example.cineapp.dbUtilidades.ControllerBD;
import com.example.cineapp.model.Pelicula;
import com.example.cineapp.model.Usuario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import io.realm.Realm;


public class CarteleraActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView reciclerPeliculas;
    List<Pelicula> listaPelis;
    ImageView img;
    private Realm con;
    Button atras;
    FloatingActionButton crearPelicula;
    FloatingActionButton crearSesion;

    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cartelera_activity);
        atras = findViewById(R.id.btnAtrasCartelera);
        crearPelicula = findViewById(R.id.crearPelicula);
        crearSesion = findViewById(R.id.crearSesion);
        img = findViewById(R.id.fondo_degradado);

        user = getIntent().getStringExtra("usu");
        Usuario u = ControllerBD.getInstance(this).getUsuario(user);
        /*if(!(u.isAdmin())){//usu no admin solo ve btn atras
            atras.setVisibility(View.VISIBLE);
            crearSesion.setVisibility(View.INVISIBLE);
            crearPelicula.setVisibility(View.INVISIBLE);
        }else{*///usu admin ve todo
            atras.setVisibility(View.VISIBLE);
            crearSesion.setVisibility(View.VISIBLE);
            crearPelicula.setVisibility(View.VISIBLE);

        //localPeliculas();
        //localSalas();

        listaPelis = ControllerBD.getInstance(getApplicationContext()).getAllPeliculas();

        //reciclerView con las peliculas id del view
        reciclerPeliculas = findViewById(R.id.ReciclerPelis);

        AdapterReciclerCine adapterCine = new AdapterReciclerCine(this, listaPelis);
        adapterCine.setOnClickListener(this);

        reciclerPeliculas.setAdapter(adapterCine);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        reciclerPeliculas.setLayoutManager(linearLayoutManager);


        //Boton atras revisar
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CarteleraActivity.this, RegisterActivity.class);
                int i = reciclerPeliculas.getChildAdapterPosition(v);
                intent.putExtra("film",listaPelis.get(i).getTitulo());
                intent.putExtra("usu", user);
                startActivity(intent);
            }
        });

        crearPelicula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CarteleraActivity.this, CrearPeliculaCine.class);
                intent.putExtra("usu", user);
                startActivity(intent);
            }
        });

        crearSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CarteleraActivity.this, CrearSesionCine.class);
                intent.putExtra("usu", user);
                startActivity(intent);
            }
        });

    }

   /*private void localSalas() {
    //lista de pelis
        //conex
        con = BaseDatos.getInstance().conectar(getBaseContext());
        Long cuantos = con.where(SalaCine.class).count();

        if (cuantos == 0) {
            SalaCine sc1 = new SalaCine();
            sc1.setColumnas(6);
            sc1.setFilas(6);
            sc1.setId(1);
            sc1.setTipo(Type.FOURDX);

            SalaCine sc2 = new SalaCine();
            sc2.setColumnas(7);
            sc2.setFilas(7);
            sc2.setId(2);
            sc2.setTipo(Type.NORMAL);

            SalaCine sc3 = new SalaCine();
            sc3.setColumnas(12);
            sc3.setFilas(12);
            sc3.setId(3);
            sc3.setTipo(Type.TRESD);

            SalaCine sc4 = new SalaCine();
            sc4.setColumnas(6);
            sc4.setFilas(6);
            sc4.setId(4);
            sc4.setTipo(Type.TRESD);

            //añadimos slas
            con.beginTransaction();
            con.copyToRealmOrUpdate(sc1);
            con.beginTransaction();
            con.copyToRealmOrUpdate(sc2);
            con.beginTransaction();
            con.copyToRealmOrUpdate(sc3);
            con.beginTransaction();
            con.copyToRealmOrUpdate(sc4);
            con.commitTransaction();

        }
    }

    private void localPeliculas() {
        con = BaseDatos.getInstance().conectar(getBaseContext());
        Long cuantos = con.where(Pelicula.class).count();

        Pelicula p1 = new Pelicula("Jurasic Park World Dominio", "2h 30m","Lagartos Antiguos","Aventura",12);

        con.beginTransaction();
        con.copyToRealmOrUpdate(p1);
        con.commitTransaction();
        //añadimos todas las peliculas a la base de datos
    }*/

    @Override
    public void onClick(View v) {
        Intent myIntent = new Intent(CarteleraActivity.this, InfoPeliCine.class);
        int i = reciclerPeliculas.getChildAdapterPosition(v);
        myIntent.putExtra("film", listaPelis.get(i).getTitulo());
        myIntent.putExtra("usu", user);
        startActivity(myIntent);
    }
}