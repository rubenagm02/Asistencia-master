package com.rafael.asistencia.Adaptadores;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rafael.asistencia.Asistencia;
import com.rafael.asistencia.Modelos.Grupo;
import com.rafael.asistencia.R;
import com.rafael.asistencia.Trabajos;

import java.util.ArrayList;

/**
 * Adaptador
 */
public class AdaptadorListaGrupos extends ArrayAdapter<Grupo> {

    ArrayList<Grupo> grupos;
    Activity context;
    ;

    public AdaptadorListaGrupos(Activity context, int resource, ArrayList<Grupo> grupos) {
        super(context, resource, grupos);
        this.grupos = grupos;
        this.context = context;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater vi;
        vi = LayoutInflater.from(getContext());
        convertView = vi.inflate(R.layout.item_lista_grupos, null);
        final Grupo grupo = getItem(position);
        TextView textViewNombre = (TextView) convertView.findViewById(R.id.item_grupos_nombre);
        //TextView textViewDatos  = (TextView) convertView.findViewById(R.id.item_grupos_adicionales);
        LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.item_linear_grupo);
        final LinearLayout linearLayout1 = (LinearLayout) convertView.findViewById(R.id.item_botones_grupo);

        RelativeLayout relativeLayoutAsistencias = (RelativeLayout) convertView.findViewById(R.id.item_boton_asistencias_grupo);
        RelativeLayout relativeLayoutTrabajos = (RelativeLayout) convertView.findViewById(R.id.item_boton_trabajos_grupo);

        textViewNombre.setText(grupo.getNombre());

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout1.setVisibility(View.VISIBLE);
            }
        });

        relativeLayoutAsistencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Asistencia.class);
                intent.putExtra("IdGrupo", grupo.getNombre());
                context.startActivity(intent);
            }
        });

        relativeLayoutTrabajos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Trabajos.class);
                intent.putExtra("IdGrupo", grupo.getNombre());

                context.startActivity(intent);
            }
        });



        return convertView;
    }

}
