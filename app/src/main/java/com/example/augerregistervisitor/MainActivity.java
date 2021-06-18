package com.example.augerregistervisitor;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements NewVisitorFragment.FromNewVisitortoActivity, EmailFragment.FromEmailtoActivity, LocationFragment.FromLocationToActivity, QuantityFragment.FromQuantityToActivity, ConfirmFragment.FromConfirmToActivity, IngreseGrupoFragment.GroupFragmentActivity, QuantityGroupFragment.QGroupActivity {


    private boolean isGroup = false;


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        // Start the app with NewVisitor Fragment.
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        NewVisitorFragment fragment = new NewVisitorFragment();
        fragmentTransaction.add(R.id.contenedor, fragment).commit();
    }


    // Comming from NewVisitorFragment
    @Override
    public void NextFragment(int frag) {
        Fragment fragment = null;
        Log.i("DEBUG", "Estoy en Activity Main");
        Toast.makeText(this, "Button pressed", Toast.LENGTH_LONG);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (frag == 0) {
            fragment = new EmailFragment();
        }
        else { // frag == 1
            isGroup = true;
            fragment = new IngreseGrupoFragment();
        }
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.contenedor, fragment).addToBackStack(null).commit();
    }

    @Override
    public void FragmentToLocation(String email) {
        // Saving preferences for email
        SharedPreferences preferences = getSharedPreferences("visitors", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("email", email);
        final boolean commit = editor.commit();

        // Close Keyboard
        closeKeyBoard();


        // Changing Fragment....
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        LocationFragment fragment = new LocationFragment();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.contenedor, fragment).addToBackStack(null).commit();
    }

    @Override
    public void FromEmailToBack() {
        super.onBackPressed();
    }

    private void closeKeyBoard() {
        View v = this.getCurrentFocus();
        if (v != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    @Override
    public void FragmentToQuantity(int currentCountryID, int currentProvinceID, int currentDeptoID, String currentCountryName, String currentProvinceName, String currentDeptoName) {
        SharedPreferences preferences = getSharedPreferences("visitors", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("countryID", currentCountryID);
        editor.putInt("provinceID", currentProvinceID);
        editor.putInt("deptoID", currentDeptoID);
        editor.putString("countryName", currentCountryName);
        editor.putString("deptoName", currentDeptoName);
        editor.putString("provinceName", currentProvinceName);
        final boolean commit = editor.commit();


        // Changing Fragment
        if (isGroup) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            QuantityGroupFragment fragment = new QuantityGroupFragment();
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            fragmentTransaction.replace(R.id.contenedor, fragment).addToBackStack(null).commit();
        }
        else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            QuantityFragment fragment = new QuantityFragment();
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            fragmentTransaction.replace(R.id.contenedor, fragment).addToBackStack(null).commit();
        }
    }

    @Override
    public void FromLocationToBack() {
        super.onBackPressed();
    }


    // Comming from QuantityFragment
    @Override
    public void ConrfirmData(int quantity, ArrayList<Integer> edades) {
        // Saving Preferences

        //Log.i("A ver 2", edades.get(0)+"");
        SharedPreferences preferences = getSharedPreferences("visitors", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("quantity", quantity);
        final boolean commit = editor.commit();


        // Sending ArrayList by Bundle because by SharedPreferences is un quilombo
        Bundle args = new Bundle();
        args.putBoolean("isGroup", isGroup);
        args.putIntegerArrayList("edades", edades);

        // Changing Fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ConfirmFragment fragment = new ConfirmFragment();
        fragment.setArguments(args);
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.contenedor, fragment).addToBackStack(null).commit();
    }

    @Override
    public void FromQtyToBack() {
        super.onBackPressed();
    }

    @Override
    public void DataSaved(int status) {
        // At this point, we already saved the data, and we show a message for a few seconds and get the main fragment again...

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        final AlertDialog alertDialog;
        View layoutView = null;
        TextView textDialog;


        if (status == 0 ) { // Data not saved

            layoutView = getLayoutInflater().inflate(R.layout.negative_alert_dialog, null);
            textDialog = (TextView) layoutView.findViewById(R.id.textoDialog);
            textDialog.setText("Error al guardar datos");
        }
        else if (status == 1) { // Data saved
            layoutView = getLayoutInflater().inflate(R.layout.datos_almacenados, null);
        }
        else if (status == -1) { // Network Connection
            layoutView = getLayoutInflater().inflate(R.layout.negative_alert_dialog, null);
            textDialog = (TextView) layoutView.findViewById(R.id.textoDialog);
            textDialog.setText("Error al conectar... Intente más tarde");
        } else if (status == 2) { // status == 2
            layoutView = getLayoutInflater().inflate(R.layout.negative_alert_dialog, null);
            textDialog = (TextView) layoutView.findViewById(R.id.textoDialog);
            textDialog.setText("Datos duplicados!");
        }
        else { // status == -2
            layoutView = getLayoutInflater().inflate(R.layout.negative_alert_dialog, null);
            textDialog = (TextView) layoutView.findViewById(R.id.textoDialog);
            textDialog.setText("Cancelado!");
        }
        alertDialogBuilder.setView(layoutView);
        alertDialog = alertDialogBuilder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(true);
        alertDialog.show();


        // Wait for a seconds and after close the dialog...

        final Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                alertDialog.dismiss();
                t.cancel();
            }
        }, 2000);

        // clear everything and change Fragment to main one
        SharedPreferences preferences = getSharedPreferences("visitors", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        final boolean commit = editor.commit();

        isGroup = false;

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        NewVisitorFragment fragment = new NewVisitorFragment();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.contenedor, fragment).addToBackStack(null).commit();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        //Toast.makeText(this, "Button pressed",Toast.LENGTH_SHORT).show();
    }


    // Comming from IngreseGupoFragment
    @Override
    public void FragmentToQuantityGroup(String nombre) {
        // Saving preferences for groupName
        SharedPreferences preferences = getSharedPreferences("visitors", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("groupName", nombre);
        final boolean commit = editor.commit();

        // Close Keyboard
        closeKeyBoard();

        // Replace to EmailFragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        EmailFragment fragment = new EmailFragment();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.contenedor, fragment).addToBackStack(null).commit();
    }

    @Override
    public void FromIngreseGrupoToBack() {
        super.onBackPressed();
    }


    // Comming from QuantityGroupFragment
    @Override
    public void Confirm(int quantity, int edadDesde, int edadHasta) {
        SharedPreferences preferences = getSharedPreferences("visitors", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("quantity", quantity);
        editor.putInt("edadDesde", edadDesde);
        editor.putInt("edadHasta", edadHasta);
        final boolean commit = editor.commit();

        // Close Keyboard
        closeKeyBoard();

        Bundle args = new Bundle();
        args.putBoolean("isGroup", isGroup);

        // Changing Fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ConfirmFragment fragment = new ConfirmFragment();
        fragment.setArguments(args);
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.contenedor, fragment).addToBackStack(null).commit();
    }

    @Override
    public void FromQtyGroupToBack() {
        super.onBackPressed();
    }


    @Override
    protected void onPause() {
        super.onPause();

        ActivityManager activityManager = (ActivityManager) getApplicationContext()
                .getSystemService(Context.ACTIVITY_SERVICE);

        activityManager.moveTaskToFront(getTaskId(), 0);
    }


}
