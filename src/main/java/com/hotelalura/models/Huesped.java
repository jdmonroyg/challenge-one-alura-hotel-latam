package com.hotelalura.models;

import java.util.Date;

/**
 * @author jdmon on 7/09/2023.
 * @project challenge-one-alura-hotel-latam
 */
public class Huesped {
    private int id;
    private String Nombre;
    private String Apellido;
    private Date fechaNacimiento;
    private String nacionalidad;
    private String telefono;
    private int idReserva;

    public Huesped(String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono, int idReserva) {
        Nombre = nombre;
        Apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.idReserva = idReserva;
    }
}
