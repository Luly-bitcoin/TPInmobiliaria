package com.luu.tpinmobiliaria.ui.logout;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.luu.tpinmobiliaria.R;

public class LogoutFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_logout, container, false);

        new AlertDialog.Builder(getContext())
                .setTitle("Cierre de sesión")
                .setMessage("¿Está seguro de que desea cerrar la sesión?")
                .setPositiveButton("Aceptar", (dialog, which) -> {
                    getActivity().finish();
                })
                .setNegativeButton("Cancelar", (dialog, id) -> {
                    // Si cancela, no hace nada
                })
                .show();

        return root;
    }
}