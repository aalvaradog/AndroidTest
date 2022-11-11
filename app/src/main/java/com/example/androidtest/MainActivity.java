package com.example.androidtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.sourceforge.jtds.jdbcx.proxy.PreparedStatementProxy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.example.androidtest.Conexion;
import com.example.androidtest.Insertar;

public class MainActivity extends AppCompatActivity {

    EditText usuario, contraseña;
    Button iniciarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario=(EditText)findViewById(R.id.Usuario);
        contraseña=(EditText)findViewById(R.id.Contraseña);
        iniciarSesion=(Button)findViewById(R.id.IniciarSesion);

        iniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertar();
            }
        });
    }

    public void registro(View view){
        Intent registro = new Intent(this, Registro.class);
        startActivity(registro);
    }

    public void insertar(){
        try{
            Insertar in=new Insertar();
            in.insertar(usuario.getText().toString(), contraseña.getText().toString());
            Toast.makeText(getApplicationContext(),"Registro agregado",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}