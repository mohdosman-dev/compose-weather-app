package com.otaku.clones.weatherapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("v1/forecast?hourly=temperature_2m,relativehumidity_2m,pressure_msl,weathercode,windspeed_10m")
    suspend fun getWeatherData(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
    ): WeatherDto
}