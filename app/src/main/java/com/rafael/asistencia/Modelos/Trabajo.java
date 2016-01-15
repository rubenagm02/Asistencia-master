package com.rafael.asistencia.Modelos;

/**
 * Created by root on 18/11/15.
 */
public class Trabajo {
    private int id;
    private int idDetalleGrupoUsuario;
    private String nombre;
    private String fechaAlta;
    private String fechaFin;
    private int valor;
    private String descripcion;

    public Trabajo () {
        id = 0;
        idDetalleGrupoUsuario = 0;
        nombre = "";
        fechaAlta = "";
        fechaFin = "";
        valor = 0;
        descripcion = "";
    }

    public Trabajo (int id, int idDetalleGrupoUsuario, String nombre, String fechaAlta, String fechaFin, int valor, String descripcion) {
        this.id = id;
        this.idDetalleGrupoUsuario = idDetalleGrupoUsuario;
        this.nombre = nombre;
        this.fechaAlta = fechaAlta;
        this.fechaFin = fechaFin;
        this.valor = valor;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDetalleGrupoUsuario() {
        return idDetalleGrupoUsuario;
    }

    public void setIdDetalleGrupoUsuario(int idDetalleGrupoUsuario) {
        this.idDetalleGrupoUsuario = idDetalleGrupoUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        valor = valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
