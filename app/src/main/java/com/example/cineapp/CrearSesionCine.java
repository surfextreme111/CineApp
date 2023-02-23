package com.example.cineapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cineapp.Reci.CarteleraActivity;
import com.example.cineapp.dbUtilidades.ControllerBD;
import com.example.cineapp.model.Pelicula;
import com.example.cineapp.model.SalaCine;
import com.example.cineapp.model.Sesion;
import com.example.cineapp.model.Usuario;
import com.example.cineapp.model.types.Type;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class CrearSesionCine extends AppCompatActivity {

    Spinner selectFormatoSala, selectNumSala, selectPelicula;
    EditText hora;

    Button btncrearSesion, btnAtras;
    List<SalaCine> listSalasCine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hacer_sesion);

        String user = ControllerBD.getInstance(this).getUsuarioApp();
        Usuario u = ControllerBD.getInstance(this).getUsuario(user);

        selectFormatoSala = findViewById(R.id.select_formatoSalaPelicula);
        selectPelicula = findViewById(R.id.selectPelicula);
        selectNumSala = findViewById(R.id.select_numSala);
        hora = findViewById(R.id.editText_horaSesion);

        ArrayAdapter<Type> ar = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Type.values());
        ArrayAdapter<Pelicula> adFilm = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ControllerBD.getInstance(this).getAllPeliculas());

        selectFormatoSala.setAdapter(ar);
        selectPelicula.setAdapter(adFilm);

        selectFormatoSala.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listSalasCine = ControllerBD.getInstance(getApplicationContext()).getAllSalasCine((Type) selectFormatoSala.getSelectedItem());
                ArrayAdapter<SalaCine> adSalas = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, listSalasCine);
                selectNumSala.setAdapter(adSalas);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btncrearSesion = findViewById(R.id.btnCrearPelicula);
        btnAtras = findViewById(R.id.btnAtrasSesion);

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CrearSesionCine.this, CarteleraActivity.class);
                intent.putExtra("usu", user);
                startActivity(intent);
            }
        });

        btncrearSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int salaText = Integer.parseInt(selectNumSala.getSelectedItem().toString());
                    SalaCine sala = ControllerBD.getInstance(getApplicationContext()).getSalaCine(salaText);

                    String peliculaText = selectPelicula.getSelectedItem().toString();
                    Pelicula peli = ControllerBD.getInstance(getApplicationContext()).getPelicula(peliculaText);
                    Date fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(hora.getText().toString());
                    int ultimo = ControllerBD.getInstance(getApplicationContext()).getLastIndexSesion();
                    ultimo++;
                    Sesion sesi = new Sesion();
                    sesi.setId(ultimo);
                    sesi.setSesionHora(fecha);
                    sesi.setIdPelicula(peli.getTitulo());
                    sesi.setIdSalaCine(sala.getId());
                    ControllerBD.getInstance(getApplicationContext()).updateSesion(sesi);
                    Toast.makeText(CrearSesionCine.this, "Creando sesion", Toast.LENGTH_SHORT).show();
                    hora.setText("");
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}