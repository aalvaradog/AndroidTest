package com.example.androidtest.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidtest.Leer;
import com.example.androidtest.Menu;
import com.example.androidtest.R;
import com.example.androidtest.databinding.FragmentNotificationsBinding;
import com.example.androidtest.eliminarAnuncio;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    String carnet;
    TextView nombrePerfil;
    Button editarPerfil, verAnuncios;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Menu activity=(Menu)getActivity();
        Bundle results=activity.getMyData();
        carnet=results.getString("Carnet");

        nombrePerfil=(TextView) root.findViewById(R.id.nombrePerfil);
        verAnuncios=(Button)root.findViewById(R.id.anunciosPerfil);

        nombrePerfil.setText(mostrarNombre());

        verAnuncios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), eliminarAnuncio.class);
                intent.putExtra("Carnet",carnet);
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

    public String mostrarNombre(){
        Leer objeto=new Leer();
        return objeto.consultarNombre(carnet);
    }
}