package com.luu.tpinmobiliaria.ui.inmuebles;

import com.google.gson.annotations.SerializedName;

public class Inmueble {

    private int idInmueble;

    @SerializedName("direccion")
    private String direccion;

    @SerializedName("ambientes")
    private int ambientes;

    @SerializedName("uso")
    private String uso;

    @SerializedName("tipo")
    private String tipo;

    @SerializedName("valor")
    private double precio;

    @SerializedName("disponible")
    private boolean estado;

    @SerializedName("imagen")
    private String avatar;

    public Inmueble() {}

    public int getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(int idInmueble) {
        this.idInmueble = idInmueble;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getAmbientes() {
        return ambientes;
    }

    public void setAmbientes(int ambientes) {
        this.ambientes = ambientes;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}