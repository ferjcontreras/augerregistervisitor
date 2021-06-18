package com.example.augerregistervisitor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.ArrayList;

public class EdadAdapter extends  RecyclerView.Adapter<EdadItemHolder> {

    Context context;
    LayoutInflater inflater;
    ArrayList<Integer> listado_edades;
    int lastPosition;

    public EdadAdapter (Context context, ArrayList<Integer> lista) {
        this.context = context;
        listado_edades = lista;
        inflater = LayoutInflater.from(context);
        Log.i("Adapter", "tamanio: "+listado_edades.size());
        lastPosition = 0;
    }


    public void Agregar(){ // se agrega un item de edad
        listado_edades.add(16); // agregamos la edad de 16
        Log.i("Adapter", "Agregamos...");
        notifyDataSetChanged();
    }

    public void Quitar() { // quitamos in item de edad
        listado_edades.remove(listado_edades.size()-1); // remove the last element from the list...
        notifyDataSetChanged();
        lastPosition--;
    }

    public ArrayList<Integer> getListadoEdades() {
        return this.listado_edades;
    }

    @NonNull
    @Override
    public EdadItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //Log.i("Adapter", "Hola 1");
        View view = inflater.inflate(R.layout.recycler_item, viewGroup,false);
        //Log.i("Adapter", "Hola 2");
        EdadItemHolder item = new EdadItemHolder(view);
        return item;
    }

    @Override
    public void onBindViewHolder(@NonNull final EdadItemHolder edadItemHolder, final int i) {
        edadItemHolder.edad.setText(""+listado_edades.get(i));
        edadItemHolder.mas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int edad = Integer.parseInt(edadItemHolder.edad.getText().toString());
                edad++;
                edadItemHolder.edad.setText(edad+"");
                Log.i("Adapter", "Edad: "+edad + " position:"+i);
                listado_edades.set(i, edad);
            }
        });
        setAnimation(edadItemHolder.itemView, i);

        edadItemHolder.menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int edad = Integer.parseInt(edadItemHolder.edad.getText().toString());
                if (edad > 0) {
                    edad--;
                    edadItemHolder.edad.setText(edad + "");
                    listado_edades.set(i, edad);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listado_edades.size();
    }


    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}
