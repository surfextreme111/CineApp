package com.example.cineapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cineapp.Reci.CarteleraActivity;
import com.example.cineapp.dbUtilidades.ControllerBD;
import com.example.cineapp.dbUtilidades.CrearBD;
import com.example.cineapp.model.Usuario;

import java.util.List;

import io.realm.Realm;

public class LogInActivity extends AppCompatActivity {

    private TextView usuario;
    private TextView password;
    private TextView mensaje;
    Button btnLogin;
    Button btnRegister;

    private Realm con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in_activity);

        usuario = findViewById(R.id.usuario_login);
        password = findViewById(R.id.contra_login);
        mensaje = findViewById(R.id.error);

        CrearBD bd = new CrearBD(this);

         /*con = BaseDatos.getInstance().conectar(getBaseContext());
       Long cuantos = con.where(Usuario.class).count();
        if (cuantos==0) {
            //admin, admin
            try {
                Usuario u = new Usuario();
                u.setNombre("admin");
                u.setPassword("admin");
                con.beginTransaction();
                con.copyToRealmOrUpdate(u);
                con.commitTransaction();
            } catch (Exception e) {
                e.printStackTrace();
                mensaje.setText("Error: " + e.getMessage());
            }
        }*/


        btnLogin = findViewById(R.id.btn_iniciarsesion_login);
        btnRegister = findViewById(R.id.btn_register_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usu_login = usuario.getText().toString();

                if (usu_login == null) {
                    mensaje.setText("Usuario no encontrado");
                } else {
                    List<Usuario> usList = ControllerBD.getInstance(getApplicationContext()).getUsers();
                    Usuario u = ControllerBD.getInstance(getApplicationContext()).getUsuario(usu_login);
                    if (u.getPassword().equals(password.getText().toString())) {
                        Intent intent = new Intent(LogInActivity.this, CarteleraActivity.class);
                        intent.putExtra("usu", u.getUsername());
                        startActivity(intent);
                        Toast.makeText(LogInActivity.this, "Estas haciendo login", Toast.LENGTH_LONG).show();
                    } else {
                        mensaje.setText("Contraseña incorrecte");
                        Toast.makeText(LogInActivity.this, "Tus datos son incorrectos", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogInActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}