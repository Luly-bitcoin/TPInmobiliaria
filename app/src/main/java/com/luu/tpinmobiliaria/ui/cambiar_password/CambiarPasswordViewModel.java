package com.luu.tpinmobiliaria.ui.cambiar_password;

import android.app.Application;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class CambiarPasswordViewModel extends AndroidViewModel {

    public CambiarPasswordViewModel(@NonNull Application application) {
        super(application);
    }

    public void procesarCambioClave(String actual, String nueva, String repetir) {
        if (actual.isEmpty() || nueva.isEmpty() || repetir.isEmpty()) {
            Toast.makeText(getApplication(), "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!nueva.equals(repetir)) {
            Toast.makeText(getApplication(), "Las nuevas contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(getApplication(), "Contraseña actualizada correctamente (Simulado)", Toast.LENGTH_SHORT).show();
    }
}