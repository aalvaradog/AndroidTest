package com.example.androidtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class eliminarAnuncio extends AppCompatActivity {

    String carnet;
    EliminarAdapter eliminarAdapter;
    ArrayList<Anuncio> anuncios=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_anuncio);

        ListView lv=findViewById(R.id.anunciosPublicados);
        carnet=getIntent().getStringExtra("Carnet");
        ImageButton volver=(ImageButton) findViewById(R.id.volverEliminar);

        Leer leerAnuncios=new Leer();
        anuncios=leerAnuncios.consultarAnuncios(carnet);
        eliminarAdapter=new EliminarAdapter(this, R.layout.item_anuncio_eliminar, anuncios, carnet);

        lv.setAdapter(eliminarAdapter);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volver();
            }
        });
    }

    public void volver(){
        Intent i=new Intent(this,Menu.class);
        i.putExtra("Carnet",carnet);
        startActivity(i);
    }
}