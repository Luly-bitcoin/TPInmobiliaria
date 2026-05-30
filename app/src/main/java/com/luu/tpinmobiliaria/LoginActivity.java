package com.luu.tpinmobiliaria;

import static com.luu.tpinmobiliaria.R.layout.activity_login;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.luu.tpinmobiliaria.request.ApiClient;

public class LoginActivity extends AppCompatActivity implements SensorEventListener {

    private EditText etCorreo, etClave;
    private Button btnIngresar;
    private TextView tvOlvidePassword;

    private SensorManager sensorManager;
    private Sensor acelerometro;
    private float aceleracionUltima;
    private float aceleracionActual;
    private float agite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_login);

        etCorreo = findViewById(R.id.etEmail);
        etClave = findViewById(R.id.etPassword);
        btnIngresar = findViewById(R.id.btnLogin);
        tvOlvidePassword = findViewById(R.id.tvOlvidePassword);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }

        aceleracionUltima = SensorManager.GRAVITY_EARTH;
        aceleracionActual = SensorManager.GRAVITY_EARTH;
        agite = 0.00f;

        btnIngresar.setOnClickListener(v -> login());
        tvOlvidePassword.setOnClickListener(v -> olvideContrasena());
    }

    private void login(){

        String usuario = etCorreo.getText().toString().trim();
        String clave = etClave.getText().toString().trim();

        if (usuario.isEmpty() || clave.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Complete los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        ApiClient.getServicio()
                .loginForm(usuario, clave)
                .enqueue(new retrofit2.Callback<String>() {

                    @Override
                    public void onResponse(retrofit2.Call<String> call,
                                           retrofit2.Response<String> response) {

                        if (response.isSuccessful() && response.body() != null) {

                            String token = response.body();

                            ApiClient.recuperarToken(LoginActivity.this, token);

                            Toast.makeText(LoginActivity.this,
                                    "Login exitoso",
                                    Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();

                        } else {
                            Toast.makeText(LoginActivity.this,
                                    "Usuario o clave incorrectos",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(retrofit2.Call<String> call, Throwable t) {
                        Toast.makeText(LoginActivity.this,
                                "Error de conexión: " + t.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void olvideContrasena() {
        String correo = etCorreo.getText().toString();

        if (correo.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Por favor, ingrese su correo electrónico para restablecer la contraseña", Toast.LENGTH_LONG).show();
            return;
        }

        Toast.makeText(LoginActivity.this, "Se ha enviado un correo de recuperación a: " + correo, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        aceleracionUltima = aceleracionActual;
        aceleracionActual = (float) Math.sqrt((double) (x * x + y * y + z * z));
        float delta = aceleracionActual - aceleracionUltima;
        agite = agite * 0.9f + delta;

        if (agite > 12) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:2657000000"));
            startActivity(intent);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sensorManager != null && acelerometro != null) {
            sensorManager.registerListener(this, acelerometro, SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
    }
}