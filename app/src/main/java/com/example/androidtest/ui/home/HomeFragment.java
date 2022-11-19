package com.example.androidtest.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidtest.Anuncio;
import com.example.androidtest.AnuncioAdapter;
import com.example.androidtest.R;
import com.example.androidtest.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ListView lv=root.findViewById(R.id.lvAnuncios);

        ArrayList<Anuncio> anuncios=new ArrayList<>();
        anuncios.add(new Anuncio("Laptop Hp","Se vende Laptop Hp en perfecto estado taka taka taka tak","San José","200,000","imageexample"));
        anuncios.add(new Anuncio("Gabacha de laboratorio","Se vende Laptop Hp en perfecto estado taka taka taka tak","San José","20,000","imageexample"));
        anuncios.add(new Anuncio("Escritorio pequeño","Se vende Laptop Hp en perfecto estado taka taka taka tak","San José","50,000","imageexample"));
        anuncios.add(new Anuncio("Calculadora cientifica","Se vende Laptop Hp en perfecto estado taka taka taka tak","San José","10,000","imageexample"));
        AnuncioAdapter anuncioAdapter=new AnuncioAdapter(getActivity(), R.layout.item_anuncio, anuncios);

        lv.setAdapter(anuncioAdapter);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}