package com.example.noteapp.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public  interface ApiInterface {

    @GET("weather?appid=92756c24107bc39dd0a7541f66ba55c5&units=metric")
    Call<Example>GetweatherData(@Query("q") String name);

}
