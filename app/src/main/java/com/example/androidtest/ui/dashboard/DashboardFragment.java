package com.example.androidtest.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.androidtest.R;

import com.example.androidtest.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Spinner categorias=root.findViewById(R.id.categoria);
        Spinner sedes=root.findViewById(R.id.sede);

        String[] test={"Selecione la categoria...","Muebles","Ofimatica","Ropa"};
        String[] test2={"Selecione la sede...","San José","Cartago","Alajuela","San Carlos"};

        ArrayAdapter<String> adapter=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,test);
        ArrayAdapter<String> adapter2=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,test2);

        categorias.setAdapter(adapter);
        sedes.setAdapter(adapter2);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}