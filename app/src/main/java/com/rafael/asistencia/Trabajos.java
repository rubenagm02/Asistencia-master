package com.rafael.asistencia;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.rafael.asistencia.Adaptadores.AdaptadorListaTrabajos;
import com.rafael.asistencia.BaseDatos.ConexionBaseDatos;
import com.rafael.asistencia.Modelos.DetalleGrupoAlumno;
import com.rafael.asistencia.Modelos.Trabajo;

import java.util.ArrayList;
import java.util.List;

public class Trabajos extends AppCompatActivity {
    Context contexto;
    ListView listViewTrabajos;
    ConexionBaseDatos conexionBaseDatos;
    int idGrupo = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trabajos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //inicialización de variables
        contexto = getApplicationContext();
        listViewTrabajos = (ListView) findViewById(R.id.trabajos_lista_trabajos);
        conexionBaseDatos = new ConexionBaseDatos(contexto);

        ArrayList<Trabajo> trabajos = conexionBaseDatos.obtenerTrabajosGrupo(idGrupo);

        AdaptadorListaTrabajos adaptadorListaTrabajos = new AdaptadorListaTrabajos(
                this, R.layout.item_lista_trabajos, trabajos);

        listViewTrabajos.setAdapter(adaptadorListaTrabajos);

        //agregar un trabajo
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.trabajos_boton_nuevo_trabajo);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Al presionar se mostrará un nuevo formulario para agregar trabajos al grupo", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

}
