package com.example.augerregistervisitor;

public class Escuela {
    private String CUE;
    private String Nombre;
    private String Direccion;
    private String Provincia;
    private String Departamento;

    public Escuela(String cue, String nombre, String direccion, String provincia, String departamento) {
        this.CUE = cue;
        this.Nombre = nombre;
        this.Direccion = direccion;
        this.Provincia = provincia;
        this.Departamento = departamento;
    }

    public String getCUE() {
        return CUE;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getDireccion() {
        return Direccion;
    }

    public String getProvincia() {
        return Provincia;
    }

    public String getDepartamento() {
        return Departamento;
    }
}
