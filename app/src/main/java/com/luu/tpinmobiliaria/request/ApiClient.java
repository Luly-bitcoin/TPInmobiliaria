package com.luu.tpinmobiliaria.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.luu.tpinmobiliaria.models.Propietario;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public class ApiClient {

    public final static String BASE_URL =
            "https://capacitacion.alwaysdata.net/";

    public static MiServicioInmobiliaria getServicio(){

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(MiServicioInmobiliaria.class);
    }

    public interface MiServicioInmobiliaria{

        @FormUrlEncoded
        @POST("api/Propietarios/login")
        Call<String> loginForm(
                @Field("Usuario") String usuario,
                @Field("Clave") String clave
        );

        @GET("api/Propietarios")
        Call<Propietario> getPropietario(
                @Header("Authorization") String token
        );

        //@GET("api/Inmuebles")
        //Call<Inmuebles> getInmuebles(@Header("Authorization") String token);

        @PUT("api/Propietarios/actualizar")
        Call<Propietario> actualizarPerfil(
                @Header("Authorization") String token,
                @Body Propietario actualizado
        );
        @FormUrlEncoded
        @PUT("api/Propietarios/changePassword")
        Call<Void> cambiarPassword(
                @Header("Authorization") String token,
                @Field("currentPassword") String currentPassword,
                @Field("newPassword") String newPassword
        );

    }

    public static void recuperarToken(Context context, String token){

        SharedPreferences sp =
                context.getSharedPreferences("token.xml",
                        Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sp.edit();

        editor.putString("token", token);

        editor.apply();
    }

    public static String usarToken(Context context){

        SharedPreferences sp =
                context.getSharedPreferences("token.xml",
                        Context.MODE_PRIVATE);

        return sp.getString("token", null);
    }

    public static String obtenerToken(Context context){

        SharedPreferences sp =
                context.getSharedPreferences("token.xml",
                        Context.MODE_PRIVATE);

        return sp.getString("token", "");
    }
}