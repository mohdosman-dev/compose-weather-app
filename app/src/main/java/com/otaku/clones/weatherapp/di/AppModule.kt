package com.otaku.clones.weatherapp.di

import android.app.Application
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.otaku.clones.weatherapp.data.remote.WeatherAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherAPI = Retrofit
        .Builder()
        .baseUrl("https://api.open-meteo.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(WeatherAPI::class.java)

    @Provides
    @Singleton
    fun provideFusedLocationClient(
        app: Application,
    ): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(app)
    }
}