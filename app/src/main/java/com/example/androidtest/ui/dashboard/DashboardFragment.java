package com.example.androidtest.ui.dashboard;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidtest.Insertar;
import com.example.androidtest.Menu;
import com.example.androidtest.R;

import com.example.androidtest.databinding.FragmentDashboardBinding;

import java.io.ByteArrayOutputStream;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    ImageView campoImagen;
    Button agregarImagen, agregarAnuncio;
    TextView nombre,descripcion,precio;
    private byte[] imagen;
    String categoria,sede,carnet;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Menu activity=(Menu)getActivity();
        Bundle results=activity.getMyData();
        carnet=results.getString("Carnet");

        Spinner categorias=root.findViewById(R.id.categoria);
        Spinner sedes=root.findViewById(R.id.sede);

        campoImagen=(ImageView)root.findViewById(R.id.campoImagen);
        agregarImagen=(Button)root.findViewById(R.id.AgregarImagen);
        agregarAnuncio=(Button)root.findViewById(R.id.publicar);
        nombre=(TextView)root.findViewById(R.id.nombreProducto);
        descripcion=(TextView)root.findViewById(R.id.descripcionProducto);
        precio=(TextView)root.findViewById(R.id.precioProducto);

        String[] test={"Selecione la categoria...","Muebles","Ofimatica","Ropa"};
        String[] test2={"Selecione la sede...","San José","Cartago","Alajuela","San Carlos"};

        ArrayAdapter<String> adapter=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,test);
        ArrayAdapter<String> adapter2=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,test2);

        categorias.setAdapter(adapter);
        sedes.setAdapter(adapter2);

        categorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categoria=categorias.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sedes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sede=sedes.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        agregarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarImagen();
            }
        });

        agregarAnuncio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarAnuncio(getActivity());
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
                        try {
                            Bitmap bitmap = ((BitmapDrawable) campoImagen.getDrawable()).getBitmap();
                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                            imagen = stream.toByteArray();
                            stream.close();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            });

    public void cargarImagen(){
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        cameraLauncher.launch(intent);
    }

    public void agregarAnuncio(Context context){
        Insertar in=new Insertar();
        int resultado=0;
        try{
            resultado=in.insertarAnuncio(carnet,
                    "1",
                    nombre.getText().toString(),
                    "1",
                    descripcion.getText().toString(),
                    precio.getText().toString(),
                    imagen);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(resultado==0){
            Toast.makeText(context,"Anuncio registrado",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"No se pudo agregar el anuncio",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}