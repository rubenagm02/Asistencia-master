package com.rafael.asistencia;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.rafael.asistencia.Adaptadores.AdaptadorListaEvaluacion;
import com.rafael.asistencia.BaseDatos.ConexionBaseDatos;
import com.rafael.asistencia.Modelos.Alumno;
import com.rafael.asistencia.Modelos.DetalleGrupoAlumno;
import com.rafael.asistencia.Modelos.DetalleTrabajoAlumno;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Evaluacion extends AppCompatActivity {
    Context contexto;
    TextView textViewNombre;
    TextView textViewFecha;
    TextView textViewDescripcion;
    ListView listViewEvaluacion;
    Button buttonGuardar;
    int idTrabajo;

    ConexionBaseDatos conexionBaseDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluacion);

        //inicialización de variables
        contexto = getApplicationContext();
        textViewDescripcion = (TextView) findViewById(R.id.evaluacion_text_descripcion);
        textViewFecha = (TextView) findViewById(R.id.evaluacion_text_fecha_creacion);
        textViewNombre = (TextView) findViewById(R.id.evaluacion_text_nombre_trabajo);
        buttonGuardar = (Button) findViewById(R.id.evaluacion_boton_guardar);
        conexionBaseDatos = new ConexionBaseDatos(contexto);

        listViewEvaluacion = (ListView) findViewById(R.id.evaluacion_lista_evaluacion);

        ArrayList<DetalleGrupoAlumno> detalleGrupoAlumnos = conexionBaseDatos.obtenerAlumnosGrupos();
        final ArrayList<DetalleTrabajoAlumno> detalleTrabajoAlumnos = new ArrayList<>();

        for (DetalleGrupoAlumno detalleGrupoAlumno : detalleGrupoAlumnos) {

            DetalleTrabajoAlumno detalleTrabajoAlumno = new DetalleTrabajoAlumno(
                    idTrabajo ,detalleGrupoAlumno.getIdAlumno(), -1, "");

            Alumno alumno = conexionBaseDatos.obtenerAlumno(detalleGrupoAlumno.getIdAlumno());

            detalleTrabajoAlumno.setNombre(alumno.getNombre());

            detalleTrabajoAlumnos.add(detalleTrabajoAlumno);
        }

        AdaptadorListaEvaluacion adaptadorListaEvaluacion = new AdaptadorListaEvaluacion(
                this, R.layout.item_lista_evaluacion, detalleTrabajoAlumnos);

        listViewEvaluacion.setAdapter(adaptadorListaEvaluacion);

        //Se guarda la evaluación
        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (DetalleTrabajoAlumno detalleTrabajoAlumno : detalleTrabajoAlumnos) {
                    conexionBaseDatos.insertarDetalleTrabajoAlumno(detalleTrabajoAlumno);
                }
            }
        });
    }
}
