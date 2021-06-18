package com.example.augerregistervisitor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class EscuelaAdapter extends RecyclerView.Adapter<EscuelaHolder> {

    Context context;
    LayoutInflater inflater;
    ArrayList<Escuela> lista;


    public EscuelaAdapter(Context context, ArrayList<Escuela> lista) {
        this.context = context;
        this.lista = lista;
        inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public EscuelaHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.escuela_item, viewGroup,false);
        EscuelaHolder item = new EscuelaHolder(view);
        return item;
    }

    @Override
    public void onBindViewHolder(@NonNull EscuelaHolder escuelaHolder, int position) {
        escuelaHolder.nombre_colegio.setText(lista.get(position).getNombre());
        escuelaHolder.cue_colegio.setText(lista.get(position).getCUE());
        escuelaHolder.provincia_colegio.setText(lista.get(position).getProvincia());
        escuelaHolder.direccion_colegio.setText(lista.get(position).getDireccion());
        escuelaHolder.departamento_colegio.setText(lista.get(position).getDepartamento());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
