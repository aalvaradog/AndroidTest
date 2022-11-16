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

    EditText carnet, contraseña;
    Button iniciarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carnet=(EditText)findViewById(R.id.Carnet);
        contraseña=(EditText)findViewById(R.id.Contraseña);
        iniciarSesion=(Button)findViewById(R.id.IniciarSesion);

        iniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    public void registro(View view){
        Intent registro = new Intent(this, Registro.class);
        startActivity(registro);
    }

    public void login() {
        boolean resultado = false;
        try {
            Leer read = new Leer();
            resultado = read.login(carnet.getText().toString(), contraseña.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (resultado == false) {
            Toast.makeText(getApplicationContext(), "Las credenciales no coinciden", Toast.LENGTH_SHORT).show();
        }
    }
}