package com.rafael.asistencia.Modelos;

/**
 * Created by root on 23/11/15.
 */
public class DetalleTrabajoAlumno {
    private int idTrabajo;
    private int idAlumno;
    private String nota;
    private int evaluacion;
    private String nombre;

    public DetalleTrabajoAlumno () {

    }

    public DetalleTrabajoAlumno (int idTrabajo, int idAlumno, int evaluacion, String nota) {
        this.idAlumno = idAlumno;
        this.idTrabajo = idTrabajo;
        this.evaluacion = evaluacion;
        this.nota = nota;

    }

    public int getIdTrabajo() {
        return idTrabajo;
    }

    public void setIdTrabajo(int idTrabajo) {
        this.idTrabajo = idTrabajo;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public int getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(int evaluacion) {
        this.evaluacion = evaluacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
