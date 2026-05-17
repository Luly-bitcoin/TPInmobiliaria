package com.luu.tpinmobiliaria.ui.inmuebles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import com.luu.tpinmobiliaria.databinding.FragmentInmueblesBinding;

public class InmueblesFragment extends Fragment {
    private FragmentInmueblesBinding binding;
    private InmueblesViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentInmueblesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(InmueblesViewModel.class);

        binding.rvInmuebles.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        viewModel.getInmuebles().observe(getViewLifecycleOwner(), lista -> {
            InmueblesAdapter adapter = new InmueblesAdapter(lista, requireContext());
            binding.rvInmuebles.setAdapter(adapter);
        });

        viewModel.cargarInmuebles();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}