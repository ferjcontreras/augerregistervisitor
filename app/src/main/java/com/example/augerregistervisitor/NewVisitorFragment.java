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

public class NewVisitorFragment extends Fragment {

    private Button btnNuevoVisitante;
    private Button btnNvaEscuela;
    FromNewVisitortoActivity mlistener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.new_visitor, container, false);
        btnNuevoVisitante = (Button)v.findViewById(R.id.nuevo);
        btnNvaEscuela = (Button) v.findViewById(R.id.nuevo_grupo);

        btnNuevoVisitante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Cambiar de Fragmento
                Log.i("DEBUG", "Button pressed");
                mlistener.NextFragment(0);
            }
        });

        btnNvaEscuela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mlistener.NextFragment(1);
            }
        });

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FromNewVisitortoActivity) {
            Log.i("DEBUG", "It is instance!");
            mlistener = (FromNewVisitortoActivity) context;
        }
        else throw new RuntimeException(context.toString()
                + " must implement FromNewVisitortoActivity interface");
    }

    public interface FromNewVisitortoActivity {
        void NextFragment(int fragment);
    }
}

