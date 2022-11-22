package com.example.androidtest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Locale;

public class Registro extends AppCompatActivity {

    EditText nombre, apellidos, contraseña, email, carnet, sede;
    Button registrar;
    ImageButton volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nombre=(EditText)findViewById(R.id.nombre);
        apellidos=(EditText)findViewById(R.id.apellidos);
        email=(EditText)findViewById(R.id.email);
        contraseña=(EditText)findViewById(R.id.contraseña);
        carnet=(EditText)findViewById(R.id.carnet);
        volver=(ImageButton)findViewById(R.id.volver);

        registrar=(Button)findViewById(R.id.registrar);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Leer read=new Leer();
                boolean validacion=read.validarCarnet(carnet.getText().toString());
                if(validacion==false || validarContraseña(contraseña.getText().toString())==false){
                    AlertDialog.Builder dialog=new AlertDialog.Builder(v.getContext());
                    dialog.setMessage("Por favor ingrese un carnet y una contraseña válidos");
                    dialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    AlertDialog d=dialog.create();
                    d.show();
                    //Toast.makeText(getApplicationContext(),"Carnet inválido",Toast.LENGTH_SHORT).show();
                }else{
                    registrar();
                }
            }
        });
    }

    private void exito(Context context){
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setMessage("Registro exitoso");
        dialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(context,MainActivity.class);
                startActivity(intent);
            }
        });
        AlertDialog d=dialog.create();
        d.show();
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
            exito(this);
            //Toast.makeText(getApplicationContext(),"Usuario registrado",Toast.LENGTH_SHORT).show();
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