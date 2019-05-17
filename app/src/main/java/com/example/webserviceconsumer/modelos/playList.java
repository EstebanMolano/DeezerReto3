package com.example.webserviceconsumer.modelos;

import android.net.Uri;

import java.io.Serializable;


public class playList {

    private String nombreLista;

    private String nombreCreador;

    private String numeroCanciones;

    private Uri imagen;

    private String listaTracks;

    private String numFans;

    public playList(){

    }

    public playList(String nombreLista,String nombreCreador,String numeroCanciones, Uri imagen,String listaTracks, String numFans){
        this.nombreLista=nombreLista;
        this.nombreCreador=nombreCreador;
        this.numeroCanciones=numeroCanciones;
        this.imagen=imagen;
        this.listaTracks=listaTracks;
        this.numFans=numFans;
    }

    public String getNumFans() {
        return numFans;
    }

    public void setNumFans(String numFans) {
        this.numFans = numFans;
    }

    public String getListaTracks() {
        return listaTracks;
    }

    public void setListaTracks(String listaTracks) {
        this.listaTracks = listaTracks;
    }

    public String getNombreLista() {
        return nombreLista;
    }

    public void setNombreLista(String nombreLista) {
        this.nombreLista = nombreLista;
    }

    public String getNombreCreador() {
        return nombreCreador;
    }

    public void setNombreCreador(String nombreCreador) {
        this.nombreCreador = nombreCreador;
    }

    public String getNumeroCanciones() {
        return numeroCanciones;
    }

    public void setNumeroCanciones(String numeroCanciones) {
        this.numeroCanciones = numeroCanciones;
    }

    public Uri getImagen() {
        return imagen;
    }

    public void setImagen(Uri imagen) {
        this.imagen = imagen;
    }
}
