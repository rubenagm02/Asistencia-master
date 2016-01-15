package com.rafael.asistencia.BaseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.rafael.asistencia.Modelos.Alumno;
import com.rafael.asistencia.Modelos.AsistenciasAlumno;
import com.rafael.asistencia.Modelos.DetalleGrupoAlumno;
import com.rafael.asistencia.Modelos.DetalleGrupoUsuario;
import com.rafael.asistencia.Modelos.DetalleTrabajoAlumno;
import com.rafael.asistencia.Modelos.Grupo;
import com.rafael.asistencia.Modelos.Trabajo;

import java.util.ArrayList;

/**
 *
 */
public class ConexionBaseDatos extends SQLiteOpenHelper {

    private final String TAG_DB = "Operacion BD";
    private final String CREATE_TABLE_ALUMNO  = "CREATE TABLE datos_alumno (codigo INTEGER PRIMARY KEY, Nombre TEXT, Salon_Grupo TEXT, Fecha_Nacimiento TEXT, ID_usuario INTEGER)";
    private final String CREATE_TABLE_SALONES   = "CREATE TABLE Salones (gradoygrupo TEXT PRIMARY KEY)";
    private final String CREATE_TABLE_TRABAJO = "CREATE TABLE Trabajo (Id INTEGER PRIMARY KEY, IdDetalleGrupoUsuario INTEGER, Nombre TEXT, FechaAlta DATE, FechaFin DATE, Evaluacion INTEGER, Nota TEXT)";
    private final String CREATE_TABLE_ASISTENCIAS_ALUMNO = "CREATE TABLE AsistenciasAlumno (IdDetalleGrupoUsuario INTEGER, IdAlumno INTEGER, Fecha DATE, Asistencia TEXT)";
    private final String CREATE_TABLE_TRABAJO_ALUMNO= "CREATE TABLE DetalleTrabajoAlumno (IdTrabajo INTEGER, IdAlumno INTEGER, Evaluacion INTEGER, Nota TEXT)";
    private final String CREATE_TABLE_GRUPO_ALUMNO= "CREATE TABLE DetalleGrupoAlumno (IdDetalleGrupoUsuario INTEGER, IdAlumno INTEGER)";
    private final String CREATE_TABLE_GRUPO_USUARIO= "CREATE TABLE DetalleGrupoUsuario (Id INTEGER PRIMARY KEY, IdUsuario INTEGER, IdGrupo INTEGER)";

    /***** query de select *****/

    private final String SELECT_GRUPO = "SELECT * FROM Salones";
    private final String SELECT_ASISTENCIAS_ALUMNO = "SELECT * FROM AsistenciasAlumno";
    private final String SELECT_DETALLE_TRABAJO_ALUMNO = "SELECT * FROM DetalleTrabajoAlumno";
    private final String SELECT_OBTENER_ALUMNO = "SELECT * FROM Alumno WHERE Id = ";
    private final String SELECT_OBTENER_TRABAJO = "SELECT * FROM Trabajo WHERE Id = ";
    private final String SELECT_OBTENER_TRABAJOS_GRUPO = "SELECT * FROM Trabajo WHERE IdDetalleGrupoUsuario = ";
    private final String SELECT_OBTENER_ALUMNOS_GRUPO = "SELECT * FROM DetalleGrupoAlumno";

