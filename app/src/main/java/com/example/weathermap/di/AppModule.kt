package com.example.weathermap.di

import com.example.weathermap.repository.WeatherRepository
import com.example.weathermap.network.WeatherServiceApi
import com.example.weathermap.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesWeatherServiceApi(retrofit: Retrofit): WeatherServiceApi{
        return retrofit.create(WeatherServiceApi::class.java)
    }

    @Singleton
    @Provides
    fun provideWeatherRepository(weatherServiceApi: WeatherServiceApi): WeatherRepository {
        return WeatherRepository(weatherServiceApi)
    }
}

