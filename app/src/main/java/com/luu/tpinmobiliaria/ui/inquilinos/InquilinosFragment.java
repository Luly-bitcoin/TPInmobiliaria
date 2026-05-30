package com.luu.tpinmobiliaria.ui.inquilinos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.luu.tpinmobiliaria.R;
import com.luu.tpinmobiliaria.databinding.FragmentInquilinosBinding;
import com.luu.tpinmobiliaria.ui.contratos.ContratosAdapter;

public class InquilinosFragment extends Fragment {

    private FragmentInquilinosBinding binding;
    private InquilinosViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentInquilinosBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(InquilinosViewModel.class);

        binding.rvInquilinos.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel.getMListaInmuebles().observe(getViewLifecycleOwner(), inmuebles -> {
            ContratosAdapter adapter = new ContratosAdapter(inmuebles, inmueble -> {
                Bundle bundle = new Bundle();
                bundle.putInt("idInmueble", inmueble.getIdInmueble());
                androidx.navigation.Navigation.findNavController(binding.getRoot())
                        .navigate(R.id.inquilinoDetalleFragment, bundle);
            });
            binding.rvInquilinos.setAdapter(adapter);
        });

        viewModel.cargarInmueblesAlquilados();

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}