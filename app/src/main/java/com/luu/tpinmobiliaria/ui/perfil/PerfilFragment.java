package com.luu.tpinmobiliaria.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.luu.tpinmobiliaria.models.Propietario;
import com.luu.tpinmobiliaria.databinding.FragmentPerfilBinding;

public class PerfilFragment extends Fragment {

    private FragmentPerfilBinding binding;
    private PerfilViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(PerfilViewModel.class);

        viewModel.getPropietario().observe(getViewLifecycleOwner(), new androidx.lifecycle.Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                binding.etId.setText(String.valueOf(propietario.getId()));
                binding.etDni.setText(propietario.getDni());
                binding.etNombre.setText(propietario.getNombre());
                binding.etApellido.setText(propietario.getApellido());
                binding.etEmail.setText(propietario.getEmail());
                binding.etTelefono.setText(propietario.getTelefono());
                binding.etClave.setText(propietario.getClave());
            }
        });

        viewModel.getEditable().observe(getViewLifecycleOwner(), new androidx.lifecycle.Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                binding.etNombre.setEnabled(aBoolean);
                binding.etApellido.setEnabled(aBoolean);
                binding.etEmail.setEnabled(aBoolean);
                binding.etTelefono.setEnabled(aBoolean);

                if (aBoolean) {
                    binding.btnEditar.setText("GUARDAR");
                } else {
                    binding.btnEditar.setText("EDITAR");
                }
            }
        });

        viewModel.obtenerDatos();

        binding.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoBoton = binding.btnEditar.getText().toString();
                if (textoBoton.equals("EDITAR")) {
                    viewModel.cambiarEstadoEditable(true);
                } else {
                    Propietario p = new Propietario();
                    p.setId(Integer.parseInt(binding.etId.getText().toString()));
                    p.setDni(binding.etDni.getText().toString());
                    p.setNombre(binding.etNombre.getText().toString());
                    p.setApellido(binding.etApellido.getText().toString());
                    p.setEmail(binding.etEmail.getText().toString());
                    p.setTelefono(binding.etTelefono.getText().toString());
                    p.setClave(binding.etClave.getText().toString());

                    viewModel.actualizarDatos(p);
                }
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}