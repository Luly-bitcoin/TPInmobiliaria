package com.luu.tpinmobiliaria;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.luu.tpinmobiliaria.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText etCorreo, etClave;
    private Button btnIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etCorreo = findViewById(R.id.etEmail);
        etClave = findViewById(R.id.etPassword);
        btnIngresar = findViewById(R.id.btnLogin);

        btnIngresar.setOnClickListener(v -> login());
    }

    private void login(){

        String usuario = etCorreo.getText().toString();
        String clave = etClave.getText().toString();

        if (usuario.isEmpty() || clave.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Complete los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        ApiClient.MiServicioInmobiliaria api = ApiClient.getServicio();

        Call<String> llamada = api.loginForm(usuario, clave);

        llamada.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if(response.isSuccessful()){

                    String token = response.body();
                    ApiClient.recuperarToken(LoginActivity.this, token);

                    Toast.makeText(LoginActivity.this,
                            "Login Correcto",
                            Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(LoginActivity.this,
                            MainActivity.class);

                    startActivity(intent);

                    finish();

                }else{

                    Toast.makeText(LoginActivity.this,
                            "Usuario o contraseña incorrectos",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Toast.makeText(LoginActivity.this,
                        "Error: " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}