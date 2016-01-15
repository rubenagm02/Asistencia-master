package com.rafael.asistencia.Adaptadores;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rafael.asistencia.Evaluacion;
import com.rafael.asistencia.Modelos.Trabajo;
import com.rafael.asistencia.R;


import java.util.ArrayList;

/**
 * Created by ruben on 24/11/15.
 */
public class AdaptadorListaTrabajos extends ArrayAdapter<Trabajo> {
    TextView textViewNombre;
    TextView textViewFecha;
    Activity activity;

    public AdaptadorListaTrabajos(Activity context, int resource, ArrayList<Trabajo> trabajos) {
        super(context, resource, trabajos);
        this.activity = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater vi;
        vi = LayoutInflater.from(getContext());
        convertView = vi.inflate(R.layout.item_lista_trabajos, null);

        Trabajo trabajo = getItem(position);

        textViewNombre = (TextView) convertView.findViewById(R.id.item_text_nombre_trabajo);
        textViewFecha = (TextView) convertView.findViewById(R.id.item_text_fecha_trabajo);

        textViewNombre.setText(trabajo.getNombre());
        textViewFecha.setText(trabajo.getFechaAlta());

        LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.item_trabajos_layout);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, Evaluacion.class);
                activity.startActivity(intent);
            }
        });

        return convertView;
    }
}
