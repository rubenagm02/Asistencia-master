package com.rafael.asistencia.Conexion;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;

import com.rafael.asistencia.Adaptadores.AdaptadorListaGrupos;
import com.rafael.asistencia.BaseDatos.ConexionBaseDatos;
import com.rafael.asistencia.Modelos.Alumno;
import com.rafael.asistencia.Modelos.DetalleGrupoAlumno;
import com.rafael.asistencia.Modelos.DetalleGrupoUsuario;
import com.rafael.asistencia.Modelos.Grupo;
import com.rafael.asistencia.Modelos.Trabajo;
import com.rafael.asistencia.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicNameValuePair;
import cz.msebera.android.httpclient.util.EntityUtils;

/**
 *
 */
public class  ObtenerDatos extends AsyncTask<Void, Void, String> {
    private Activity activity;

    private SwipeRefreshLayout swipeRefreshLayout;

    private ListView listViewGrupos;

    private final String URL_POST = "http://asistenciaprueba.esy.es/asistencia/asistencia.php";

    private ConexionBaseDatos conexionBaseDatos;

    private final String SELECT_GRUPO   = "SELECT * FROM `salones`";

    private final String SELECT_ALUMNOS = "SELECT * FROM datos_alumno WHERE ID_usuario = " ;

    private final String SELECT_DETALLE_GRUPO_USUARIO = "SELECT * FROM `DetalleGrupoUsuario` " +
            "WHERE IdUsuario = ";

    private final String SELECT_DETALLE_GRUPO_ALUMNO = "SELECT DGA.* FROM DetalleGrupoAlumno DGA " +
            "JOIN Alumno A ON A.id = DGA.IdAlumno " +
            "JOIN DetalleGrupoUsuario DGU ON DGU.Id = DGA.IdDetalleGrupoUsuario " +
            "WHERE DGU.IdUsuario = ";

    private final String SELECT_TRABAJO = "SELECT * FROM Trabajo T " +
            "JOIN DetalleGrupoUsuario DGU ON DGU.Id = T.IdDetalleGrupoUsuario " +
            "WHERE DGU.IdUsuario = ";

    public ObtenerDatos (Activity activity) {
        this.activity = activity;
    }

    @Override

    protected String doInBackground(Void... params) {
        conexionBaseDatos = new ConexionBaseDatos(activity);
        SharedPreferences sharedPreferences =
                activity.getSharedPreferences("asistencias", Context.MODE_PRIVATE);

        int id = sharedPreferences.getInt("Id", 1);
        String resultado = "";

        //Se insertan los grupos
        resultado = peticion(SELECT_GRUPO);
        insertarGrupos(resultado);

        //se insertan los alumnos
        resultado = peticion(SELECT_ALUMNOS + id);
        insertarAlumnos(resultado);

        /*//Se insertan los detalles de grupo de usuario
        resultado = peticion(SELECT_DETALLE_GRUPO_USUARIO + id);
        insertarDetalleGrupoUsuario(resultado);*/

        //Se insertan los detalles de grupo de alumnos
        resultado = peticion(SELECT_DETALLE_GRUPO_ALUMNO + id);
        insertarDetalleGrupoAlumno(resultado);

        //se insertan los trabajos
        resultado = peticion(SELECT_TRABAJO + id);
        insertarTrabajo(resultado);

        return null;
    }

