package com.example.augerregistervisitor;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class QuantityFragment extends Fragment {

    private Button sumar;
    private Button restar;
    private Button nextC;
    private Button btnAtras;
    private EditText nvisitantes;
    private FromQuantityToActivity mlistener;
    private RecyclerView lista_edades;
    private EdadAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.cantidad_visitantes, container, false);

        sumar = (Button)v.findViewById(R.id.sumar);
        restar = (Button) v.findViewById(R.id.restar);
        nvisitantes = (EditText) v.findViewById(R.id.numerovisitantes);
        nextC = (Button) v.findViewById(R.id.nextC);
        lista_edades = (RecyclerView)v.findViewById(R.id.listaedades);
        btnAtras = (Button) v.findViewById(R.id.btnAtras);


        sumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numb = Integer.parseInt(nvisitantes.getText().toString());
                numb = numb + 1;
                nvisitantes.setText(numb+"");
                adapter.Agregar();
            }
        });

        restar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numb = Integer.parseInt(nvisitantes.getText().toString());
                if (numb > 1 ) {
                    numb = numb - 1;
                    nvisitantes.setText(numb+"");
                    adapter.Quitar();
                }
            }
        });

        nextC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("A ver", adapter.getListadoEdades().get(0)+"");
                mlistener.ConrfirmData(Integer.parseInt(nvisitantes.getText().toString()), adapter.getListadoEdades());
            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mlistener.FromQtyToBack();
            }
        });


        // Recycler View
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(16); // We add just the first value for one person

        lista_edades.setHasFixedSize(true);
        lista_edades.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new EdadAdapter(getContext(), list);
        lista_edades.setAdapter(adapter);




        return v;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof LocationFragment.FromLocationToActivity) {
            Log.i("DEBUG", "It is instance!");
            mlistener = (FromQuantityToActivity) context;
        }
        else throw new RuntimeException(context.toString()
                + " must implement FromQuantityToActivity interface");
    }

    public interface FromQuantityToActivity {
        void ConrfirmData(int quantity, ArrayList<Integer> edades);
        void FromQtyToBack();
    }
}
