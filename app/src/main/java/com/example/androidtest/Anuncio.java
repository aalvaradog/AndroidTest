package com.example.androidtest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Anuncio {
    private String titulo;
    private String descripcion;
    private String sede;
    private String precio;
    private String id;
    private byte[] imagen;
    private String carnet;


    public Anuncio(){}

    public Anuncio(String titulo, String descripcion, String sede, String precio, byte[] imagenUrl, String id) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.sede = sede;
        this.precio = precio;
        this.imagen = imagen;
        this.id=id;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public byte[] getImagenUrl() {
        return imagen;
    }

    public void setImagenUrl(byte[] byteArray) {
        this.imagen=byteArray;
    }

    public Bitmap convertirABitmap(byte[] byteArray){
        if(byteArray!=null) {
            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            return bmp;
        }else{
            return null;
        }
    }

}