    public ConexionBaseDatos(Context context) {
        super(context, "Asistencia", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SALONES);
        db.execSQL(CREATE_TABLE_ALUMNO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertarAsistenciaAlumno (AsistenciasAlumno asistenciasAlumno) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues   = new ContentValues();

        contentValues.put("IdDetalleGrupoUsuario", asistenciasAlumno.getIdDetalleGrupoUsuario());
        contentValues.put("IdAlumno", asistenciasAlumno.getIdAlumno());
        contentValues.put("Fecha", asistenciasAlumno.getFecha());
        contentValues.put("Asistencia", asistenciasAlumno.getAsistencia());

        sqLiteDatabase.insert("AsistenciasAlumno", null, contentValues);

        Log.i(TAG_DB, "Se ha insertado una asistencia");

        return true;
    }

    public boolean insertarTrabajo (Trabajo trabajo) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues   = new ContentValues();

        contentValues.put("IdDetalleGrupoUsuario", trabajo.getIdDetalleGrupoUsuario());
        contentValues.put("Nombre", trabajo.getNombre());
        contentValues.put("FechaAlta", trabajo.getFechaAlta());
        contentValues.put("FechaFin", trabajo.getFechaFin());
        contentValues.put("Evaluacion", trabajo.getValor());
        contentValues.put("Nota", trabajo.getDescripcion());

        sqLiteDatabase.insert("Trabajo", null, contentValues);

        Log.i(TAG_DB, "Se ha insertado un trabajo");

        return true;
    }

    public boolean insertarDetalleTrabajoAlumno (DetalleTrabajoAlumno detalleTrabajoAlumno) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues   = new ContentValues();

        contentValues.put("IdAlumno", detalleTrabajoAlumno.getIdAlumno());
        contentValues.put("IdTrabajo", detalleTrabajoAlumno.getIdTrabajo());
        contentValues.put("Evaluacion", detalleTrabajoAlumno.getEvaluacion());
        contentValues.put("Nota", detalleTrabajoAlumno.getNota());

        sqLiteDatabase.insert("DetalleTrabajoAlumno", null, contentValues);

        Log.i(TAG_DB, "Se ha insertado un DetalleTrabajoAlumno");

