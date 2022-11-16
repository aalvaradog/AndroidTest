package com.example.androidtest;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.Callable;

public class Leer extends AppCompatActivity {


    public boolean validarCarnet(String carnet){
        boolean salida=true;
        try{
            String q="exec validar_carnet"+carnet;
            Conexion conn= new Conexion();
            Statement statement=conn.conexionBD().createStatement();
            ResultSet result=statement.executeQuery(q);
            if(result!=null){
                salida=false;
            }
            Toast.makeText(getApplicationContext(),"Registro agregado",Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salida;
    }
}
