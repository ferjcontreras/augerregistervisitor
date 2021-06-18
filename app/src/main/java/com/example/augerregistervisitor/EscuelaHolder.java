package com.example.augerregistervisitor;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class EscuelaHolder extends RecyclerView.ViewHolder {
    TextView nombre_colegio;
    TextView cue_colegio;
    TextView direccion_colegio;
    TextView departamento_colegio;
    TextView provincia_colegio;

    public EscuelaHolder(@NonNull View itemView) {
        super(itemView);
        nombre_colegio = (TextView) itemView.findViewById(R.id.nombre_colegio);
        cue_colegio = (TextView) itemView.findViewById(R.id.cue_colegio);
        direccion_colegio = (TextView) itemView.findViewById(R.id.direccion_colegio);
        departamento_colegio = (TextView) itemView.findViewById(R.id.departamento_colegio);
        provincia_colegio = (TextView) itemView.findViewById(R.id.provincia_colegio);
    }
}
