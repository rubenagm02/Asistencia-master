package com.rafael.asistencia.Modelos;

/**
 *
 */
public class Grupo {
    private int id;
    private String nombre;
    private int idCliente;

    public Grupo () {
        id        = 0;
        nombre    = "";
        idCliente = 0;
    }

    public Grupo(String nombre) {
        this.nombre = nombre;
    }

    public Grupo (int id, String nombre, int idCliente) {
        this.id = id;
        this.nombre = nombre;
        this.idCliente = idCliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
