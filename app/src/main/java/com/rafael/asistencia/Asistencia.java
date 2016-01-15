package com.rafael.asistencia;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.rafael.asistencia.Adaptadores.AdaptadorListaAsistencias;
import com.rafael.asistencia.BaseDatos.ConexionBaseDatos;
import com.rafael.asistencia.Modelos.Alumno;
import com.rafael.asistencia.Modelos.AsistenciasAlumno;
import com.rafael.asistencia.Modelos.DetalleGrupoAlumno;

import java.util.ArrayList;

public class Asistencia extends AppCompatActivity {
    Context contexto;
    TextView textViewFecha;
    ListView listViewAsistencia;
    Button buttonGuardar;
    ArrayList<AsistenciasAlumno> asistenciasAlumnos;
    ConexionBaseDatos conexionBaseDatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asistencia);

        //Inicialización de variables
        contexto           = getApplicationContext();
        textViewFecha      = (TextView) findViewById(R.id.asistencia_text_dia_asistencia);
        listViewAsistencia = (ListView) findViewById(R.id.asistencia_lista_asistencia);
        buttonGuardar      = (Button) findViewById(R.id.asistencia_boton_guardar);

        conexionBaseDatos = new ConexionBaseDatos(contexto);

        ArrayList<DetalleGrupoAlumno> detalleGrupoAlumnos = conexionBaseDatos.obtenerAlumnosGrupos();
        asistenciasAlumnos = new ArrayList<>();

        for (DetalleGrupoAlumno detalleGrupoAlumno : detalleGrupoAlumnos) {
            Alumno alumno = conexionBaseDatos.obtenerAlumno(detalleGrupoAlumno.getIdAlumno());

            AsistenciasAlumno asistenciasAlumno = new AsistenciasAlumno(
                    detalleGrupoAlumno.getIdDetalleGrupoUsuario(),
                    detalleGrupoAlumno.getIdAlumno(),
                    "2015-11-11",
                    "No marcada");
            asistenciasAlumno.setNombre(alumno.getNombre());

            asistenciasAlumnos.add(asistenciasAlumno);

        }

        AdaptadorListaAsistencias adaptadorListaAsistencias = new AdaptadorListaAsistencias(
                contexto, R.id.asistencia_lista_asistencia ,asistenciasAlumnos);

        listViewAsistencia.setAdapter(adaptadorListaAsistencias);

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (AsistenciasAlumno asistenciasAlumno : asistenciasAlumnos) {
                    conexionBaseDatos.insertarAsistenciaAlumno(asistenciasAlumno);
                }
            }
        });
    }
}
