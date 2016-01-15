package com.rafael.asistencia.Modelos;

/**
 * Created by root on 29/11/15.
 */
public class DetalleGrupoUsuario {
    private int id;
    private int idUsuario;
    private int idGrupo;

    public DetalleGrupoUsuario(int id, int idUsuario, int idGrupo) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idGrupo = idGrupo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
