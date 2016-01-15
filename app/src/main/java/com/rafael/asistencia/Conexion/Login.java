package com.rafael.asistencia.Conexion;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.rafael.asistencia.Grupos;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class Login {

    private String correo;
    private String password;
    private EditText editTextCorreo;
    private EditText editTextPassword;
    private Activity activity;

    private final String URL_POST = "http://asistenciaprueba.esy.es/asistencia/asistencia.php";

    public Login(EditText editTextCorreo, EditText editTextPassword, Activity activity) {
        this.correo = editTextCorreo.getText().toString();
        this.password = editTextPassword.getText().toString();
        this.editTextCorreo = editTextCorreo;
        this.editTextPassword = editTextPassword;
        this.activity = activity;

        editTextCorreo.setEnabled(false);
        editTextPassword.setEnabled(false);
    }


    public void login () {
        StringRequest postRequest = new StringRequest(Request.Method.POST, URL_POST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String resultado) {
                        Log.i("Login", resultado);

                        if (resultado.contains("false")) {
                            Toast.makeText(activity,
                                    "La contraseña o el correo son incorrectos",
                                    Toast.LENGTH_SHORT).show();
                            editTextPassword.setEnabled(true);
                            editTextCorreo.setEnabled(true);
                        } else if (resultado.equals("")) {
                            Toast.makeText(activity,
                                    "Hubo un error al iniciar sesión",
                                    Toast.LENGTH_SHORT).show();
                            editTextPassword.setEnabled(true);
                            editTextCorreo.setEnabled(true);
                        } else {

                            try {
                                String correo = new JSONObject(resultado)
                                        .getJSONArray("data")
                                        .getJSONObject(0).getString("Correo");

                                int id = new JSONObject(resultado)
                                        .getJSONArray("data")
                                        .getJSONObject(0).getInt("ID");

                                String nombre = new JSONObject(resultado)
                                        .getJSONArray("data")
                                        .getJSONObject(0).getString("Nombre");

                                SharedPreferences sharedPreferences =
                                        activity.getSharedPreferences("asistencias", Context.MODE_PRIVATE);

                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putString("Correo", correo);
                                editor.putInt("Id", id);
                                editor.putString("Nombre", nombre);

                                editor.apply();

                                Intent intent = new Intent(activity, Grupos.class);
                                activity.startActivity(intent);
                                activity.finish();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();

                        Toast.makeText(activity,
                                "Hubo un error al iniciar sesión",
                                Toast.LENGTH_SHORT).show();
                        editTextPassword.setEnabled(true);
                        editTextCorreo.setEnabled(true);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<>();
                // the POST parameters:

                params.put("query", "SELECT * FROM datos_usuario WHERE Correo = '"
                        + correo
                        + "' AND Contrasena = '"
                        + password + "'");

                return params;
            }
        };
        Volley.newRequestQueue(activity).add(postRequest);

    }


}
