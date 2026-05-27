package com.luu.tpinmobiliaria.ui.contratos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.luu.tpinmobiliaria.R;
import com.luu.tpinmobiliaria.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratoDetalleFragment extends Fragment {

    private TextView tvInquilino, tvFechaInicio, tvFechaFin, tvMonto;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detalle_contrato, container, false);

        tvInquilino = view.findViewById(R.id.tvInquilino);
        tvFechaInicio = view.findViewById(R.id.tvFechaInicio);
        tvFechaFin = view.findViewById(R.id.tvFechaFin);
        tvMonto = view.findViewById(R.id.tvMonto);

        int idInmueble = getArguments().getInt("idInmueble");
        Toast.makeText(requireContext(),
                "ID inmueble: " + idInmueble,
                Toast.LENGTH_SHORT).show();

        String token = "Bearer " + ApiClient.obtenerToken(requireContext());

        ApiClient.getServicio().obtenerContrato(token, idInmueble)
                .enqueue(new Callback<Contrato>() {
                    @Override
                    public void onResponse(Call<Contrato> call, Response<Contrato> response) {

                        if(response.isSuccessful() && response.body() != null){

                            Contrato contrato = response.body();

                            tvInquilino.setText(
                                    contrato.getInquilino().getNombre()
                                            + " " +
                                            contrato.getInquilino().getApellido()
                            );

                            tvFechaInicio.setText(
                                    contrato.getFechaInicio()
                            );

                            tvFechaFin.setText(
                                    contrato.getFechaFinalizacion()
                            );

                            tvMonto.setText(
                                    "$" + String.format("%.0f", contrato.getMontoAlquiler())
                            );

                        }else{
                            Toast.makeText(requireContext(),
                                    "No se pudo obtener el contrato",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Contrato> call, Throwable t) {

                        Toast.makeText(requireContext(),
                                t.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });

        return view;
    }
}