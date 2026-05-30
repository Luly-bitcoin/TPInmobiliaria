package com.luu.tpinmobiliaria.models;

import java.io.Serializable;

public class Pago implements Serializable {

    private int idPago;
    private int numero;
    private int idContrato;
    private double importe;
    private String fechaPago;

    public Pago() {
    }

    public Pago(int idPago, int numero, int idContrato, double importe, String fechaPago) {
        this.idPago = idPago;
        this.numero = numero;
        this.idContrato = idContrato;
        this.importe = importe;
        this.fechaPago = fechaPago;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }
}