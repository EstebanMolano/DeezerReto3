package com.example.webserviceconsumer;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class TrackActivity extends AppCompatActivity {

    private ProgressBar progres;
    private TextView nombreConcion,nombreArtista,nombreAlbun,duracion;
    private ImageView img;
    private Button btn_escuchar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);

        nombreConcion=findViewById(R.id.txt_nombreCancion);
        nombreArtista=findViewById(R.id.txt_nombreArtista);
        nombreAlbun=findViewById(R.id.txt_albunCancion);
        duracion=findViewById(R.id.txt_duracion);

        nombreAlbun.setText(getIntent().getExtras().getString("NombreAlbun"));
        nombreArtista.setText(getIntent().getExtras().getString("nombreArtista"));
        nombreConcion.setText(getIntent().getExtras().getString("nombreCancion"));
        duracion.setText("Duracionde la cancion es: " + getIntent().getExtras().getString("duracion"));

        Uri i = Uri.parse(getIntent().getExtras().getString("imagen"));

        img = findViewById(R.id.img_Cancion);
        Glide.with(this).load(i).into(img);


        btn_escuchar = findViewById(R.id.btn_escuchar);
        btn_escuchar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri UrlTack = Uri.parse(getIntent().getExtras().getString("link"));
                Intent i = new Intent(Intent.ACTION_VIEW,UrlTack);
                startActivity(i);
            }
        });


    }
}
