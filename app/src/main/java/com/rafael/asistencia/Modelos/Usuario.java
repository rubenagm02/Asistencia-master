package com.rafael.asistencia.Modelos;

/**
 * Created by root on 18/11/15.
 */
public class Usuario {
    private int id;
    private String nombre;
    private String contrasenia;
    private String correo;
    private int idCliente;

    public Usuario () {
        id          = 0;
        nombre      = "";
        contrasenia = "";
        correo      = "";
        idCliente   = 0;
    }

    public Usuario(int id, String nombre, String contrasenia, String correo, int idCliente){
        this.id           = id;
        this.nombre       = nombre;
        this.contrasenia = contrasenia;
        this.correo       = correo;
        this.idCliente    = idCliente;
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

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
