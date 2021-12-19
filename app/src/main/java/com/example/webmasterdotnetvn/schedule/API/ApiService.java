package com.example.webmasterdotnetvn.schedule.API;


import com.example.webmasterdotnetvn.schedule.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    Gson GSON = new GsonBuilder().serializeNulls().create();
    ApiService apiService= new Retrofit.Builder()
            .baseUrl("https://banhangdacap207.000webhostapp.com/schedule/public/")
            .addConverterFactory(GsonConverterFactory.create(GSON))
            .build()
            .create(ApiService.class);

    @POST("api/login/{email}/{password}")
    Call<User> postLogin(@Path("email") String idNongSan,@Path("password") String password);


}
