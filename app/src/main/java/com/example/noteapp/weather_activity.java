package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.noteapp.Retrofit.ApiClient;
import com.example.noteapp.Retrofit.ApiInterface;
import com.example.noteapp.Retrofit.Example;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class weather_activity extends AppCompatActivity {
   EditText city_name;
   ImageView search;
   TextView temp,desciption,humidity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_activity);
        city_name=findViewById(R.id.edt_city);
        search=findViewById(R.id.iv_search);
        temp=findViewById(R.id.tv_temp);
        desciption=findViewById(R.id.tv_description);
        humidity=findViewById(R.id.tv_humidity);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 getWeatherData(city_name.getText().toString().trim());

            }
        });
    }
    private void getWeatherData(String name){
        ApiInterface api= ApiClient.getclient().create(ApiInterface.class);
        Call<Example>call=api.GetweatherData(name);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                temp.setText("Temp"+" "+response.body().getMain().getTemp()+" C");
                desciption.setText("Feels Like"+" "+response.body().getMain().getFeels_like());
                humidity.setText("Humidity"+" "+response.body().getMain().getHumidity());
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });

    }
}