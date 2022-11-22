package com.example.androidtest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
            AlertDialog.Builder dialog=new AlertDialog.Builder(this);
            dialog.setMessage("Ingrese un carnet y una contraseña válidos");
            dialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            AlertDialog d=dialog.create();
            d.show();
        }else{
            Intent menu= new Intent(this, Menu.class);
            menu.putExtra("Carnet",carnet.getText().toString());
            startActivity(menu);
        }
    }

    public Bundle getMyData2(){
        Bundle hm = new Bundle();
        hm.putString("Carnet",carnet.getText().toString());
        return hm;
    }
}