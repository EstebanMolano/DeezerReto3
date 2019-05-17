package com.example.webserviceconsumer;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.webserviceconsumer.adapter.adapterPlayList;
import com.example.webserviceconsumer.modelos.playList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements adapterPlayList.OnItemClickListener{


    private adapterPlayList adapterChat;
    private RecyclerView lista_mensajes;

    private EditText txt_busqueda;
    private Button btn_searchplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_searchplay = findViewById(R.id.btn_buscarList);
        txt_busqueda = findViewById(R.id.txt_busqueda);

        lista_mensajes = findViewById(R.id.lista_playlist);
        lista_mensajes.setLayoutManager(new LinearLayoutManager(this));

        adapterChat = new adapterPlayList();
        adapterChat.setListener(this);



        btn_searchplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!txt_busqueda.toString().equals("")){

                    adapterChat.limpiarPlayList();
                    adapterChat.notifyDataSetChanged();
                new Thread(()->{
                    new ServiceManager.Comentaio1GET(txt_busqueda.getText().toString(),new ServiceManager.Comentaio1GET.OnResponseListener() {
                        @Override
                        public void onResponse(String response) {
                            runOnUiThread(()->{

                                try {
                                    JSONObject jsonjObject = new JSONObject(response);
                                    JSONArray listplayList = jsonjObject.getJSONArray("data");
                                    for (int i = 0; i < listplayList.length(); i++)
                                    {
                                        try {

                                            playList playlist = new playList();
                                            JSONObject jsonObjectHijo = listplayList.getJSONObject(i);
                                            JSONObject user = jsonObjectHijo.getJSONObject("user");
                                            String nombreCreador = user.getString("name");
                                            String nombreLista = jsonObjectHijo.getString("title");
                                            String numerocanciones = ""+ jsonObjectHijo.getInt("nb_tracks");
                                            Uri imagen = Uri.parse(jsonObjectHijo.getString("picture"));
                                            String tracklist =""+ jsonObjectHijo.getString("tracklist");
                                            playlist.setNombreLista(nombreLista);
                                            playlist.setNombreCreador(nombreCreador);
                                            playlist.setNumeroCanciones(numerocanciones);
                                            playlist.setImagen(imagen);
                                            playlist.setListaTracks(tracklist);
                                            adapterChat.agregarPlayList(playlist);



                                        } catch (JSONException e) {
                                            Log.e("Parser JSON", e.toString());
                                        }
                                    }



                                    lista_mensajes.setAdapter(adapterChat);
                                    lista_mensajes.setHasFixedSize(true);
                                    adapterChat.notifyDataSetChanged();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            });
                        }
                    });
                }).start();
                }

            }
        });




    }

    @Override
    public void onItemClick(playList playlist) {
        Intent i = new Intent(this,TracksActivity.class);
        i.putExtra("playList",playlist.getListaTracks());
        i.putExtra("nombrePlayList",playlist.getNombreLista());
        i.putExtra("numeroCanciones",playlist.getNumeroCanciones());

        startActivity(i);

    }
}
