package com.example.augerregistervisitor;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Request;
import okhttp3.Response;

public class ConfirmFragment extends Fragment {

    private TextView valor1;
    private TextView valor2;
    private TextView valor3;
    private TextView valor4;
    private TextView valor5;
    private TextView valor6;

    private TextView clave1;
    private TextView clave2;
    private TextView clave3;
    private TextView clave4;
    private TextView clave5;
    private TextView clave6;


    private Button btnConfirmar;
    private Button btnCancelar;

    private FromConfirmToActivity mlistener;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FromConfirmToActivity) {
            Log.i("DEBUG", "It is instance!");
            mlistener = (FromConfirmToActivity) context;
        }
        else throw new RuntimeException(context.toString()
                + " must implement FromConfirmtoActivity interface");
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.confirmacion, container, false);


        clave1 = (TextView)v.findViewById(R.id.clave1);
        clave2 = (TextView)v.findViewById(R.id.clave2);
        clave3 = (TextView)v.findViewById(R.id.clave3);
        clave4 = (TextView)v.findViewById(R.id.clave4);
        clave5 = (TextView)v.findViewById(R.id.clave5);
        clave6 = (TextView)v.findViewById(R.id.clave6);

        valor1 = (TextView)v.findViewById(R.id.valor1);
        valor2 = (TextView)v.findViewById(R.id.valor2);
        valor3 = (TextView)v.findViewById(R.id.valor3);
        valor4 = (TextView)v.findViewById(R.id.valor4);
        valor5 = (TextView)v.findViewById(R.id.valor5);
        valor6 = (TextView)v.findViewById(R.id.valor6);

        btnConfirmar = (Button) v.findViewById(R.id.btnConfirmar);
        btnCancelar = (Button) v.findViewById(R.id.btnCancelar);



        // Geting boolean variable: isGroup
        final boolean isGroup = getArguments().getBoolean("isGroup");


        ArrayList<Integer> edades = null;
        if (!isGroup)
            edades = getArguments().getIntegerArrayList("edades");

            // Getting others variables:
            SharedPreferences preferences = getActivity().getSharedPreferences("visitors", Context.MODE_PRIVATE);


            final String groupName = preferences.getString("groupName", "");
            final String correo = preferences.getString("email", "");
            final int countryID = preferences.getInt("countryID", 0);
            final int provinceID = preferences.getInt("provinceID", 0);
            final int deptoID = preferences.getInt("deptoID", 0);
            final int quantity = preferences.getInt("quantity", 0);
            String countryName = preferences.getString("countryName", "");
            String deptoName = preferences.getString("deptoName", "");
            String provinceName = preferences.getString("provinceName", "");
            int edadDesde = 0;
            int edadHasta = 0;
            if (isGroup) {
                edadDesde = preferences.getInt("edadDesde", 0);
                edadHasta = preferences.getInt("edadHasta", 0);
            }

            if (!isGroup) {
                clave1.setText("Email:");
                clave2.setText("País:");
                clave3.setText("Provincia:");
                clave4.setText("Departamento:");
                clave5.setText("Número de visitantes:");
                clave6.setText("");

                valor1.setText(correo);
                valor2.setText(countryName);
                valor3.setText(provinceName);
                valor4.setText(deptoName);
                valor5.setText(String.valueOf(quantity));
                valor6.setText("");
            }
            else {
                clave1.setText("Nombre:");
                clave2.setText("Email:");
                clave3.setText("País:");
                clave4.setText("Provincia:");
                clave5.setText("Departamento:");
                clave6.setText("Número de visitantes:");

                valor1.setText(groupName);
                valor2.setText(correo);
                valor3.setText(countryName);
                valor4.setText(provinceName);
                valor5.setText(deptoName);
                valor6.setText(String.valueOf(quantity));
            }
            final ArrayList<Integer> finalEdades = edades;
            final int finalEdadDesde = edadDesde;
            final int finalEdadHasta = edadHasta;
            btnConfirmar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Save data
                    final Thread thread = new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            OkHttpClient client = new OkHttpClient();
                            RequestBody body = null;

                            if (!isGroup) {
                                Log.i("Registro", "Registro de Visitante");
                                body = new FormBody.Builder()
                                        .add("isgroup", "0")
                                        .add("email", correo)
                                        .add("countryID", String.valueOf(countryID))
                                        .add("provinceID", String.valueOf(provinceID))
                                        .add("deptoID", String.valueOf(deptoID))
                                        .add("quantity", String.valueOf(quantity))
                                        .add("edades", String.valueOf(finalEdades))
                                        .build();
                            }
                            else {
                                Log.i("Registro", "Registro de Grupo");
                                body = new FormBody.Builder()
                                        .add("isgroup", "1")
                                        .add("groupname", groupName)
                                        .add("email", correo)
                                        .add("countryID", String.valueOf(countryID))
                                        .add("provinceID", String.valueOf(provinceID))
                                        .add("deptoID", String.valueOf(deptoID))
                                        .add("quantity", String.valueOf(quantity))
                                        .add("edadDesde", String.valueOf(finalEdadDesde))
                                        .add("edadHasta", String.valueOf(finalEdadHasta))
                                        .build();
                            }
                            Request request = new Request.Builder()
                                    .url("https://amiga.auger.org.ar/json/AugerRegisterVisitor/registerVisitor.php")
                                    .post(body)
                                    .build();


                            Log.i("Connect", "step 1");

                            try {
                                final Response response = client.newCall(request).execute();
                                String status = response.body().string();
                                Log.i("Response", status);
                                if (status.equals("1")) {
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            mlistener.DataSaved(1);
                                        }
                                    });

                                } else if (status.equals("0")) { // Error en la consulta o al guardar
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            mlistener.DataSaved(0);
                                        }
                                    });

                                } else { // status == 2 // caso de registro duplicado
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            mlistener.DataSaved(2);
                                        }
                                    });

                                }
                            } catch (IOException e) {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mlistener.DataSaved(-1);
                                        //Toast.makeText(getContext(), "Error de conección, intente mas tarde", Toast.LENGTH_LONG).show();
                                    }
                                });

                                e.printStackTrace();
                            }
                        }

                    };
                    thread.start();
                }

            });


            btnCancelar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Cancel data
                    mlistener.DataSaved(-2);
                }
            });


        return v;
    }



    public interface FromConfirmToActivity {
        public void DataSaved(int status);
    }


}
