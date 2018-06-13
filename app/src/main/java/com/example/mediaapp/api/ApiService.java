/*
*
* Klasa ApiService definiuje połączenie z API
*
* */

package com.example.mediaapp.api;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    public static ApiClient getService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.theaudiodb.com/api/v1/json/1/")  //link do serwisu skąd pobrane będą dane
                .addConverterFactory(GsonConverterFactory.create())   //konwersja pobranych z api danych do GSON
                .build();
        return  retrofit.create(ApiClient.class);

    }

}
