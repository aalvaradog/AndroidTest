package com.example.androidtest;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class Leer extends AppCompatActivity {

    public boolean validarCarnet(String carnet){
        boolean salida=true;
        if(carnet.length()<9){
            return salida=false;
        }
        try{
            String q="Select * from Usuarios where carnet="+carnet;
            Conexion conn= new Conexion();
            Statement statement=conn.conexionBD().createStatement();
            ResultSet result=statement.executeQuery(q);
            if(result.next()){
                salida=false;
            }
            //Toast.makeText(getApplicationContext(),"Registro agregado",Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salida;
    }

    public ArrayList<Anuncio> cargarMenu() {
        ArrayList<Anuncio> salida=new ArrayList<>();
        try {
            String q = "Select nombre, descripcion, sede, precio, imagen, idUsuario, id from Productos;";
            Conexion conn = new Conexion();
            Statement statement = conn.conexionBD().createStatement();
            ResultSet result = statement.executeQuery(q);
            while (result.next()) {
                Anuncio anuncio=new Anuncio();
                anuncio.setTitulo(result.getString(1));
                anuncio.setDescripcion(result.getString(2));
                anuncio.setSede(result.getString(3));
                anuncio.setPrecio(result.getString(4));
                anuncio.setImagenUrl(result.getBytes(5));
                anuncio.setCarnet(result.getString(6));
                anuncio.setId(result.getString(7));
                salida.add(anuncio);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return salida;
    }

    public ArrayList<Anuncio> consultarAnuncios(String carnet) {
        ArrayList<Anuncio> salida=new ArrayList<>();
        try {
            String q = "Select nombre, descripcion, sede, precio, imagen, id, idUsuario from Productos where idUsuario="+carnet;
            Conexion conn = new Conexion();
            Statement statement = conn.conexionBD().createStatement();
            ResultSet result = statement.executeQuery(q);
            while (result.next()) {
                Anuncio anuncio=new Anuncio();
                anuncio.setTitulo(result.getString(1));
                anuncio.setDescripcion(result.getString(2));
                anuncio.setSede(result.getString(3));
                anuncio.setPrecio(result.getString(4));
                anuncio.setImagenUrl(result.getBytes(5));
                anuncio.setId(result.getString(6));
                salida.add(anuncio);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return salida;
    }

    public boolean login(String carnet, String contraseña){
        boolean salida=false;
        try{
            String q="Select * from Usuarios where carnet= "+carnet+" and contraseña= '"+contraseña+"';";
            Conexion conn= new Conexion();
            Statement statement=conn.conexionBD().createStatement();
            ResultSet result=statement.executeQuery(q);
            if(result.next()){
                salida=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salida;
    }

    public String consultarNombre(String carnet){
        String salida = null;
        try{
            String q="Select nombre,apellidos from Usuarios where carnet="+carnet;
            Conexion conn= new Conexion();
            Statement statement=conn.conexionBD().createStatement();
            ResultSet result=statement.executeQuery(q);
            if(result.next()){
                salida=result.getString(1)+" "+result.getString(2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salida;
    }

    public ArrayList<Sede> consultarSedes(){
        ArrayList<Sede> sedes=new ArrayList<>();
        try{
            String q="Select id, descripcion from Sedes;";
            Conexion conn = new Conexion();
            Statement statement = conn.conexionBD().createStatement();
            ResultSet result = statement.executeQuery(q);
            while (result.next()) {
                Sede sede=new Sede();
                sede.setId(result.getString(1));
                sede.setDescripcion(result.getString(2));
                sedes.add(sede);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sedes;
    }

    public ArrayList<Categoria> consultarCategorias(){
        ArrayList<Categoria> categorias=new ArrayList<>();
        try{
            String q="Select id, descripcion from Categorias;";
            Conexion conn = new Conexion();
            Statement statement = conn.conexionBD().createStatement();
            ResultSet result = statement.executeQuery(q);
            while (result.next()) {
                Categoria categoria=new Categoria();
                categoria.setId(result.getString(1));
                categoria.setDescripcion(result.getString(2));
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
    }

    public String consultarSede(String id){
        String salida="";
        try{
            String q="Select id, descripcion from Sedes where id="+id;
            Conexion conn = new Conexion();
            Statement statement = conn.conexionBD().createStatement();
            ResultSet result = statement.executeQuery(q);
            while (result.next()) {
                salida=result.getString(2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salida;
    }

    public String consultarCategoria(String id){
        String salida="";
        try{
            String q="Select id, descripcion from Categorias where id="+id;
            Conexion conn = new Conexion();
            Statement statement = conn.conexionBD().createStatement();
            ResultSet result = statement.executeQuery(q);
            while (result.next()) {
                salida=result.getString(2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salida;
    }
}
