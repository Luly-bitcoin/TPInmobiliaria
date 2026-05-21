package com.luu.tpinmobiliaria.ui.inmuebles;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.luu.tpinmobiliaria.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmueblesViewModel extends AndroidViewModel {

    private MutableLiveData<List<Inmueble>> mInmuebles;

    public InmueblesViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Inmueble>> getInmuebles() {
        if (mInmuebles == null) {
            mInmuebles = new MutableLiveData<>();
            cargarInmuebles();
        }
        return mInmuebles;
    }

    public void cargarInmuebles() {
        Context context = getApplication().getApplicationContext();
        String token = ApiClient.obtenerToken(context);

        ApiClient.MiServicioInmobiliaria api = ApiClient.getServicio();
        Call<List<Inmueble>> llamada = api.getInmuebles(token);

        llamada.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mInmuebles.setValue(response.body());
                } else {
                    Toast.makeText(context, "Error al obtener inmuebles", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                Toast.makeText(context, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}