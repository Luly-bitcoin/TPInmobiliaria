package com.luu.tpinmobiliaria.ui.contratos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.luu.tpinmobiliaria.databinding.FragmentContratosBinding;

public class ContratosFragment extends Fragment {

    private FragmentContratosBinding binding;
    private ContratosViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentContratosBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(ContratosViewModel.class);

        binding.rvContratos.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel.getMListaInmuebles().observe(getViewLifecycleOwner(), inmuebles -> {
            ContratosAdapter adapter = new ContratosAdapter(inmuebles);
            binding.rvContratos.setAdapter(adapter);
        });

        viewModel.cargarInmueblesConContrato();

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}