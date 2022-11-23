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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AnuncioAdapter extends ArrayAdapter<Anuncio> {

    private Context context;
    private int layoutId;
    private ArrayList<Anuncio> arrayAnuncios;
    private ArrayList<Anuncio> arrayOriginal;

    public AnuncioAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Anuncio> objects) {
        super(context, resource, objects);

        this.context=context;
        this.layoutId=resource;
        this.arrayAnuncios=objects;

        this.arrayOriginal=new ArrayList<>();
        arrayOriginal.addAll(arrayAnuncios);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(layoutId,parent,false);
        TextView nombre=(TextView) view.findViewById(R.id.nombreAnuncioEliminar);
        TextView precio=(TextView) view.findViewById(R.id.precioAnuncioEliminar);
        TextView sede=(TextView) view.findViewById(R.id.sedeAnuncioEliminar);
        ImageView imagen=(ImageView) view.findViewById(R.id.imagenEliminar);

        Anuncio anuncio=arrayAnuncios.get(position);

        Leer leer=new Leer();

        nombre.setText(anuncio.getTitulo());
        precio.setText(anuncio.getPrecio());
        sede.setText(leer.consultarSede(anuncio.getSede()));
        imagen.setImageBitmap(anuncio.convertirABitmap(anuncio.getImagenUrl()));

        return view;
    }

    public void filtrado(final String txtBuscar) {
        int longitud = txtBuscar.length();
        if (longitud == 0) {
            arrayAnuncios.clear();
            arrayAnuncios.addAll(arrayOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Anuncio> collecion = arrayAnuncios.stream()
                        .filter(i -> i.getTitulo().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                arrayAnuncios.clear();
                arrayAnuncios.addAll(collecion);
            } else {
                for (Anuncio c : arrayOriginal) {
                    if (c.getTitulo().toLowerCase().contains(txtBuscar.toLowerCase())) {
                        arrayAnuncios.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }
}