    @Override
    protected void onPostExecute(String resultado) {

        if (listViewGrupos != null) {

            listViewGrupos.setAdapter(
                    new AdaptadorListaGrupos(
                            activity,
                            R.layout.item_lista_grupos,
                            conexionBaseDatos.obtenerGrupos()));
        }

        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    public String peticion (String select) {
        HttpPost httpPost     = new HttpPost(URL_POST);
        HttpClient httpClient = new DefaultHttpClient();
        String resultado      = "";

        //Se inicializa la base de datos
        conexionBaseDatos = new ConexionBaseDatos(activity);

        try {
            List<NameValuePair> mNameValuePairs = new ArrayList<NameValuePair>(1);
            mNameValuePairs.add(new BasicNameValuePair("query", select));
            httpPost.setEntity(new UrlEncodedFormEntity(mNameValuePairs));
            HttpResponse response = httpClient.execute(httpPost);

            HttpEntity entity = response.getEntity();
            resultado = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultado;
    }

    public void insertarGrupos(String resultado) {

        try {
            JSONArray jsonArray = new JSONObject(resultado).getJSONArray("data");

            for (int x = 0; x < jsonArray.length(); x++) {
                Grupo grupo = new Grupo(
                        jsonArray.getJSONObject(x).getString("gradoygrupo"));

                //Se inserta en la base de datos
                conexionBaseDatos.insertarGrupo(grupo);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void insertarAlumnos(String resultado) {

        try {
            JSONArray jsonArray = new JSONObject(resultado).getJSONArray("data");

            for (int x = 0; x < jsonArray.length(); x++) {
                Alumno alumno = new Alumno();

                alumno.setCodigo(jsonArray.getJSONObject(x).getInt("Codigo"));
                alumno.setNombre(jsonArray.getJSONObject(x).getString("Nombre"));
                alumno.setSalon(jsonArray.getJSONObject(x).getString("Salon_Grupo"));
                alumno.setFechaNacimiento(jsonArray.getJSONObject(x).getString("Fecha_Nacimiento"));
                alumno.setIdUsuario(jsonArray.getJSONObject(x).getInt("ID_usuario"));

                //Se inserta en la base de datos
                conexionBaseDatos.insertarAlumno(alumno);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void insertarDetalleGrupoUsuario(String resultado) {

        try {
            JSONArray jsonArray = new JSONObject(resultado).getJSONArray("data");

            for (int x = 0; x < jsonArray.length(); x++) {
                DetalleGrupoUsuario detalleGrupoUsuario = new DetalleGrupoUsuario(
                        jsonArray.getJSONObject(x).getInt("Id"),
                        jsonArray.getJSONObject(x).getInt("IdUsuario"),
                        jsonArray.getJSONObject(x).getInt("IdGrupo"));

                //Se inserta en la base de datos
                conexionBaseDatos.insertarDetalleGrupoUsuario(detalleGrupoUsuario);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void insertarDetalleGrupoAlumno(String resultado) {

        try {
            JSONArray jsonArray = new JSONObject(resultado).getJSONArray("data");

            for (int x = 0; x < jsonArray.length(); x++) {
                DetalleGrupoAlumno detalleGrupoAlumno = new DetalleGrupoAlumno(
                        jsonArray.getJSONObject(x).getInt("IdAlumno"),
                        jsonArray.getJSONObject(x).getInt("IdDetalleGrupoUsuario"));

                //Se inserta en la base de datos
                conexionBaseDatos.insertarDetalleGrupoAlumno(detalleGrupoAlumno);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void insertarTrabajo(String resultado) {

        try {
            JSONArray jsonArray = new JSONObject(resultado).getJSONArray("data");

            for (int x = 0; x < jsonArray.length(); x++) {
                Trabajo trabajo = new Trabajo(
                        jsonArray.getJSONObject(x).getInt("Id"),
                        jsonArray.getJSONObject(x).getInt("IdDetalleGrupoUsuario"),
                        jsonArray.getJSONObject(x).getString("Nombre"),
                        jsonArray.getJSONObject(x).getString("FechaAlta"),
                        jsonArray.getJSONObject(x).getString("FechaFin"),
                        jsonArray.getJSONObject(x).getInt("Evaluacion"),
                        jsonArray.getJSONObject(x).getString("Nota"));

                //Se inserta en la base de datos
                conexionBaseDatos.insertarTrabajo(trabajo);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public ListView getListViewGrupos() {
        return listViewGrupos;
    }

    public void setListViewGrupos(ListView listViewGrupos) {
        this.listViewGrupos = listViewGrupos;
    }

    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return swipeRefreshLayout;
    }

    public void setSwipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout) {
        this.swipeRefreshLayout = swipeRefreshLayout;
    }
}
