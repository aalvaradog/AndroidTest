package com.example.androidtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class detallesActivity extends AppCompatActivity {


    ImageButton volver;
    String carnet, titulo, descripcion, sede, precio;
    TextView tTitulo, tDescripcion, tSede, tPrecio;
    ImageView iImagen;
    byte[] imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        volver = (ImageButton) findViewById(R.id.volverDetalles);

        carnet = getIntent().getStringExtra("Carnet");

        titulo = getIntent().getStringExtra("Titulo");
        descripcion = getIntent().getStringExtra("Descripcion");
        precio = getIntent().getStringExtra("Precio");
        sede = getIntent().getStringExtra("Sede");
        imagen = getIntent().getByteArrayExtra("Imagen");

        tTitulo = (TextView) findViewById(R.id.tituloDetalles);
        tDescripcion = (TextView) findViewById(R.id.descripcionDetalles);
        tPrecio = (TextView) findViewById(R.id.precioDetalles);
        iImagen = (ImageView) findViewById(R.id.imagenDetalles);


        tTitulo.setText(titulo);
        tDescripcion.setText(descripcion);
        tPrecio.setText(precio);
        iImagen.setImageBitmap(BitmapFactory.decodeByteArray(imagen, 0, imagen.length));

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menu = new Intent(getApplicationContext(), Menu.class);
                menu.putExtra("Carnet", carnet);
                startActivity(menu);

            }
        });
    }
}