        return true;
    }

    public boolean insertarGrupo (Grupo grupo) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues   = new ContentValues();

        contentValues.put("gradoygrupo", grupo.getNombre());

        sqLiteDatabase.insert("Salones", null, contentValues);

        Log.i(TAG_DB, "Se ha insertado un Grupo");

        return true;
    }

    public boolean insertarDetalleGrupoUsuario(DetalleGrupoUsuario detalleGrupoUsuario) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues   = new ContentValues();

        contentValues.put("Id", detalleGrupoUsuario.getId());
        contentValues.put("IdGrupo", detalleGrupoUsuario.getIdGrupo());
        contentValues.put("IdUsuario", detalleGrupoUsuario.getIdUsuario());

        sqLiteDatabase.insert("DetalleGrupoUsuario", null, contentValues);

        Log.i(TAG_DB, "Se ha insertado un DetalleGrupoUsuario");

        return true;
    }




    public boolean insertarAlumno (Alumno alumno) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues   = new ContentValues();

        contentValues.put("Codigo", alumno.getCodigo());
        contentValues.put("Nombre", alumno.getNombre());
        contentValues.put("Salon_Grupo", alumno.getSalon());
        contentValues.put("Fecha_Nacimiento", alumno.getNombre());
        contentValues.put("ID_usuario", alumno.getIdUsuario());

        sqLiteDatabase.insert("datos_alumno", null, contentValues);

        Log.i(TAG_DB, "Se ha insertado un Alumno");

        return true;
    }

    public boolean insertarDetalleGrupoAlumno (DetalleGrupoAlumno detalleGrupoAlumno) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues   = new ContentValues();

        contentValues.put("IdDetalleGrupoUsuario", detalleGrupoAlumno.getIdDetalleGrupoUsuario());
        contentValues.put("IdAlumno", detalleGrupoAlumno.getIdAlumno());

        sqLiteDatabase.insert("DetalleGrupoAlumno", null, contentValues);

        Log.i(TAG_DB, "Se ha insertado un DetalleGrupoAlumno");

        return true;
    }



    /******** FUNCIONES PARA OBTENER DATOS ********/

    public ArrayList<Grupo> obtenerGrupos () {
        ArrayList<Grupo> grupos = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_GRUPO, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

            grupos.add(new Grupo(
                    cursor.getInt(0),
                    cursor.getString(cursor.getColumnIndex("gradoygrupo")),
                    cursor.getInt(0)
            ));

            cursor.moveToNext();
        }

        return grupos;
    }

    public ArrayList<AsistenciasAlumno> obtenerAsistencias () {

        ArrayList<AsistenciasAlumno> asistenciasAlumnos = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ASISTENCIAS_ALUMNO, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

            asistenciasAlumnos.add(new AsistenciasAlumno(
                    cursor.getInt(cursor.getColumnIndex("IdDetalleGrupoUsuario")),
                    cursor.getInt(cursor.getColumnIndex("IdAlumno")),
                    cursor.getString(cursor.getColumnIndex("Fecha")),
                    cursor.getString(cursor.getColumnIndex("Asistencia"))
                    ));

            cursor.moveToNext();
        }

        return asistenciasAlumnos;
    }

    public ArrayList<DetalleGrupoAlumno> obtenerAlumnosGrupos () {

        ArrayList<DetalleGrupoAlumno> detalleGrupoAlumnos = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_OBTENER_ALUMNOS_GRUPO, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

            detalleGrupoAlumnos.add(new DetalleGrupoAlumno(
                    cursor.getInt(cursor.getColumnIndex("IdDetalleGrupoUsuario")),
                    cursor.getInt(cursor.getColumnIndex("IdAlumno")))
            );

            cursor.moveToNext();
        }

        return detalleGrupoAlumnos;
    }

    public Alumno obtenerAlumno (int id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_OBTENER_ALUMNO + id, null);
        cursor.moveToFirst();

        Alumno alumno = new Alumno();

        cursor.close();
        return alumno;
    }

    public ArrayList<DetalleTrabajoAlumno> obtenerDetalleTrabajoAlumno () {

        ArrayList<DetalleTrabajoAlumno> detalleTrabajoAlumnos = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_DETALLE_TRABAJO_ALUMNO, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

            detalleTrabajoAlumnos.add(new DetalleTrabajoAlumno(
                    cursor.getInt(cursor.getColumnIndex("IdAlumno")),
                    cursor.getInt(cursor.getColumnIndex("IdTrabajo")),
                    cursor.getInt(cursor.getColumnIndex("Evaluacion")),
                    cursor.getString(cursor.getColumnIndex("Nota"))
            ));

            cursor.moveToNext();
        }
        cursor.close();
        return detalleTrabajoAlumnos;
    }

    public Trabajo obtenerTrabajo (int id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_OBTENER_TRABAJO + id, null);
        cursor.moveToFirst();

        Trabajo trabajo = new Trabajo(cursor.getInt(cursor.getColumnIndex("Id")),
                cursor.getInt(cursor.getColumnIndex("IdDetalleGrupoUsuario")),
                cursor.getString(cursor.getColumnIndex("Nombre")),
                cursor.getString(cursor.getColumnIndex("FechaAlta")),
                cursor.getString(cursor.getColumnIndex("FechaFin")),
                cursor.getInt(cursor.getColumnIndex("Evaluacion")),
                cursor.getString(cursor.getColumnIndex("Nota"))
                );

        cursor.close();
        return trabajo;

    }

    public ArrayList<Trabajo> obtenerTrabajosGrupo (int id) {
        ArrayList<Trabajo> trabajos = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_OBTENER_TRABAJOS_GRUPO + id, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

            trabajos.add(new Trabajo(cursor.getInt(cursor.getColumnIndex("Id")),
                    cursor.getInt(cursor.getColumnIndex("IdDetalleGrupoUsuario")),
                    cursor.getString(cursor.getColumnIndex("Nombre")),
                    cursor.getString(cursor.getColumnIndex("FechaAlta")),
                    cursor.getString(cursor.getColumnIndex("FechaFin")),
                    cursor.getInt(cursor.getColumnIndex("Evaluacion")),
                    cursor.getString(cursor.getColumnIndex("Nota"))
            ));

            cursor.moveToNext();
        }
        cursor.close();
        return trabajos;

    }
}
