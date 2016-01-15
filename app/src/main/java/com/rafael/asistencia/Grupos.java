package com.rafael.asistencia;

import android.app.Activity;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.rafael.asistencia.Adaptadores.AdaptadorListaGrupos;
import com.rafael.asistencia.BaseDatos.ConexionBaseDatos;
import com.rafael.asistencia.Conexion.ObtenerDatos;
import com.rafael.asistencia.Modelos.Grupo;

import java.util.ArrayList;


public class Grupos extends AppCompatActivity {
    Activity activity;
    ListView listViewGrupos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupos);

        //inicialización de variables
        activity = this;
        listViewGrupos = (ListView) findViewById(R.id.grupos_lista_grupos);

        ConexionBaseDatos conexionBaseDatos = new ConexionBaseDatos(activity);
        ArrayList<Grupo> grupos = conexionBaseDatos.obtenerGrupos();



        AdaptadorListaGrupos adaptadorListaGrupos = new AdaptadorListaGrupos(this,
                R.layout.item_lista_grupos,
                grupos
        );

        listViewGrupos.setAdapter(adaptadorListaGrupos);

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.recarga_grupos);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ObtenerDatos obtenerDatos = new ObtenerDatos(activity);
                obtenerDatos.setListViewGrupos(listViewGrupos);
                obtenerDatos.setSwipeRefreshLayout(swipeRefreshLayout);
                obtenerDatos.execute();
            }
        });
    }
}
