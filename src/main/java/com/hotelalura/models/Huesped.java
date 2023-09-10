package com.hotelalura.models;

import java.sql.Date;

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
        this.Nombre = nombre;
        this.Apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.idReserva = idReserva;
    }

    public Huesped(int id,String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono, int idReserva) {
        this.id=id;
        this.Nombre = nombre;
        this.Apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.idReserva = idReserva;
    }

    public int getId() {
        return this.id;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setId(int id) {
        this.id = id;
    }
}
