package com.example.cineapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cineapp.Reci.CarteleraActivity;
import com.example.cineapp.dbUtilidades.ControllerBD;
import com.example.cineapp.model.Pelicula;
import com.example.cineapp.model.Usuario;
import com.example.cineapp.model.types.TypeGeneroPelicula;
import com.example.cineapp.model.types.PegiEdad;

public class CrearPeliculaCine extends AppCompatActivity {

    EditText titulo, tiempo, descripcion, url;
    Spinner spinerGenero, spinner2_pegi;

    Button btnAtras, btnCrearPeli;
    CheckBox cb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hacer_pelicula);

        String user = ControllerBD.getInstance(this).getUsuarioApp();
        Usuario u = ControllerBD.getInstance(this).getUsuario(user);

        ArrayAdapter<TypeGeneroPelicula> adGenero = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, TypeGeneroPelicula.values());
        spinerGenero = findViewById(R.id.spinner_generoPeli);
        spinerGenero.setAdapter(adGenero);

        ArrayAdapter<PegiEdad> pegiEdad = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, PegiEdad.values());
        spinner2_pegi = findViewById(R.id.spinner2_pegi);
        //checkbuton = findViewById(R.id.isInCartelera);
        spinner2_pegi.setAdapter(pegiEdad);

        titulo = findViewById(R.id.titulo_crear_peli);
        tiempo = findViewById(R.id.tiempopeli_crear_peli2);
        descripcion = findViewById(R.id.descrippeli_crear_peli3);
        url = findViewById(R.id.url_imagenPeliCrear);

        btnAtras = findViewById(R.id.btnAtrasCrearPeli);
        btnCrearPeli = findViewById(R.id.btnCrearPelicula);

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CrearPeliculaCine.this, CarteleraActivity.class);
                intent.putExtra("usu", user);
                startActivity(intent);
            }
        });

        btnCrearPeli.setOnClickListener(new View.OnClickListener() {
            Pelicula peli = new Pelicula();
            @Override
            public void onClick(View v) {
                if(!tiempo.getText().toString().isEmpty() && !descripcion.getText().toString().isEmpty() && !url.getText().toString().isEmpty()){
                    peli.setTitulo(titulo.getText().toString());
                    peli.setGenero(spinerGenero.getSelectedItem().toString());
                    peli.setTiempo(tiempo.getText().toString());
                    peli.setSipnosis(descripcion.getText().toString());
                    peli.setPegi((PegiEdad) spinner2_pegi.getSelectedItem());

                    ControllerBD.getInstance(getApplicationContext()).updatePelicula(peli);
                    
                    Toast.makeText(CrearPeliculaCine.this, "Creando Pelicula", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(CrearPeliculaCine.this, "Campos vacios, completalos.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}