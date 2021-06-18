package com.example.augerregistervisitor;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EdadItemHolder extends RecyclerView.ViewHolder {
    EditText edad;
    Button mas;
    Button menos;

    public EdadItemHolder(@NonNull View itemView) {
        super(itemView);
        edad = (EditText) itemView.findViewById(R.id.edad);
        mas = (Button)itemView.findViewById(R.id.mas);
        menos = (Button) itemView.findViewById(R.id.menos);
    }


}
