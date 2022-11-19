package com.aplicacion.ejercicio22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.aplicacion.ejercicio22.Configuraciones.Objeto;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lista;
    Button activityBuscar;
    RequestQueue requestQueue;
    List<String> argDatos = new ArrayList<>();

    private static final String URL = "https://jsonplaceholder.typicode.com/todos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        jsonRequest();
        activityBuscar.setOnClickListener(this::onClick);
    }


    private void onClick(View view) {
        Intent ventana = new Intent(getApplicationContext(), ActivityConsultar.class);
        startActivity(ventana);
        finish();
    }


    private void init(){
        lista = findViewById(R.id.Lista);
        activityBuscar = findViewById(R.id.btnConsultar);
        requestQueue = Volley.newRequestQueue(this);
    }

    private void jsonRequest(){

        JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, URL, null,

                response -> {

                    if (response.length() > 0) {

                        for (int i = 0; i < response.length(); i++) {

                            try {

                                JSONObject objeto = response.getJSONObject(i);
                                Objeto obj = new Objeto();
                                obj.setUserId(Integer.parseInt(objeto.get("userId").toString()));
                                obj.setId(Integer.parseInt(objeto.get("id").toString()));
                                obj.setTitle(objeto.get("title").toString());
                                obj.setBodycompleted(objeto.get("completed").toString());
                                argDatos.add("UserId: "+obj.getUserId() + "\nID: " + obj.getId() + "\nTitle: " + obj.getTitle() + "\nCompleted: " + obj.getBodycompleted());
                                ArrayAdapter<String> adp = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, argDatos);
                                lista.setAdapter(adp);

                            } catch (JSONException e) {

                                message(e.getMessage());

                            }
                        }
                    }
                },
                error -> message(error.getMessage())
        );
        requestQueue.add(jsonRequest);
    }

    public void message(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}