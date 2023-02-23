package com.example.cineapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cineapp.Reci.AdapterComienzaPelicula;
import com.example.cineapp.Reci.CarteleraActivity;
import com.example.cineapp.dbUtilidades.ControllerBD;
import com.example.cineapp.model.Pelicula;
import com.example.cineapp.model.Sesion;
import com.example.cineapp.model.Usuario;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InfoPeliCine extends AppCompatActivity implements View.OnClickListener {
    Pelicula peli;
    String user;
    RecyclerView rv_Sesiones;
    ImageView img;
    TextView tiempo;
    TextView sipnosis;
    TextView titulo;
    List<Sesion> listaSesiones;

    Button btnAtras;

    CheckBox ponerCartelera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_peli);

        user = ControllerBD.getInstance(this).getUsuarioApp();
        Usuario u = ControllerBD.getInstance(this).getUsuario(user);
        String film = getIntent().getStringExtra("film");
        peli = ControllerBD.getInstance(this).getPelicula(film);

        ponerCartelera = findViewById(R.id.ponerQuitarCartelera);

        listaSesiones = ControllerBD.getInstance(this).getAllHoursSesionsFromPeliculasHoy(peli);
        rv_Sesiones = findViewById(R.id.sesionRecicler);
        AdapterComienzaPelicula adapterHora = new AdapterComienzaPelicula(this, listaSesiones);
        adapterHora.setOnClickListener(this);

        rv_Sesiones.setAdapter(adapterHora);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_Sesiones.setLayoutManager(linearLayoutManager);
        img = findViewById(R.id.fotoPeli);
        titulo = findViewById(R.id.tituloInfoPeli);
        tiempo = findViewById(R.id.tiempoInfoPeli);
        sipnosis = findViewById(R.id.sipnoInfoPeli);
        Picasso.get().load(peli.getCartelera()).into(img);
        titulo.setText(peli.getTitulo());
        tiempo.setText(peli.getTiempo());
        sipnosis.setText(peli.getSipnosis());
        ponerCartelera.setChecked(peli.isPublicadaPeli());

        btnAtras = findViewById(R.id.btnInfoAtrasPelicula);
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoPeliCine.this, CarteleraActivity.class);
                intent.putExtra("usu", user);
                startActivity(intent);
            }
        });

        ponerCartelera.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Pelicula peli = new Pelicula();
                peli.setCartelera(InfoPeliCine.this.peli.getCartelera());
                peli.setPublicadaPeli(InfoPeliCine.this.peli.isPublicadaPeli());
                peli.setPegi(InfoPeliCine.this.peli.getPegi());
                peli.setSipnosis(InfoPeliCine.this.peli.getSipnosis());
                peli.setTitulo(InfoPeliCine.this.peli.getTitulo());
                peli.setTiempo(InfoPeliCine.this.peli.getTiempo());
                peli.setGenero(InfoPeliCine.this.peli.getGenero());
                peli.setPublicadaPeli(isChecked);
                ControllerBD.getInstance(getApplicationContext()).updatePelicula(InfoPeliCine.this.peli);
            }
        });
    }

    @Override
    public void onClick(View v) {
        int i = rv_Sesiones.getChildAdapterPosition(v);
        Intent intent = new Intent(InfoPeliCine.this, AsientosActivity.class);
        intent.putExtra("sesion", listaSesiones.get(i).getId());
        intent.putExtra("usu", user);
        startActivity(intent);
    }
}