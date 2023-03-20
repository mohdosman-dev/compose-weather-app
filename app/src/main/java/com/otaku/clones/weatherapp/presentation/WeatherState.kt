package com.otaku.clones.weatherapp.presentation

import com.otaku.clones.weatherapp.domain.weather.models.WeatherInfo

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
