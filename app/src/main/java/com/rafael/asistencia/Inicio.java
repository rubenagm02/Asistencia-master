package com.rafael.asistencia;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rafael.asistencia.Conexion.Login;

public class Inicio extends AppCompatActivity {

    Button buttonInicioSesion;
    EditText editTextUsuario;
    EditText editTextPassword;
    Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        //Inicialización de variables
        contexto            = getApplicationContext();
        buttonInicioSesion  = (Button) findViewById(R.id.inicio_boton_iniciar_sesion);
        editTextPassword    = (EditText) findViewById(R.id.inicio_text_password);
        editTextUsuario     = (EditText) findViewById(R.id.inicio_text_usuario);

        SharedPreferences sharedPreferences =
                getSharedPreferences("asistencias", Context.MODE_PRIVATE);

        if (sharedPreferences.getInt("Id", -1) != -1) {
            Intent intent = new Intent(this, Grupos.class);
            startActivity(intent);
            finish();
        }

        //on click Inicio de sesión
        buttonInicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login login = new Login(editTextUsuario, editTextPassword, Inicio.this);
                login.login();

            }
        });



    }
}
