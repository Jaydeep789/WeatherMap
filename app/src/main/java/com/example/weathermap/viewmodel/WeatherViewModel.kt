package com.example.weathermap.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathermap.model.Weather
import com.example.weathermap.repository.WeatherRepository
import com.example.weathermap.utils.DataState
import com.example.weathermap.utils.DataState.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
): ViewModel() {

    private val _weatherData: MutableLiveData<DataState<Weather>> = MutableLiveData()
    val weatherData: LiveData<DataState<Weather>> = _weatherData

    fun getWeatherByCity(city: String){
        viewModelScope.launch {
            _weatherData.value = Loading
            val response = weatherRepository.getWeatherForecast(city)
            _weatherData.value = response
        }
    }
}