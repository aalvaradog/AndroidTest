package com.example.androidtest.ui.settings;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.androidtest.R;
import com.example.androidtest.databinding.FragmentHomeBinding;
import com.example.androidtest.databinding.FragmentSettingsBinding;
import com.example.androidtest.ui.home.HomeViewModel;

public class SettingsFragment extends Fragment {

    //private SettingsViewModel mViewModel;
    private FragmentSettingsBinding binding;
    Button cerrarSesion;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        SettingsViewModel settingsViewModel =
                new ViewModelProvider(this).get(SettingsViewModel.class);

        binding = FragmentSettingsBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        cerrarSesion=(Button)root.findViewById(R.id.cerrarSesion);

        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        return root;
    }

}