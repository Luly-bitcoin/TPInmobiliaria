package com.luu.tpinmobiliaria.ui.inquilinos;

import java.io.Serializable;

public class Inquilino implements Serializable {

    private int idInquilino;
    private long dni;
    private String nombre;
    private String apellido;
    private String lugarTrabajo;
    private String telefono;
    private String email;
    private String nombreGarante;
    private long dniGarante;

    public Inquilino() {
    }

    public Inquilino(int idInquilino, long dni, String nombre, String apellido, String lugarTrabajo, String telefono, String email, String nombreGarante, long dniGarante) {
        this.idInquilino = idInquilino;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.lugarTrabajo = lugarTrabajo;
        this.telefono = telefono;
        this.email = email;
        this.nombreGarante = nombreGarante;
        this.dniGarante = dniGarante;
    }

    public int getIdInquilino() {
        return idInquilino;
    }

    public void setIdInquilino(int idInquilino) {
        this.idInquilino = idInquilino;
    }

    public long getDni() {
        return dni;
    }

    public void setDni(long dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getLugarTrabajo() {
        return lugarTrabajo;
    }

    public void setLugarTrabajo(String lugarTrabajo) {
        this.lugarTrabajo = lugarTrabajo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombreGarante() {
        return nombreGarante;
    }

    public void setNombreGarante(String nombreGarante) {
        this.nombreGarante = nombreGarante;
    }

    public long getDniGarante() {
        return dniGarante;
    }

    public void setDniGarante(long dniGarante) {
        this.dniGarante = dniGarante;
    }
}