package com.example.webserviceconsumer.modelos;

import android.net.Uri;

public class Track {

    private String NombreCancion;
    private String NombreArtista;
    private String AñoLanzamiento;
    private Uri imagen;
    private Uri link;
    private String nombreAlbun;
    private String duracion;

    public Track(){

    }

    public Track(String nombreCancion, String nombreArtista, String añoLanzamiento, Uri imagen,Uri link,String nombreAlbun,String duracion) {
        this.NombreCancion = nombreCancion;
        this.NombreArtista = nombreArtista;
        this.AñoLanzamiento = añoLanzamiento;
        this.imagen = imagen;
        this.link=link;
        this.nombreAlbun=nombreAlbun;
        this.duracion=duracion;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getNombreAlbun() {
        return nombreAlbun;
    }

    public void setNombreAlbun(String nombreAlbun) {
        this.nombreAlbun = nombreAlbun;
    }

    public Uri getLink() {
        return link;
    }

    public void setLink(Uri link) {
        this.link = link;
    }

    public String getNombreCancion() {
        return NombreCancion;
    }

    public void setNombreCancion(String nombreCancion) {
        NombreCancion = nombreCancion;
    }

    public String getNombreArtista() {
        return NombreArtista;
    }

    public void setNombreArtista(String nombreArtista) {
        NombreArtista = nombreArtista;
    }

    public String getAñoLanzamiento() {
        return AñoLanzamiento;
    }

    public void setAñoLanzamiento(String añoLanzamiento) {
        AñoLanzamiento = añoLanzamiento;
    }

    public Uri getImagen() {
        return imagen;
    }

    public void setImagen(Uri imagen) {
        this.imagen = imagen;
    }
}
