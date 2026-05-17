package com.luu.tpinmobiliaria.ui.inmuebles;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.luu.tpinmobiliaria.R;
import com.luu.tpinmobiliaria.ui.inmuebles.Inmueble;
import java.util.List;

public class InmueblesAdapter extends RecyclerView.Adapter<InmueblesAdapter.ViewHolder> {

    private List<Inmueble> listaInmuebles;
    private Context context;

    public InmueblesAdapter(List<Inmueble> listaInmuebles, Context context) {
        this.listaInmuebles = listaInmuebles;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inmueble, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Inmueble inmueble = listaInmuebles.get(position);

        holder.tvDireccion.setText(inmueble.getDireccion());
        holder.tvPrecio.setText("$ " + inmueble.getPrecio());

        if (inmueble.getAvatar() != null && !inmueble.getAvatar().isEmpty()) {
            Glide.with(context)
                    .load(inmueble.getAvatar())
                    .into(holder.ivFoto);
        } else {
            holder.ivFoto.setImageResource(android.R.drawable.ic_menu_gallery);
        }

        holder.itemView.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt("id", inmueble.getIdInmueble());
            bundle.putString("direccion", inmueble.getDireccion());
            bundle.putDouble("precio", inmueble.getPrecio());
            bundle.putBoolean("estado", inmueble.isEstado());
            bundle.putString("avatar", inmueble.getAvatar());

            Navigation.findNavController(v).navigate(R.id.action_nav_inmuebles_to_nav_detalle_inmueble, bundle);
        });
    }

    @Override
    public int getItemCount() {
        return listaInmuebles.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivFoto;
        TextView tvDireccion, tvPrecio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFoto = itemView.findViewById(R.id.ivFotoInmueble);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
        }
    }
}