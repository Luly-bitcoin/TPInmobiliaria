package com.luu.tpinmobiliaria.ui.contratos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.luu.tpinmobiliaria.R;
import com.luu.tpinmobiliaria.request.ApiClient;
import com.luu.tpinmobiliaria.ui.inmuebles.Inmueble;
import java.util.List;

public class ContratosAdapter extends RecyclerView.Adapter<ContratosAdapter.ViewHolder> {

    private List<Inmueble> listaInmuebles;

    public ContratosAdapter(List<Inmueble> listaInmuebles) {
        this.listaInmuebles = listaInmuebles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inmueble_alquilado, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Inmueble inmueble = listaInmuebles.get(position);

        holder.tvDireccion.setText("Dirección: " + inmueble.getDireccion());
        holder.tvTipo.setText("Tipo: " + inmueble.getTipo());
        holder.tvUso.setText("Uso: " + inmueble.getUso());

        String urlImagen = ApiClient.BASE_URL + inmueble.getImagen();
        Glide.with(holder.itemView.getContext())
                .load(urlImagen)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.ic_menu_gallery)
                .into(holder.ivFoto);

        holder.btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaInmuebles.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDireccion, tvTipo, tvUso;
        ImageView ivFoto;
        Button btnVer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDireccion = itemView.findViewById(R.id.tvInmuebleDireccion);
            tvTipo = itemView.findViewById(R.id.tvInmuebleTipo);
            tvUso = itemView.findViewById(R.id.tvInmuebleUso);
            ivFoto = itemView.findViewById(R.id.ivInmuebleFoto);
            btnVer = itemView.findViewById(R.id.btnVerContrato);
        }
    }
}