package com.example.androidtest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Borrar {

    public Borrar(){}

    public boolean borrarAnuncio(String id){
        boolean salida=false;
        try{
            String q="delete from Productos where id="+id;
            Conexion conn= new Conexion();
            Statement statement=conn.conexionBD().createStatement();
            int resultado=statement.executeUpdate(q);
            if(resultado==1){
                salida=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salida;
    }
}
