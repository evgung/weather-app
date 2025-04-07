package com.example.weatherapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.weather.api.ForecastType
import com.example.weatherapp.data.weather.model.raw.WeatherInfo
import com.example.weatherapp.data.weatherbycity.WeatherByCityRepository
import com.example.weatherapp.ui.CommonParameters
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

sealed interface WeatherUiState {
    data class Success(
        val currentWeather: WeatherInfo,
        val hourlyWeather: WeatherInfo,
        val dailyWeather: WeatherInfo
    ) : WeatherUiState
    data class Error(val message: String) : WeatherUiState
    data object Loading : WeatherUiState
}

class WeatherViewModel(
    private val repository: WeatherByCityRepository
) : ViewModel() {

    private val _weatherState = MutableLiveData<WeatherUiState>(WeatherUiState.Loading)
    val weatherState: LiveData<WeatherUiState> = _weatherState

    var currentCity: String = "Izhevsk"

    init {
        loadWeather()
    }

    fun loadWeather() {
        viewModelScope.launch {
            _weatherState.value = WeatherUiState.Loading
            try {
                val (current, hourly, daily) = awaitAll(
                    async { loadCurrentWeather() },
                    async { loadHourlyWeather() },
                    async { loadDailyWeather() }
                )
                _weatherState.value = WeatherUiState.Success(current, hourly, daily)
            } catch (e: Exception) {
                _weatherState.value = WeatherUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    private suspend fun loadCurrentWeather() =
        repository.getWeather(currentCity, 1, ForecastType.CURRENT, CommonParameters.currentParams)

    private suspend fun loadHourlyWeather() =
        repository.getWeather(currentCity, 1, ForecastType.HOURLY, CommonParameters.hourlyParams)

    private suspend fun loadDailyWeather() =
        repository.getWeather(currentCity, CommonParameters.daysCountForDailyWeather, ForecastType.DAILY, CommonParameters.dailyParams)

    companion object {
        fun create(owner: ViewModelStoreOwner, repository: WeatherByCityRepository)
        : WeatherViewModel {
            val factory = WeatherViewModelFactory(repository)
            return ViewModelProvider(owner, factory)[WeatherViewModel::class.java]
        }
    }
}

class WeatherViewModelFactory(
    private val repository: WeatherByCityRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            return WeatherViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}