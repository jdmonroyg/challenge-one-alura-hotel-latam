package com.hotelalura.models;

import java.sql.Date;

/**
 * @author jdmon on 7/09/2023.
 * @project challenge-one-alura-hotel-latam
 */
public class Reserva {
    private int id;
    private Date fechaEntrada;
    private Date fechaSalida;
    private double valor;
    private String formaPago;

    public Reserva(Date fechaEntrada, Date fechaSalida, double valor, String formaPago) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.valor = valor;
        this.formaPago = formaPago;
    }

    public Reserva(Integer id, Date fechaEntrada, Date fechaSalida, double valor, String formaPago) {
        this.id=id;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.valor = valor;
        this.formaPago = formaPago;
    }
    public int getId() {
        return id;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public double getValor() {
        return valor;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setId(int id) {
        this.id=id;
    }
}
