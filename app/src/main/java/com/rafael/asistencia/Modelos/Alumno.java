package com.rafael.asistencia.Modelos;



/**
 *
 */
public class Alumno {
    private int codigo;
    private String nombre;
    private String fechaNacimiento;
    private int idUsuario;
    private String salon;

    public Alumno () {
        codigo = 0;
        nombre = "";
    }

    public Alumno(int codigo, String nombre, String correo, String fechaNacimiento, int idUsuario, String salon) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.idUsuario = idUsuario;
        this.salon = salon;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int id) {
        this.codigo = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getSalon() {
        return salon;
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }
}
