package com.otaku.clones.weatherapp.data.repository

import com.otaku.clones.weatherapp.data.mappers.toWeatherInfo
import com.otaku.clones.weatherapp.data.remote.WeatherAPI
import com.otaku.clones.weatherapp.domain.repository.WeatherRepository
import com.otaku.clones.weatherapp.domain.utils.Resource
import com.otaku.clones.weatherapp.domain.weather.models.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherAPI,
) : WeatherRepository {

    override suspend fun getWeatherInfo(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            val result = api.getWeatherData(lat, long)
            Resource.Success(
                data = result.toWeatherInfo()
            )
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            Resource.Error(message = "Error while fetching data from the API")
        }
    }
}