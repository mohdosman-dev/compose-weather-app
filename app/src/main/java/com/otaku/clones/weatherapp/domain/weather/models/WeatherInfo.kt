package com.otaku.clones.weatherapp.domain.weather.models

data class WeatherInfo(
    val weatherPerDay: Map<Int, List<WeatherData>>,
    val currentWeatherData: WeatherData?
)
