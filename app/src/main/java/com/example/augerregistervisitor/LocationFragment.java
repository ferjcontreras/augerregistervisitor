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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class LocationFragment extends Fragment {


    private Button next;
    private Button btnAtras;
    private Spinner paises;
    private Spinner provincias;
    private Spinner departamentos;
    private ArrayList<TextItem> listaPaises;
    private ArrayList<TextItem> listaProvincias;
    private ArrayList<TextItem> listaDeptos;

    // Current selection
    private int currentCountryID;
    private int currentProvinceID;
    private int currentDeptoID;
    private String currentCountryName;
    private String currentProvinceName;
    private String currentDeptoName;

    private FromLocationToActivity mlistener;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FromLocationToActivity) {
            Log.i("DEBUG", "It is instance!");
            mlistener = (FromLocationToActivity) context;
        }
        else throw new RuntimeException(context.toString()
                + " must implement FromLocationtoActivity interface");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.location, container, false);

        paises = (Spinner) v.findViewById(R.id.country);
        provincias = (Spinner) v.findViewById(R.id.provincia);
        departamentos = (Spinner) v.findViewById(R.id.departamento);
        next = (Button)v.findViewById(R.id.nextB);
        btnAtras = (Button) v.findViewById(R.id.btnAtras);

        initPaises();
        SpinnerAdapter adapter = new SpinnerAdapter(getContext(), listaPaises);
        paises.setAdapter(adapter);
        paises.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextItem item = (TextItem) adapterView.getItemAtPosition(i);
                currentCountryID = item.getID();
                currentCountryName = item.getNombre();
                Log.i("DEBUG", item.getID()+" - "+item.getNombre());
                if (item.getNombre().equals("Argentina")) {
                    setProvinciasSpinner();
                }
                else {
                    provincias.setAdapter(null);
                    departamentos.setAdapter(null);
                    currentDeptoID = 0;
                    currentDeptoName = "";
                    currentProvinceID = 0;
                    currentProvinceName = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        paises.setSelection(10);



        // Buton Next Implementation
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mlistener.FragmentToQuantity(currentCountryID, currentProvinceID,currentDeptoID, currentCountryName, currentProvinceName,currentDeptoName);
            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mlistener.FromLocationToBack();
            }
        });
        return v;
    }


    private void setProvinciasSpinner() {
        initProvincias();
        final SpinnerAdapter adapter = new SpinnerAdapter(getContext(), listaProvincias);
        provincias.setAdapter(adapter);
        provincias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextItem item = (TextItem) adapterView.getItemAtPosition(i);
                currentProvinceID = item.getID();
                currentProvinceName = item.getNombre();
                setDeptosSpinner(item.getID());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        provincias.setSelection(12);
    }
    private void setDeptosSpinner(int position) {
        initDeptos(position);
        SpinnerAdapter adapter = new SpinnerAdapter(getContext(), listaDeptos);
        departamentos.setAdapter(adapter);
        departamentos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextItem item = (TextItem) adapterView.getItemAtPosition(i);
                currentDeptoID = item.getID();
                currentDeptoName = item.getNombre();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void initDeptos(int position) {
        listaDeptos = new ArrayList<>();
        String[] deptos_array = new String[0];
        switch (position) {
            case 1:
                deptos_array = getResources().getStringArray(R.array.buenos_aires);
                break;
            case 2:
                deptos_array = getResources().getStringArray(R.array.catamarca);
                break;
            case 3:
                deptos_array = getResources().getStringArray(R.array.chaco);
                break;
            case 4:
                deptos_array = getResources().getStringArray(R.array.chubut);
                break;
            case 5:
                deptos_array = getResources().getStringArray(R.array.cab);
                break;

            case 6:
                deptos_array = getResources().getStringArray(R.array.cordoba);
                break;
            case 7:
                deptos_array = getResources().getStringArray(R.array.corrientes);
                break;
            case 8:
                deptos_array = getResources().getStringArray(R.array.entre_rios);
                break;
            case 9:
                deptos_array = getResources().getStringArray(R.array.formosa);
                break;
            case 10:
                deptos_array = getResources().getStringArray(R.array.jujuy);
                break;
            case 11:
                deptos_array = getResources().getStringArray(R.array.la_pampa);
                break;
            case 12:
                deptos_array = getResources().getStringArray(R.array.la_rioja);
                break;
            case 13:
                deptos_array = getResources().getStringArray(R.array.mendoza);
                break;
            case 14:
                deptos_array = getResources().getStringArray(R.array.misiones);
                break;
            case 15:
                deptos_array = getResources().getStringArray(R.array.neuquen);
                break;
            case 16:
                deptos_array = getResources().getStringArray(R.array.rio_negro);
                break;
            case 17:
                deptos_array = getResources().getStringArray(R.array.salta);
                break;
            case 18:
                deptos_array = getResources().getStringArray(R.array.san_juan);
                break;
            case 19:
                deptos_array = getResources().getStringArray(R.array.san_luis);
                break;
            case 20:
                deptos_array = getResources().getStringArray(R.array.santa_cruz);
                break;
            case 21:
                deptos_array = getResources().getStringArray(R.array.santa_fe);
                break;
            case 22:
                deptos_array = getResources().getStringArray(R.array.santiago_del_estero);
                break;
            case 23:
                deptos_array = getResources().getStringArray(R.array.tierra_del_fuego);
                break;
            case 24:
                deptos_array = getResources().getStringArray(R.array.tucuman);
                break;
        }
        for (int i = 0; i< deptos_array.length; i++) {
            TextItem depto = new TextItem(i+1, deptos_array[i]);
            listaDeptos.add(depto);
        }
    }

    private void initPaises() {
        // Init Paises
        listaPaises = new ArrayList<TextItem>();
        String[] paises_array= getResources().getStringArray(R.array.paises);
        for (int i = 0; i< paises_array.length; i++) {
            TextItem pais = new TextItem(i+1, paises_array[i]);
            listaPaises.add(pais);
        }
    }

    private void initProvincias() {
        listaProvincias = new ArrayList<TextItem>();
        String[] provincias_array = getResources().getStringArray(R.array.provincias);
        for (int i = 0; i< provincias_array.length; i++) {
            TextItem provincia = new TextItem(i+1, provincias_array[i]);
            listaProvincias.add(provincia);
        }
        //provincias.setVisibility(View.VISIBLE);
    }


    public interface FromLocationToActivity {
        void FragmentToQuantity(int countryID, int provinceID, int deptoID, String countryName, String provinceName, String deptoName);
        void FromLocationToBack();
    }
}
