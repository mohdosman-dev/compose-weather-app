package com.otaku.clones.weatherapp.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otaku.clones.weatherapp.domain.location.LocationTracker
import com.otaku.clones.weatherapp.domain.repository.WeatherRepository
import com.otaku.clones.weatherapp.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker,
) : ViewModel() {

    var state by mutableStateOf(WeatherState())
        private set

    fun loadWeatherInfo() {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            locationTracker.getCurrentLocation()?.let { location ->
                repository.getWeatherInfo(
                    long = location.longitude,
                    lat = location.latitude,
                ).let {
                    when (it) {
                        is Resource.Success -> {
                            state = state.copy(
                                isLoading = false,
                                error = null,
                                weatherInfo = it.data
                            )
                        }
                        is Resource.Error -> {
                            state = state.copy(
                                isLoading = false,
                                error = it.message,
                                weatherInfo = null
                            )
                        }
                    }
                }
            } ?: kotlin.run {
                state = state.copy(
                    isLoading = false,
                    error = "Cannot fetch current location",
                    weatherInfo = null,
                )
            }
        }
    }
}