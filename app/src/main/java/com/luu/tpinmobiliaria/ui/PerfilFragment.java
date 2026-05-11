package com.luu.tpinmobiliaria.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.luu.tpinmobiliaria.R;
import com.luu.tpinmobiliaria.models.Propietario;
import com.luu.tpinmobiliaria.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilFragment extends Fragment {

    private boolean editando = false;

    private EditText etId, etDni,
            etNombre, etApellido,
            etEmail, etTelefono,
            etClave;

    private Button btnEditar;

    public PerfilFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_perfil,
                container, false);

        etId = root.findViewById(R.id.etId);

        etDni = root.findViewById(R.id.etDni);

        etNombre = root.findViewById(R.id.etNombre);

        etApellido = root.findViewById(R.id.etApellido);

        etEmail = root.findViewById(R.id.etEmail);

        etTelefono = root.findViewById(R.id.etTelefono);

        etClave = root.findViewById(R.id.etClave);

        btnEditar = root.findViewById(R.id.btnEditar);

        cargarPerfil();

        desactivarEdicion();

        btnEditar.setOnClickListener(v -> {

            if(!editando){

                activarEdicion();

                btnEditar.setText("GUARDAR");

                editando = true;

            }else{

                guardarCambios();

                desactivarEdicion();

                btnEditar.setText("EDITAR");

                editando = false;
            }
        });

        return root;
    }

    private void cargarPerfil(){

        String token = "Bearer " +
                ApiClient.obtenerToken(getContext());

        ApiClient.MiServicioInmobiliaria api =
                ApiClient.getServicio();

        Call<Propietario> llamada =
                api.obtenerPerfil(token);

        llamada.enqueue(new Callback<Propietario>() {

            @Override
            public void onResponse(Call<Propietario> call,
                                   Response<Propietario> response) {

                if(response.isSuccessful()){

                    Propietario p = response.body();

                    etId.setText(String.valueOf(p.getId()));

                    etDni.setText(String.valueOf(p.getDni()));

                    etNombre.setText(p.getNombre());

                    etApellido.setText(p.getApellido());

                    etEmail.setText(p.getEmail());

                    etTelefono.setText(p.getTelefono());

                    etClave.setText(p.getClave());

                }else{

                    Toast.makeText(getContext(),
                            "Error al cargar perfil",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Propietario> call,
                                  Throwable t) {

                Toast.makeText(getContext(),
                        t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void activarEdicion(){

        etNombre.setEnabled(true);
        etApellido.setEnabled(true);
        etEmail.setEnabled(true);
        etTelefono.setEnabled(true);
    }

    private void desactivarEdicion(){

        etNombre.setEnabled(false);
        etApellido.setEnabled(false);
        etEmail.setEnabled(false);
        etTelefono.setEnabled(false);

        etDni.setEnabled(false);
        etId.setEnabled(false);
        etClave.setEnabled(false);
    }

    private void guardarCambios(){

        Toast.makeText(getContext(),
                "Cambios guardados",
                Toast.LENGTH_SHORT).show();
    }
}