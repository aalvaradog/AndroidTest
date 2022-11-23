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
    String carnet, titulo, descripcion, sede, precio,idAnuncio;
    TextView tTitulo, tDescripcion, tSede, tPrecio;
    ImageView iImagen;
    byte[] imagen;
    Button reportar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        volver = (ImageButton) findViewById(R.id.volverDetalles);

        carnet = getIntent().getStringExtra("Carnet");
        carnet = getIntent().getStringExtra("Id");

        titulo = getIntent().getStringExtra("Titulo");
        descripcion = getIntent().getStringExtra("Descripcion");
        precio = getIntent().getStringExtra("Precio");
        sede = getIntent().getStringExtra("Sede");
        imagen = getIntent().getByteArrayExtra("Imagen");

        tTitulo = (TextView) findViewById(R.id.tituloDetalles);
        tDescripcion = (TextView) findViewById(R.id.descripcionDetalles);
        tPrecio = (TextView) findViewById(R.id.precioDetalles);
        tSede=(TextView)findViewById(R.id.sedeDetalles);
        iImagen = (ImageView) findViewById(R.id.imagenDetalles);
        reportar=(Button)findViewById(R.id.reportar);


        tTitulo.setText(titulo);
        tDescripcion.setText(descripcion);
        tPrecio.setText(precio);
        tSede.setText(sede);
        iImagen.setImageBitmap(BitmapFactory.decodeByteArray(imagen, 0, imagen.length));

        reportar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),ReporteActivity.class);
                intent.putExtra("Carnet",carnet);
                intent.putExtra("Id",idAnuncio);
                startActivity(intent);
            }
        });

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
