package com.example.augerregistervisitor;

public class TextItem {
    int ID;
    String nombre;

    public TextItem(int ID, String n) {
        this.ID = ID;
        this.nombre = n;
    }

    public String getNombre() {
        return nombre;
    }

    public int getID (){
        return ID;
    }
}
