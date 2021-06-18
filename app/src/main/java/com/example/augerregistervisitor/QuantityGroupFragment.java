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

public class QuantityGroupFragment extends Fragment {


    // Botones de cantidad
    private Button restar;
    private Button restar10;
    private Button sumar;
    private Button sumar10;
    private EditText quantity;

    // Botones de manejo de edades
    private Button masDesde;
    private Button menosDesde;

    private Button masHasta;
    private Button menosHasta;

    private EditText edadDesde;
    private EditText edadHasta;


    // listener
    QGroupActivity mlistener;

    private Button next;
    private Button btnAtras;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.quantity_group, container, false);

        restar = (Button) v.findViewById(R.id.restar);
        restar10 = (Button) v.findViewById(R.id.restar10);
        sumar = (Button) v.findViewById(R.id.sumar);
        sumar10 = (Button) v.findViewById(R.id.sumar10);
        quantity = (EditText) v.findViewById(R.id.numerovisitantes);

        masDesde = (Button)v.findViewById(R.id.masDesde);
        menosDesde = (Button)v.findViewById(R.id.menosDesde);
        masHasta = (Button) v.findViewById(R.id.masHasta);
        menosHasta = (Button) v.findViewById(R.id.menosHasta);
        edadDesde = (EditText)v.findViewById(R.id.edadDesde);
        edadHasta = (EditText) v.findViewById(R.id.edadHasta);

        next = (Button)v.findViewById(R.id.nextC);
        btnAtras = (Button) v.findViewById(R.id.btnAtras);


        // Cantidad de gente...
        restar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numb = Integer.parseInt(quantity.getText().toString());
                if (numb > 1) {
                    numb = numb - 1;
                    quantity.setText(numb+"");
                }
            }
        });
        sumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numb = Integer.parseInt(quantity.getText().toString());
                numb = numb + 1;
                quantity.setText(numb+"");
            }
        });
        restar10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numb = Integer.parseInt(quantity.getText().toString());
                if (numb > 10) {
                    numb = numb - 10;
                    quantity.setText(numb+"");
                }
            }
        });
        sumar10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numb = Integer.parseInt(quantity.getText().toString());
                numb = numb + 10;
                quantity.setText(numb+"");
            }
        });



        // Edades

        menosDesde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numb = Integer.parseInt(edadDesde.getText().toString());
                if (numb > 1) {
                    numb = numb - 1;
                    edadDesde.setText(numb+"");
                }
            }
        });
        masDesde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numb = Integer.parseInt(edadDesde.getText().toString());
                numb = numb + 1;
                edadDesde.setText(numb+"");

            }
        });


        menosHasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numb = Integer.parseInt(edadHasta.getText().toString());
                if (numb > 1) {
                    numb = numb - 1;
                    edadHasta.setText(numb+"");
                }
            }
        });
        masHasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numb = Integer.parseInt(edadHasta.getText().toString());
                numb = numb + 1;
                edadHasta.setText(numb+"");
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mlistener.Confirm(Integer.parseInt(quantity.getText().toString()), Integer.parseInt(edadDesde.getText().toString()), Integer.parseInt(edadHasta.getText().toString()));
            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mlistener.FromQtyGroupToBack();
            }
        });

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof LocationFragment.FromLocationToActivity) {
            Log.i("DEBUG", "It is instance!");
            mlistener = (QGroupActivity) context;
        }
        else throw new RuntimeException(context.toString()
                + " must implement FromQuantityToActivity interface");
    }

    interface QGroupActivity {
        void Confirm(int quantity, int edadDesde, int edadHasta);
        void FromQtyGroupToBack();
    }
}
