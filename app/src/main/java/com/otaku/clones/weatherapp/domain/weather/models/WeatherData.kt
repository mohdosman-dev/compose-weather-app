package com.otaku.clones.weatherapp.domain.weather.models

import java.time.LocalDateTime

data class WeatherData(
    val time: LocalDateTime,
    val pressure: Double,
    val temperatureCelsius: Double,
    val windSpeed: Double,
    val humidity: Double,
    val weatherType: WeatherType,
)
