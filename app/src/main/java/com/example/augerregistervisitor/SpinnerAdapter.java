package com.example.augerregistervisitor;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SpinnerAdapter extends ArrayAdapter<TextItem> {


    public SpinnerAdapter(Context context, ArrayList<TextItem> lista) {
        super(context, 0, lista);
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        return initView(position, convertView,parent);
    }

    @Override
    public View getDropDownView(int position,  View convertView,  ViewGroup parent) {
        //return super.getDropDownView(position, convertView, parent);
        return initView(position, convertView,parent);
    }



    private View initView (int position,  View convertView,  ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_item, parent, false);
        }
        TextView nombre = convertView.findViewById(R.id.item_name);
        TextItem currentItem = getItem(position);
        nombre.setText(currentItem.getNombre());
        return convertView;
    }
}

