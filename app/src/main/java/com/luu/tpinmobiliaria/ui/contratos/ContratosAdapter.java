package com.luu.tpinmobiliaria.ui.contratos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.luu.tpinmobiliaria.R;
import com.luu.tpinmobiliaria.models.Inmueble;

import java.util.List;

public class ContratosAdapter extends RecyclerView.Adapter<ContratosAdapter.ViewHolder> {

    private List<Inmueble> inmuebles;

    public ContratosAdapter(List<Inmueble> inmuebles, OnItemClickListener listener) {
        this.inmuebles = inmuebles;
        this.listener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(Inmueble inmueble);
    }

    private OnItemClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contrato, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Inmueble inmueble = inmuebles.get(position);

        holder.tvDireccion.setText(inmueble.getDireccion());
        holder.tvTipo.setText(inmueble.getTipo());
        holder.tvPrecio.setText("$ " + inmueble.getPrecio());

        Glide.with(holder.itemView.getContext())
                .load(inmueble.getAvatar())
                .into(holder.imgAvatar);

        holder.itemView.setOnClickListener(v -> {
            listener.onItemClick(inmueble);
        });
    }

    @Override
    public int getItemCount() {
        return inmuebles.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgAvatar;
        TextView tvDireccion, tvTipo, tvPrecio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            tvTipo = itemView.findViewById(R.id.tvTipo);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
        }
    }
}