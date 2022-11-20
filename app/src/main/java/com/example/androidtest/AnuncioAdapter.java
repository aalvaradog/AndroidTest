package com.example.androidtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class AnuncioAdapter extends ArrayAdapter<Anuncio> {

    private Context context;
    private int layoutId;
    private ArrayList<Anuncio> arrayAnuncios;

    public AnuncioAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Anuncio> objects) {
        super(context, resource, objects);

        this.context=context;
        this.layoutId=resource;
        this.arrayAnuncios=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(layoutId,parent,false);
        TextView nombre=(TextView) view.findViewById(R.id.nombreAnuncio);
        TextView precio=(TextView) view.findViewById(R.id.precioAnuncio);
        TextView sede=(TextView) view.findViewById(R.id.sedeAnuncio);
        ImageView imagen=(ImageView) view.findViewById(R.id.imagen);

        Anuncio anuncio=arrayAnuncios.get(position);

        nombre.setText(anuncio.getTitulo());
        precio.setText(anuncio.getPrecio());
        sede.setText(anuncio.getSede());
        imagen.setImageBitmap(anuncio.getImagenUrl());

        return view;
    }
}
