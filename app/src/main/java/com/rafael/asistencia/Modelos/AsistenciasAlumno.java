package com.rafael.asistencia.Modelos;

/**
 * Created by root on 23/11/15.
 */
public class AsistenciasAlumno {
    private int idDetalleGrupoUsuario;
    private int idAlumno;
    private String asistencia;
    private String fecha;
    private String nombre;

    public AsistenciasAlumno () {

    }

    public AsistenciasAlumno (int idDetalleGrupoUsuario, int idAlumno, String fecha, String asistencia) {
        this.idDetalleGrupoUsuario = idDetalleGrupoUsuario;
        this.idAlumno   = idAlumno;
        this.fecha      = fecha;
        this.asistencia = asistencia;
    }

    public int getIdDetalleGrupoUsuario() {
        return idDetalleGrupoUsuario;
    }

    public void setIdDetalleGrupoUsuario(int idDetalleGrupoUsuario) {
        this.idDetalleGrupoUsuario = idDetalleGrupoUsuario;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(String asistencia) {
        this.asistencia = asistencia;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
