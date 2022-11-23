package com.example.androidtest;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidtest.Conexion;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Insertar extends AppCompatActivity {

    public void insertar(String usuario, String contraseña){
        try{
            Conexion conn= new Conexion();
            PreparedStatement pst=conn.conexionBD().prepareStatement("insert into tbl_departamentos values(?,?)");
            pst.setString(1,usuario);
            pst.setString(2,contraseña);
            pst.executeUpdate();

            Toast.makeText(getApplicationContext(),"Registro agregado",Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int insertarReporte(String id, String motivo, String explicacion){
        int salida=0;
        try{
            Conexion conn= new Conexion();
            PreparedStatement pst=conn.conexionBD().prepareStatement("insert into Reportes values(?,?,?)");
            pst.setString(1,id);
            pst.setString(2,motivo);
            pst.setString(3,explicacion);
            pst.executeUpdate();
            salida=0;
            //Toast.makeText(getApplicationContext(),"Reporte agregado",Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            salida=1;
            e.printStackTrace();
        }
        return salida;
    }

    public int insertarAnuncio(String carnet, String categoria, String nombre, String sede, String descripcion, String precio, byte[] imagen){
        int salida=0;
        try{
            Conexion conn= new Conexion();
            PreparedStatement pst=conn.conexionBD().prepareStatement("insert into Productos values(?,?,?,?,?,?,?)");
            pst.setString(1,carnet);
            pst.setString(2,categoria);
            pst.setString(3,nombre);
            pst.setString(4,descripcion);
            pst.setString(5,sede);
            pst.setString(6,precio);
            pst.setBytes(7,imagen);            pst.executeUpdate();
            salida=0;
            Toast.makeText(getApplicationContext(),"Registro agregado",Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            salida=1;
            e.printStackTrace();
        }
        return salida;
    }

    public int registrarUsuario(String nombre, String apellidos, String email, String carnet, String contraseña){
        int salida=0;
        try{
            Conexion conn= new Conexion();
            PreparedStatement pst=conn.conexionBD().prepareStatement("insert into Usuarios values(?,?,?,?,?)");
            pst.setString(1,carnet);
            pst.setString(2,nombre);
            pst.setString(3,apellidos);
            pst.setString(4,email);
            pst.setString(5,contraseña);
            pst.executeUpdate();
            salida=0;
            Toast.makeText(getApplicationContext(),"Registro agregado",Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            salida=1;
            e.printStackTrace();
        }
        return salida;
    }
}
