package com.otaku.clones.weatherapp.domain.repository

import com.otaku.clones.weatherapp.domain.utils.Resource
import com.otaku.clones.weatherapp.domain.weather.models.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherInfo(lat: Double, long: Double): Resource<WeatherInfo>
}