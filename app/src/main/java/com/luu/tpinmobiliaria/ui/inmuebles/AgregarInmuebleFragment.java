package com.luu.tpinmobiliaria.ui.inmuebles;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.gson.Gson;
import com.luu.tpinmobiliaria.R;
import com.luu.tpinmobiliaria.request.ApiClient;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgregarInmuebleFragment extends Fragment {

    private EditText etDireccion, etAmbientes, etPrecio, etUso, etTipo;
    private CheckBox cbDisponible;
    private ImageView ivFotoAgregar;
    private Button btnGuardar;
    private String rutaImagen = "";

    private ActivityResultLauncher<Intent> launcher;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_agregar_inmueble, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etDireccion = view.findViewById(R.id.etDireccion);
        etAmbientes = view.findViewById(R.id.etAmbientes);
        etPrecio = view.findViewById(R.id.etPrecio);
        etUso = view.findViewById(R.id.etUso);
        etTipo = view.findViewById(R.id.etTipo);
        cbDisponible = view.findViewById(R.id.cbDisponibleForm);
        ivFotoAgregar = view.findViewById(R.id.ivFotoAgregar);
        btnGuardar = view.findViewById(R.id.btnGuardarInmueble);

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        Uri uri = result.getData().getData();
                        ivFotoAgregar.setImageURI(uri);
                        rutaImagen = getPath(uri);
                    }
                }
        );

        ivFotoAgregar.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            launcher.launch(intent);
        });

        btnGuardar.setOnClickListener(v -> guardarInmueble(view));
    }

    private void guardarInmueble(View view) {
        String direccion = etDireccion.getText().toString();
        String ambientesStr = etAmbientes.getText().toString();
        String precioStr = etPrecio.getText().toString();
        String uso = etUso.getText().toString();
        String tipo = etTipo.getText().toString();
        boolean disponible = cbDisponible.isChecked();

        if (direccion.isEmpty() || ambientesStr.isEmpty() || precioStr.isEmpty() || uso.isEmpty() || tipo.isEmpty() || rutaImagen == null || rutaImagen.isEmpty()) {
            Toast.makeText(requireContext(), "Complete todos los campos y seleccione una foto", Toast.LENGTH_SHORT).show();
            return;
        }

        Inmueble inmueble = new Inmueble();
        inmueble.setDireccion(direccion);
        inmueble.setAmbientes(Integer.parseInt(ambientesStr));
        inmueble.setPrecio(Double.parseDouble(precioStr));
        inmueble.setUso(uso);
        inmueble.setTipo(tipo);
        inmueble.setEstado(disponible);

        File file = new File(rutaImagen);
        RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part imagenPart = MultipartBody.Part.createFormData("imagen", file.getName(), fileBody);

        Gson gson = new Gson();
        String json = gson.toJson(inmueble);
        RequestBody inmueblePart = RequestBody.create(MediaType.parse("application/json"), json);

        Context context = requireContext();
        String token = ApiClient.obtenerToken(context);

        ApiClient.MiServicioInmobiliaria api = ApiClient.getServicio();
        Call<Inmueble> llamada = api.cargarInmueble(token, imagenPart, inmueblePart);

        llamada.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Inmueble guardado con éxito", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(view).popBackStack();
                } else {
                    Toast.makeText(context, "Error al guardar el inmueble", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {
                Toast.makeText(context, "Error de red", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getPath(Uri uri) {
        String result = null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = requireContext().getContentResolver().query(uri, proj, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                result = cursor.getString(column_index);
            }
            cursor.close();
        }
        return result;
    }
}