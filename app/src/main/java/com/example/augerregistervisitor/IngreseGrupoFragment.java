package com.example.augerregistervisitor;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class IngreseGrupoFragment extends Fragment {

    private Button next;
    private Button btnAtras;
    private EditText nombre;
    private GroupFragmentActivity mlistener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.ingrese_grupo, container, false);
        next = (Button) v.findViewById(R.id.nextA);
        nombre = (EditText) v.findViewById(R.id.nombre_grupo);
        btnAtras = (Button) v.findViewById(R.id.btnAtras);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(), "Pressed!", Toast.LENGTH_SHORT).show();
                mlistener.FragmentToQuantityGroup(nombre.getText().toString());
            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mlistener.FromIngreseGrupoToBack();
            }
        });
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof LocationFragment.FromLocationToActivity) {
            Log.i("DEBUG", "It is instance!");
            mlistener = (GroupFragmentActivity) context;
        }
        else throw new RuntimeException(context.toString()
                + " must implement FromQuantityToActivity interface");
    }

    interface GroupFragmentActivity {
        void FragmentToQuantityGroup(String nombre);
        void FromIngreseGrupoToBack();
    }
}
