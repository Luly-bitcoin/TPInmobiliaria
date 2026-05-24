package com.luu.tpinmobiliaria.ui.contratos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.luu.tpinmobiliaria.R;
import com.luu.tpinmobiliaria.ui.inmuebles.Inmueble;

import java.util.List;

public class ContratosAdapter extends RecyclerView.Adapter<ContratosAdapter.ViewHolder> {

    private List<Inmueble> inmuebles;

    public ContratosAdapter(List<Inmueble> inmuebles) {
        this.inmuebles = inmuebles;
    }

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
    }

    @Override
    public int getItemCount() {
        return inmuebles.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvDireccion, tvTipo, tvPrecio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            tvTipo = itemView.findViewById(R.id.tvTipo);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
        }
    }
}