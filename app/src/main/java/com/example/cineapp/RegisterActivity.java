package com.example.cineapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cineapp.Reci.CarteleraActivity;
import com.example.cineapp.dbUtilidades.ControllerBD;
import com.example.cineapp.model.Usuario;

import io.realm.Realm;

public class RegisterActivity extends AppCompatActivity {

    EditText nombre,apellido,dni,usuario,contraseña;
    private Realm con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        nombre = findViewById(R.id.usuario_login);
        apellido = findViewById(R.id.apellido_register);
        dni = findViewById(R.id.dni_register);
        usuario = findViewById(R.id.usuario_register);
        contraseña = findViewById(R.id.contraseña_register);

        String usu = getIntent().getStringExtra("usu");


        Button btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ur = "";

                if(usu != null){
                    ur = ControllerBD.getInstance(getApplicationContext()).getUsuarioApp();
                }
                String nameString = nombre.getText().toString();
                String contraString = contraseña.getText().toString();
                String usuString = usuario.getText().toString();
                String dniString = dni.getText().toString();
                String apellidoString = apellido.getText().toString();

                    long count = con.where(Usuario.class).equalTo("username", usuString).count();
                    if(count == 0){
                        Usuario u = new Usuario();
                        u.setPassword(contraString);
                        u.setUsername(usuString);
                        u.setNombre(nameString);
                        u.setApellido(apellidoString);
                        u.setDNI(dniString);

                        //register admin  = false
                        u.setAdmin(false);

                        con.beginTransaction();
                        con.copyToRealmOrUpdate(u);
                        con.commitTransaction();

                        Intent intent = new Intent(RegisterActivity.this, CarteleraActivity.class);
                        if(usu != null){
                            intent.putExtra("usu",ur);
                        }else{
                            ControllerBD.getInstance(getApplicationContext()).setUsuarioApp(u);
                            intent.putExtra("usu",ur);
                        }
                        startActivity(intent);
                    }else{
                        Toast.makeText(RegisterActivity.this, "Este nombre no esta disponible", Toast.LENGTH_SHORT).show();
                    }
                }
        });
    }
}


