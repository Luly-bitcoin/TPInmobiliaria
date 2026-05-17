package com.luu.tpinmobiliaria.ui.inmuebles;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.luu.tpinmobiliaria.ui.inmuebles.Inmueble;
import java.util.ArrayList;
import java.util.List;

public class InmueblesViewModel extends AndroidViewModel {

    private MutableLiveData<List<Inmueble>> mInmuebles;

    public InmueblesViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Inmueble>> getInmuebles() {
        if (mInmuebles == null) {
            mInmuebles = new MutableLiveData<>();
        }
        return mInmuebles;
    }

    public void cargarInmuebles() {
        List<Inmueble> lista = new ArrayList<>();

        Inmueble i1 = new Inmueble();
        i1.setIdInmueble(1);
        i1.setDireccion("Av. Mitre 1234");
        i1.setPrecio(150000);
        i1.setEstado(true);
        i1.setAvatar("");

        Inmueble i2 = new Inmueble();
        i2.setIdInmueble(2);
        i2.setDireccion("Calle Lavalle 567");
        i2.setPrecio(220000);
        i2.setEstado(false);
        i2.setAvatar("");

        Inmueble i3 = new Inmueble();
        i3.setIdInmueble(3);
        i3.setDireccion("Pedernera 890");
        i3.setPrecio(180000);
        i3.setEstado(true);
        i3.setAvatar("");

        Inmueble i4 = new Inmueble();
        i4.setIdInmueble(4);
        i4.setDireccion("Marconi 112");
        i4.setPrecio(250000);
        i4.setEstado(true);
        i4.setAvatar("");

        lista.add(i1);
        lista.add(i2);
        lista.add(i3);
        lista.add(i4);

        mInmuebles.setValue(lista);
    }
}