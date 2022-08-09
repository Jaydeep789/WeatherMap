package com.example.weathermap.network

import com.example.weathermap.model.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherServiceApi {

    @GET("data/2.5/forecast")
    suspend fun getWeatherForecast(
        @Query("q") city: String,
        @Query("appid") key: String
    ) : Response<Weather>
}

