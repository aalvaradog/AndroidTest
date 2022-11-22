package com.example.androidtest;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EliminarAdapter extends ArrayAdapter<Anuncio> {

    private Context context;
    private int layoutId;
    private ArrayList<Anuncio> arrayAnuncios;
    private ArrayList<Anuncio> arrayOriginal;
    private String carnet;
    public String id;

    public EliminarAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Anuncio> objects, String carnet) {
        super(context, resource, objects);

        this.context=context;
        this.layoutId=resource;
        this.arrayAnuncios=objects;
        this.carnet=carnet;

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
        ImageButton eliminar=(ImageButton) view.findViewById(R.id.eliminar);

        Anuncio anuncio=arrayAnuncios.get(position);

        nombre.setText(anuncio.getTitulo());
        precio.setText(anuncio.getPrecio());
        sede.setText(anuncio.getSede());
        imagen.setImageBitmap(anuncio.convertirABitmap(anuncio.getImagenUrl()));
        id=anuncio.getId();

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog=new AlertDialog.Builder(context);
                dialog.setMessage("¿Seguro que desea eliminar el anuncio?");
                dialog.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Borrar borrar=new Borrar();
                        boolean resultado=borrar.borrarAnuncio(id);
                        if(resultado){
                            AlertDialog.Builder builder=new AlertDialog.Builder(context);
                            builder.setMessage("Anuncio Eliminado");
                            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            AlertDialog d=builder.create();
                            d.show();
                        }else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setMessage("No se pudo eliminar el anuncio");
                            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            AlertDialog d = builder.create();
                            d.show();
                        }
                    }
                });
                dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog d=dialog.create();
                d.show();
                notifyDataSetChanged();
            }
        });

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
