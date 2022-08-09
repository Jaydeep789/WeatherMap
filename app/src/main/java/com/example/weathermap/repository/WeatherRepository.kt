package com.example.weathermap.repository

import com.example.weathermap.BuildConfig
import com.example.weathermap.model.Weather
import com.example.weathermap.network.WeatherServiceApi
import com.example.weathermap.utils.DataState
import com.example.weathermap.utils.DataState.Error
import com.example.weathermap.utils.DataState.Success
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherServiceApi: WeatherServiceApi
) {

    suspend fun getWeatherForecast(city: String): DataState<Weather>{
        return try {
            val response = weatherServiceApi.getWeatherForecast(city, BuildConfig.API_KEY)

            val result = response.body()
            if (response.isSuccessful && result != null){
                Success(result)
            } else {
                Error(Exception())
            }
        } catch (e: Exception) {
            Error(e)
        }
    }
}

