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


public class EmailFragment extends Fragment {

    private Button btnNext;
    private Button btnPrevious;
    private EditText txtEmail;
    private FromEmailtoActivity mlistener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.ingrese_mail, container, false);
        btnNext = (Button) v.findViewById(R.id.nextA);
        txtEmail = (EditText) v.findViewById(R.id.email);
        btnPrevious = (Button) v.findViewById(R.id.btnAtras);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mlistener.FragmentToLocation(txtEmail.getText().toString());
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mlistener.FromEmailToBack();
            }
        });
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FromEmailtoActivity) {
            Log.i("DEBUG", "It is instance!");
            mlistener = (FromEmailtoActivity) context;
        }
        else throw new RuntimeException(context.toString()
                + " must implement FromEmailtoActivity interface");
    }

    public interface FromEmailtoActivity {
        void FragmentToLocation(String email);
        void FromEmailToBack();
    }
}
