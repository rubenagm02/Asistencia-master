package com.rafael.asistencia.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.rafael.asistencia.Modelos.AsistenciasAlumno;
import com.rafael.asistencia.R;

import java.util.ArrayList;

/**
 * Adaptador
 */
public class AdaptadorListaAsistencias extends ArrayAdapter<AsistenciasAlumno> {
    Context contexto;
    CheckBox checkBoxAsistencia;
    CheckBox checkBoxRetardo;
    CheckBox checkBoxFalta;
    ArrayList<AsistenciasAlumno> asistencias;

    public AdaptadorListaAsistencias(
            Context context, int resource, ArrayList<AsistenciasAlumno> asistencias) {
        super(context, resource, asistencias);
        this.contexto = context;
        this.asistencias = asistencias;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater vi;
        vi = LayoutInflater.from(getContext());
        convertView = vi.inflate(R.layout.item_lista_asistencia, null);

        final AsistenciasAlumno asistencia = getItem(position);
        TextView textViewNombre = (TextView) convertView.findViewById(R.id.item_asistencia_nombre);
        checkBoxAsistencia = (CheckBox) convertView.findViewById(R.id.item_check_asistencia);
        checkBoxRetardo = (CheckBox) convertView.findViewById(R.id.item_check_retardo);
        checkBoxFalta = (CheckBox) convertView.findViewById(R.id.item_check_falta);

        textViewNombre.setText(asistencia.getNombre());

        checkBoxAsistencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desmarcarCheckBox(checkBoxAsistencia);
                asistencias.get(position).setAsistencia("Asistencia");
            }
        });

        checkBoxFalta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desmarcarCheckBox(checkBoxFalta);
                asistencias.get(position).setAsistencia("Falta");
            }
        });

        checkBoxRetardo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desmarcarCheckBox(checkBoxRetardo);
                asistencias.get(position).setAsistencia("Retardo");
            }
        });
        return convertView;
    }

    public void desmarcarCheckBox(CheckBox checkBox){

        if (checkBox == checkBoxAsistencia) {
            checkBoxAsistencia.setChecked(true);
            checkBoxFalta.setChecked(false);
            checkBoxRetardo.setChecked(false);

        } else if (checkBox == checkBoxFalta) {
            checkBoxAsistencia.setChecked(false);
            checkBoxFalta.setChecked(true);
            checkBoxRetardo.setChecked(false);
        } else if (checkBox == checkBoxRetardo) {
            checkBoxAsistencia.setChecked(false);
            checkBoxFalta.setChecked(false);
            checkBoxRetardo.setChecked(true);
        }
    }
}
