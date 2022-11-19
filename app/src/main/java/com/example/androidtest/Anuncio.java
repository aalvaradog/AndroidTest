package com.example.androidtest;

public class Anuncio {
    private String titulo;
    private String descripcion;
    private String sede;
    private String precio;
    private String imagenUrl;

    public Anuncio(String titulo, String descripcion, String sede, String precio, String imagenUrl) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.sede = sede;
        this.precio = precio;
        this.imagenUrl = imagenUrl;
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

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }
}
