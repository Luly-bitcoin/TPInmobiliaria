package com.luu.tpinmobiliaria.ui;

import android.os.Bundle;
import android.util.Log;
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

    public PerfilFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_perfil, container, false);

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

            if (!editando) {

                activarEdicion();
                btnEditar.setText("GUARDAR");
                editando = true;

            } else {

                guardarCambios();
            }
        });

        return root;
    }

    private void guardarCambios() {

        String token = "Bearer " +
                ApiClient.obtenerToken(getContext());

        ApiClient.MiServicioInmobiliaria api =
                ApiClient.getServicio();

        Propietario actualizado = new Propietario();
        actualizado.setNombre(etNombre.getText().toString());
        actualizado.setApellido(etApellido.getText().toString());
        actualizado.setEmail(etEmail.getText().toString());
        actualizado.setTelefono(etTelefono.getText().toString());

        Call<Void> llamada = api.actualizarPerfil(token, actualizado);

        llamada.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.isSuccessful()) {

                    Toast.makeText(getContext(),
                            "Perfil actualizado",
                            Toast.LENGTH_SHORT).show();

                    editando = false;
                    btnEditar.setText("EDITAR");
                    desactivarEdicion();
                    cargarPerfil();

                } else {
                    Toast.makeText(getContext(),
                            "Error al actualizar",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                Toast.makeText(getContext(),
                        "Error: " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void activarEdicion() {

        etNombre.setEnabled(true);
        etApellido.setEnabled(true);
        etEmail.setEnabled(true);
        etTelefono.setEnabled(true);

        etNombre.requestFocus();
    }

    private void desactivarEdicion() {

        etNombre.setEnabled(false);
        etApellido.setEnabled(false);
        etEmail.setEnabled(false);
        etTelefono.setEnabled(false);

        etId.setEnabled(false);
        etDni.setEnabled(false);
        etClave.setEnabled(false);
    }

    private void cargarPerfil() {

        String token = "Bearer " + ApiClient.obtenerToken(getContext());

        ApiClient.MiServicioInmobiliaria api =
                ApiClient.getServicio();

        Call<Propietario> call = api.getPropietario(token);

        call.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {

                if (response.isSuccessful()) {

                    Propietario p = response.body();

                    if (p != null) {
                        etNombre.setText(p.getNombre());
                        etApellido.setText(p.getApellido());
                        etEmail.setText(p.getEmail());
                        etTelefono.setText(p.getTelefono());
                    } else {
                        Toast.makeText(getContext(), "Body vacío", Toast.LENGTH_LONG).show();
                    }

                } else {
                    try {
                        Log.e("API_ERROR", response.errorBody().string());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                Log.d("API_CODE", String.valueOf(response.code()));
                Log.d("API_BODY", String.valueOf(response.body()));
                Log.e("API_ERROR", String.valueOf(response.errorBody()));
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}