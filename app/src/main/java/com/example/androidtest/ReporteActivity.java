package com.example.androidtest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ReporteActivity extends AppCompatActivity {

    String carnet, idAnuncio;
    Button enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte);

        TextView motivo=(TextView) findViewById(R.id.motivoReporte);
        TextView explicacion=(TextView) findViewById(R.id.explicacionReporte);
        ImageButton volver=(ImageButton)findViewById(R.id.volverReporte);
        enviar=(Button)findViewById(R.id.enviarReporte);

        carnet=getIntent().getStringExtra("Carnet");
        idAnuncio=getIntent().getStringExtra("Id");

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),detallesActivity.class);
                intent.putExtra("Carnet",carnet);
                startActivity(intent);
            }
        });

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Insertar insertar=new Insertar();
                int resultado=insertar.insertarReporte(idAnuncio, motivo.getText().toString(), explicacion.getText().toString());
                if(resultado==0){
                    Intent intent=new Intent(v.getContext(),detallesActivity.class);
                    intent.putExtra("Carnet",carnet);
                    startActivity(intent);
                }else{
                    AlertDialog.Builder dialog=new AlertDialog.Builder(v.getContext());
                    dialog.setMessage("No se pudo enviar el reporte");
                    dialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent=new Intent(v.getContext(),detallesActivity.class);
                            intent.putExtra("Carnet",carnet);
                            startActivity(intent);
                        }
                    });
                    AlertDialog d=dialog.create();
                    d.show();
                }
            }
        });
    }
}