package com.example.weathermap.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Weather(
    val list: List<WeatherData>
)

@Parcelize
data class WeatherData(
    val main: WeatherInfo,
    val weather: List<WeatherDetails>
) : Parcelable

@Parcelize
data class WeatherInfo(
    val temp: Double,
    val feels_like: Double
) : Parcelable

@Parcelize
data class WeatherDetails(
    val main: String,
    val description: String
) : Parcelable

