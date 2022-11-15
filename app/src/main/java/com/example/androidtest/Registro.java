package com.example.androidtest;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity {

    EditText nombre, apellidos, contraseña, email, carnet, sede;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

    }
    public void registrar(){
        try{
            Insertar in=new Insertar();
            in.registrarUsuario(usuario.getText().toString(), contraseña.getText().toString());
            Toast.makeText(getApplicationContext(),"Registro agregado",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}