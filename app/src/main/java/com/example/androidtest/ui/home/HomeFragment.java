package com.example.androidtest.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidtest.Anuncio;
import com.example.androidtest.AnuncioAdapter;
import com.example.androidtest.Insertar;
import com.example.androidtest.Leer;
import com.example.androidtest.Menu;
import com.example.androidtest.R;
import com.example.androidtest.databinding.FragmentHomeBinding;
import com.example.androidtest.detallesActivity;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements SearchView.OnQueryTextListener {

    private FragmentHomeBinding binding;
    SearchView buscar;
    AnuncioAdapter anuncioAdapter;
    ArrayList<Anuncio> anuncios=new ArrayList<>();
    Anuncio anuncio=new Anuncio();
    String carnet;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        buscar=(SearchView)root.findViewById(R.id.buscar);
        ListView lv=root.findViewById(R.id.lvAnuncios);

        Menu activity=(Menu)getActivity();
        Bundle results=activity.getMyData();
        carnet=results.getString("Carnet");

        Leer leerAnuncios=new Leer();
        anuncios=leerAnuncios.cargarMenu();
        anuncioAdapter=new AnuncioAdapter(getActivity(), R.layout.item_anuncio, anuncios);

        lv.setAdapter(anuncioAdapter);

        buscar.setOnQueryTextListener(this);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                anuncio= anuncios.get(position);
                Intent intent=new Intent(getActivity(), detallesActivity.class);
                //intent.putExtra("Carnet",carnet);
                intent.putExtra("Titulo",anuncio.getTitulo());
                intent.putExtra("Descripcion",anuncio.getDescripcion());
                intent.putExtra("Precio",anuncio.getPrecio());
                intent.putExtra("Sede",anuncio.getSede());
                intent.putExtra("Imagen",anuncio.getImagenUrl());
                startActivity(intent);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        anuncioAdapter.filtrado(newText);
        return false;
    }
}