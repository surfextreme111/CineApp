package com.example.cineapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cineapp.Reci.AdapterReciclerAsientos;
import com.example.cineapp.Reci.CarteleraActivity;
import com.example.cineapp.dbUtilidades.ControllerBD;
import com.example.cineapp.model.AsientoComprado;
import com.example.cineapp.model.AsientosDistrib;
import com.example.cineapp.model.Ticket;
import com.example.cineapp.model.SalaCine;
import com.example.cineapp.model.Sesion;
import com.example.cineapp.model.Usuario;
import com.example.cineapp.model.VentaProvisionalSinTerminar;
import com.example.cineapp.model.types.Type;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
public class ComprarEntradasActivity extends AppCompatActivity{

    List<AsientoComprado> asientos;
    TextView totalCompra;
    Button comprar;
    FloatingActionButton btnAtras;
    RecyclerView rcView;
    Sesion sesion;
    Usuario u;
    int euros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);
        totalCompra = findViewById(R.id.todaCompra);
        comprar = findViewById(R.id.btnComprarAsientosNew);
        rcView = findViewById(R.id.reciclerCompra);
        btnAtras = findViewById(R.id.btnAtrasCompra);

        asientos = ((AsientosDistrib) getIntent().getSerializableExtra("asientos")).getAsientos();

        String user = ControllerBD.getInstance(this).getUsuarioApp();
        u = ControllerBD.getInstance(this).getUsuario(user);
        sesion = ControllerBD.getInstance(this).getSesion(getIntent().getExtras().getInt("sesion"));
        SalaCine sc = ControllerBD.getInstance(this).getSalaCine(asientos.get(0).getIdSala());
        euros = 0;

        AdapterReciclerAsientos adapterBut = new AdapterReciclerAsientos(this, asientos);

        rcView.setAdapter(adapterBut);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcView.setLayoutManager(linearLayoutManager);

        for(AsientoComprado a : asientos){
            if(sc.getTypeSala() == Type.NORMAL){
                euros += 7;
            }else if(sc.getTypeSala() == Type.TRESD){
                euros += 9;
            }else if(sc.getTypeSala() == Type.FOURDX){
                euros += 11;
            }
        }

        totalCompra.setText("DINERO A PAGAR: " + euros + "€");

        comprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VentaProvisionalSinTerminar venta = new VentaProvisionalSinTerminar();
                venta.setHora(sesion.getSesionHora());
                venta.setPrecio(euros);
                venta.setId(ControllerBD.getInstance(getApplicationContext()).getLastIndexVenta());

                for(AsientoComprado asiento : asientos){
                    Ticket entrada = new Ticket();
                    entrada.setAsientoX(asiento.getAsientoX());
                    entrada.setAsientoY(asiento.getAsientoY());
                    entrada.setId_CineVenta(venta.getId());
                    entrada.setId_CineSesion(sesion.getId());
                    entrada.setId(ControllerBD.getInstance(getApplicationContext()).getLastIndexEntrada());

                    ControllerBD.getInstance(getApplicationContext()).updateEntrada(entrada);
                }
                ControllerBD.getInstance(getApplicationContext()).updateVenta(venta);

                Toast.makeText(ComprarEntradasActivity.this, "Compra terminada", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ComprarEntradasActivity.this, CarteleraActivity.class);
                intent.putExtra("usu", user);
                startActivity(intent);
            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ComprarEntradasActivity.this, AsientosActivity.class);
                intent.putExtra("usu", user);
                startActivity(intent);
            }
        });
    }
}