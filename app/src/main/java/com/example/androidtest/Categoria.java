package com.example.androidtest;

public class Categoria {
    private String id;
    private String descripcion;

    public void Categoria(){}

    public void Categoria(String id, String descripcion){
        this.id=id;
        this.descripcion=descripcion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
