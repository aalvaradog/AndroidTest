package com.example.androidtest.ui.dashboard;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.androidtest.R;

import com.example.androidtest.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    ImageView campoImagen;
    Button agregarImagen;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Spinner categorias=root.findViewById(R.id.categoria);
        Spinner sedes=root.findViewById(R.id.sede);

        campoImagen=(ImageView)root.findViewById(R.id.campoImagen);
        agregarImagen=(Button)root.findViewById(R.id.AgregarImagen);

        String[] test={"Selecione la categoria...","Muebles","Ofimatica","Ropa"};
        String[] test2={"Selecione la sede...","San José","Cartago","Alajuela","San Carlos"};

        ArrayAdapter<String> adapter=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,test);
        ArrayAdapter<String> adapter2=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,test2);

        categorias.setAdapter(adapter);
        sedes.setAdapter(adapter2);

        agregarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarImagen();
                //cameraLauncher.launch(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
            }
        });

        return root;
    }

    ActivityResultLauncher<Intent> cameraLauncher= registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == RESULT_OK){
                        Uri data=result.getData().getData();
                        binding.campoImagen.setImageURI(data);
                        //Bundle extras=result.getData().getExtras();
                        //Bitmap imgBitmap=(Bitmap) extras.get("data");
                        //campoImagen.setImageBitmap(imgBitmap);
                    }
                }
            });

    public void cargarImagen(){
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        cameraLauncher.launch(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}