package com.example.webserviceconsumer;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webserviceconsumer.adapter.adapterPlayList;
import com.example.webserviceconsumer.adapter.adapterTrackList;
import com.example.webserviceconsumer.modelos.Track;
import com.example.webserviceconsumer.modelos.playList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TracksActivity extends AppCompatActivity implements adapterTrackList.OnItemClickListener{

    private adapterTrackList adapterChat;
    private RecyclerView lista_mensajes;
    private TextView nombrePlay,numeroCanciones;
    private String nombreAlbun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracks);

        nombrePlay= findViewById(R.id.txt_nombrePlayList);
        numeroCanciones= findViewById(R.id.txt_numeroCanciones);

        nombreAlbun =getIntent().getExtras().getString("nombrePlayList");

        numeroCanciones.setText(getIntent().getExtras().getString("numeroCanciones")+" Canciones en total");
        nombrePlay.setText("Nombre del play list "+nombreAlbun);

        String UrlTack = getIntent().getExtras().getString("playList");

        lista_mensajes = findViewById(R.id.lista_Tracklist);
        lista_mensajes.setLayoutManager(new LinearLayoutManager(this));

        adapterChat = new adapterTrackList();
        adapterChat.setListener(this);

        new Thread(()->{
            new ServiceManager.Comentaio2GET(UrlTack,new ServiceManager.Comentaio2GET.OnResponseListener() {
                @Override
                public void onResponse(String response) {
                    runOnUiThread(()->{


                        try {
                            JSONObject jsonjObject = new JSONObject(response);
                            JSONArray listplayList = jsonjObject.getJSONArray("data");

                            for (int i = 0; i < listplayList.length(); i++) {
                                try {

                                    Track track = new Track();
                                    JSONObject jsonObjectHijo = listplayList.getJSONObject(i);
                                    JSONObject user = jsonObjectHijo.getJSONObject("artist");
                                    String nombreArtista = user.getString("name");
                                    String nombreCancion = jsonObjectHijo.getString("title");
                                    String AñoLanzamiento = "" + jsonObjectHijo.getInt("time_add");
                                    Uri imagen = Uri.parse(user.getString("picture"));
                                    Uri link = Uri.parse(jsonObjectHijo.getString("link"));
                                    String duracion = jsonObjectHijo.getString("duration");

                                    track.setDuracion(duracion);
                                    track.setAñoLanzamiento(AñoLanzamiento);
                                    track.setImagen(imagen);
                                    track.setNombreArtista(nombreArtista);
                                    track.setNombreCancion(nombreCancion);
                                    track.setLink(link);

                                    adapterChat.agregarTrackList(track);


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

    @Override
    public void onItemClick(Track track) {

        Intent i =new Intent(this,TrackActivity.class);
        i.putExtra("nombreCancion",track.getNombreCancion());
        i.putExtra("nombreArtista",track.getNombreArtista());
        i.putExtra("NombreAlbun",nombreAlbun);
        i.putExtra("imagen",track.getImagen()+"");
        i.putExtra("link",track.getLink()+"");
        i.putExtra("duracion",track.getDuracion());
        startActivity(i);
        finish();
    }
}
