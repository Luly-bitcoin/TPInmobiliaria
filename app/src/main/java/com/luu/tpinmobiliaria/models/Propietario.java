package com.luu.tpinmobiliaria.models;

import com.google.gson.annotations.SerializedName;

public class Propietario {

    @SerializedName("idPropietario")
    private int id;

    private String dni;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String clave;

    public int getId() {
        return id;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getClave() {
        return clave;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}