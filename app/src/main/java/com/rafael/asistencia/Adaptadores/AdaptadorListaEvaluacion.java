package com.rafael.asistencia.Adaptadores;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.rafael.asistencia.Modelos.DetalleTrabajoAlumno;
import com.rafael.asistencia.R;

import java.util.ArrayList;

/**
 *
 */
public class AdaptadorListaEvaluacion extends ArrayAdapter<DetalleTrabajoAlumno> {
    ArrayList<DetalleTrabajoAlumno> detalleTrabajoAlumnos;
    EditText editTextEvaluacion;

    public AdaptadorListaEvaluacion(Activity context, int resource, ArrayList<DetalleTrabajoAlumno> detalleTrabajoAlumnos) {
        super(context, resource, detalleTrabajoAlumnos);
        this.detalleTrabajoAlumnos = detalleTrabajoAlumnos;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater vi;
        vi = LayoutInflater.from(getContext());
        convertView = vi.inflate(R.layout.item_lista_evaluacion, null);

        DetalleTrabajoAlumno detalleTrabajoAlumno = getItem(position);

        TextView textViewNombre = (TextView) convertView.findViewById(R.id.item_evaluacion_nombre);
        editTextEvaluacion = (EditText) convertView.findViewById(R.id.item_evaluacion_evaluacion);

        textViewNombre.setText(detalleTrabajoAlumno.getNombre());

        editTextEvaluacion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                detalleTrabajoAlumnos.get(position).setEvaluacion(
                        Integer.parseInt(editTextEvaluacion.getText().toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {
                detalleTrabajoAlumnos.get(position).setEvaluacion(
                        Integer.parseInt(editTextEvaluacion.getText().toString()));
            }
        });

        return convertView;
    }
}
