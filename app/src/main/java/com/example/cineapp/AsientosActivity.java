package com.example.cineapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cineapp.dbUtilidades.ControllerBD;
import com.example.cineapp.model.AsientoComprado;
import com.example.cineapp.model.AsientosDistrib;
import com.example.cineapp.model.SalaCine;
import com.example.cineapp.model.Sesion;
import com.example.cineapp.model.Usuario;

import java.util.LinkedList;
import java.util.List;


public class AsientosActivity extends AppCompatActivity {

    Button btnComprar;
    Button btnAtras;
    List<Integer> listAsientos;
    List<AsientoComprado> asientosOcupados;
    List<AsientoComprado> listAsientosOcupados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asientos);
        listAsientos = new LinkedList<>();
        asientosOcupados = new LinkedList<>();
        listAsientosOcupados = new LinkedList<>();

        String user = ControllerBD.getInstance(this).getUsuarioApp();
        Usuario u = ControllerBD.getInstance(this).getUsuario(user);

        int a = getIntent().getExtras().getInt("sesion");
        Sesion s = ControllerBD.getInstance(this).getSesion(a);
        SalaCine sala = ControllerBD.getInstance(this).getSalaCine(s.getIdSalaCine());

        TableLayout layout = (TableLayout) findViewById(R.id.tablaAsientos);

        TableLayout contenedor = new TableLayout(this);
        contenedor.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        int contadorTotal = 0;
        for (int i = 0; i < sala.getX_fila(); i++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            tableRow.setGravity(Gravity.CENTER);
            for (int j = 0; j < sala.getY_columnas(); j++) {
                CheckBox cb = new CheckBox(this);
                cb.setButtonDrawable(R.drawable.seleccion_asiento);
                cb.setGravity(Gravity.CENTER);
                cb.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
                cb.setId(++contadorTotal);
                cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        int posibleY = 1;
                        int posibleX = buttonView.getId();

                        for (int k = 2, g = 5; k <= sala.getX_fila(); k++, g+=5) {
                            if (posibleX >= 1+g && posibleX <= 5+g){
                                posibleX -= g;
                                posibleY = k;
                            }
                        }

                        AsientoComprado asiento = new AsientoComprado(posibleX, posibleY, sala.getId());
                        if(isChecked){
                            asientosOcupados.add(asiento);
                        }else{
                            asientosOcupados.remove(asiento);
                        }
                    }
                });
                tableRow.addView(cb);
            }
            contenedor.addView(tableRow);
        }

        layout.addView(contenedor);

        btnComprar = findViewById(R.id.btnComprarAsientos);
        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AsientosActivity.this, ComprarEntradasActivity.class);
                intent.putExtra("usu", user);
                AsientosDistrib tot = new AsientosDistrib(asientosOcupados);
                intent.putExtra("asientos", tot);
                intent.putExtra("sesion", a);
                startActivity(intent);
            }
        });
        btnAtras = findViewById(R.id.btnAtrasSelctAsientos);
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AsientosActivity.this, InfoPeliCine.class);
                intent.putExtra("usu", user);
                startActivity(intent);
            }
        });


    }
}