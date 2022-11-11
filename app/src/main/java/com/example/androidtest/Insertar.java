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
}
