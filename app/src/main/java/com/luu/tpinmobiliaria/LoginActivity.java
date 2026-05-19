package com.luu.tpinmobiliaria;

import static com.luu.tpinmobiliaria.R.layout.activity_login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.luu.tpinmobiliaria.request.ApiClient;

public class LoginActivity extends AppCompatActivity {

    private EditText etCorreo, etClave;
    private Button btnIngresar;
    private TextView tvOlvidePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_login);

        etCorreo = findViewById(R.id.etEmail);
        etClave = findViewById(R.id.etPassword);
        btnIngresar = findViewById(R.id.btnLogin);
        tvOlvidePassword = findViewById(R.id.tvOlvidePassword);

        btnIngresar.setOnClickListener(v -> login());
        tvOlvidePassword.setOnClickListener(v -> olvideContrasena());
    }

    private void login(){

        String usuario = etCorreo.getText().toString();
        String clave = etClave.getText().toString();

        if (usuario.isEmpty() || clave.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Complete los campos", Toast.LENGTH_SHORT).show();
            return;
        }

    }

    private void olvideContrasena() {
        String correo = etCorreo.getText().toString();

        if (correo.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Por favor, ingrese su correo electrónico para restablecer la contraseña", Toast.LENGTH_LONG).show();
            return;
        }

        Toast.makeText(LoginActivity.this, "Se ha enviado un correo de recuperación a: " + correo, Toast.LENGTH_LONG).show();
    }
}