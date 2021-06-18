package com.example.augerregistervisitor;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class NewSchoolFragment extends Fragment {

    private EditText nombre;
    private RecyclerView lista_escuelas;
    private ArrayList<Escuela> lista;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.ingrese_escuela, container, false);
        nombre = (EditText)v.findViewById(R.id.nombre_escuela);
        lista_escuelas= (RecyclerView) v.findViewById(R.id.lista_escuelas);


        // Setting up the Recycler...
        initListaEscuelas();





        nombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Toast.makeText(getContext(), "Cambio a:"+charSequence, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return v;
    }


    private void initListaEscuelas(){

    }
}
