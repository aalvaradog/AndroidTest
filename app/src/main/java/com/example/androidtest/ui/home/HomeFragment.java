package com.example.androidtest.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.androidtest.R;
import com.example.androidtest.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements SearchView.OnQueryTextListener {

    private FragmentHomeBinding binding;
    SearchView buscar;
    AnuncioAdapter anuncioAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        buscar=(SearchView)root.findViewById(R.id.buscar);
        ListView lv=root.findViewById(R.id.lvAnuncios);

        Leer leerAnuncios=new Leer();
        ArrayList<Anuncio> anuncios=new ArrayList<>();
        anuncios=leerAnuncios.cargarMenu();
        /*anuncios.add(new Anuncio("Laptop Hp","Se vende Laptop Hp en perfecto estado taka taka taka tak","San José","200,000","imageexample"));
        anuncios.add(new Anuncio("Gabacha de laboratorio","Se vende Laptop Hp en perfecto estado taka taka taka tak","San José","20,000","imageexample"));
        anuncios.add(new Anuncio("Escritorio pequeño","Se vende Laptop Hp en perfecto estado taka taka taka tak","San José","50,000","imageexample"));
        anuncios.add(new Anuncio("Calculadora cientifica","Se vende Laptop Hp en perfecto estado taka taka taka tak","San José","10,000","imageexample"));*/
        anuncioAdapter=new AnuncioAdapter(getActivity(), R.layout.item_anuncio, anuncios);

        lv.setAdapter(anuncioAdapter);

        buscar.setOnQueryTextListener(this);
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