package com.example.androidtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class Registro extends AppCompatActivity {

    EditText nombre, apellidos, contraseña, email, carnet, sede;
    Button registrar, volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nombre=(EditText)findViewById(R.id.nombre);
        apellidos=(EditText)findViewById(R.id.apellidos);
        email=(EditText)findViewById(R.id.email);
        contraseña=(EditText)findViewById(R.id.contraseña);
        carnet=(EditText)findViewById(R.id.carnet);

        registrar=(Button)findViewById(R.id.registrar);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Leer read=new Leer();
                boolean validacion=read.validarCarnet(carnet.getText().toString());
                if(validacion=false){
                    Toast.makeText(getApplicationContext(),"Carnet inválido",Toast.LENGTH_SHORT).show();
                }*/
                if(validarContraseña(contraseña.getText().toString())){
                    registrar();
                }else{
                    Toast.makeText(getApplicationContext(),"Contraseña invalida",Toast.LENGTH_SHORT).show();
                };

            }
        });
    }
    public void volver(View view){
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }

    //Método para insertar los datos en la base de datos
    public void registrar(){
        Insertar in=new Insertar();
        int resultado=0;
        try{
            resultado=in.registrarUsuario(nombre.getText().toString(),
                    apellidos.getText().toString(),
                    email.getText().toString(),
                    carnet.getText().toString(),
                    contraseña.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(resultado==0){
            Toast.makeText(getApplicationContext(),"Usuario registrado",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"No se pudo agregar el usuario",Toast.LENGTH_SHORT).show();
        }
    }
    //Método para validar la contraseña
    public boolean validarContraseña(String cadena) {
        boolean resultado = true;
        if (cadena.length() > 20 || cadena.length() < 8) {
            resultado = false;
        }
        if (cadena.equals(cadena.toLowerCase()) && cadena.equals(cadena.toUpperCase())) {
            resultado = false;
        }
        if (cadena.matches("[a-zA-Z]*")) {
            resultado = false;
        }
        if (cadena.matches("[0-9]")) {
            resultado = false;
        }
        return resultado;
    }
}