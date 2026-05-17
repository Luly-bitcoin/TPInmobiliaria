package com.luu.tpinmobiliaria.ui.inmuebles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.luu.tpinmobiliaria.R;

public class DetalleInmuebleFragment extends Fragment {

    private TextView tvCodigo, tvDireccion, tvPrecio;
    private CheckBox cbDisponible;
    private ImageView ivFoto;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detalle_inmueble, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvCodigo = view.findViewById(R.id.tvCodigo);
        tvDireccion = view.findViewById(R.id.tvDireccionDetalle);
        tvPrecio = view.findViewById(R.id.tvPrecioDetalle);
        cbDisponible = view.findViewById(R.id.cbDisponible);
        ivFoto = view.findViewById(R.id.ivFotoDetalle);

        Bundle bundle = getArguments();
        if (bundle != null) {
            int id = bundle.getInt("id");
            String dir = bundle.getString("direccion");
            double precio = bundle.getDouble("precio");
            boolean est = bundle.getBoolean("estado");
            String avatar = bundle.getString("avatar");

            tvCodigo.setText(String.valueOf(id));
            tvDireccion.setText(dir);
            tvPrecio.setText("$" + precio);
            cbDisponible.setChecked(est);

            if (avatar != null && !avatar.isEmpty()) {
                Glide.with(requireContext())
                        .load(avatar)
                        .into(ivFoto);
            } else {
                ivFoto.setImageResource(android.R.drawable.ic_menu_gallery);
            }
        }
    }
}