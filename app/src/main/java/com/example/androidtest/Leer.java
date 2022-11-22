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
            String q = "Select nombre, descripcion, sede, precio, imagen, idUsuario from Productos;";
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
}
