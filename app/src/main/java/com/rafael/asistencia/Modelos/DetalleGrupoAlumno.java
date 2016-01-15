package com.rafael.asistencia.Modelos;

/**
 * Created by root on 23/11/15.
 */
public class DetalleGrupoAlumno {
    private int idDetalleGrupoUsuario;
    private int idAlumno;

    public DetalleGrupoAlumno() {

    }

    public DetalleGrupoAlumno (int idAlumno, int idDetalleGrupoUsuario) {
        this.idAlumno = idAlumno;
        this.idDetalleGrupoUsuario = idDetalleGrupoUsuario;
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
}
