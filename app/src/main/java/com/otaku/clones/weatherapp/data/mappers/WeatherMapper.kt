package com.otaku.clones.weatherapp.data.mappers

import com.otaku.clones.weatherapp.data.remote.WeatherDataDto
import com.otaku.clones.weatherapp.data.remote.WeatherDto
import com.otaku.clones.weatherapp.domain.weather.models.WeatherData
import com.otaku.clones.weatherapp.domain.weather.models.WeatherInfo
import com.otaku.clones.weatherapp.domain.weather.models.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexedWeather(
    val index: Int,
    val data: WeatherData,
)

fun WeatherDataDto.toWeatherData(): Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, time ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val humidity = humiditties[index]
        val pressure = pressures[index]

        IndexedWeather(
            index = index,
            data = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                pressure = pressure,
                temperatureCelsius = temperature,
                windSpeed = windSpeed,
                humidity = humidity,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }.groupBy {
        it.index / 24
    }.mapValues { item ->
        item.value.map { data -> data.data }
    }
}

fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = weatherData.toWeatherData()
    val now = LocalDateTime.now()
    val currentWeather = weatherDataMap[0]?.find {
        val hour = if (now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }

    return WeatherInfo(
        weatherPerDay = weatherDataMap,
        currentWeatherData = currentWeather
    )
}