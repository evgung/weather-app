package com.example.weatherapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.weather.model.processed.CurrentWeather
import com.example.weatherapp.data.weather.model.processed.DailyWeather
import com.example.weatherapp.data.weather.model.processed.HourlyWeather
import com.example.weatherapp.data.weatherbycity.IWeatherByCityRepository
import com.example.weatherapp.data.weatherbycity.WeatherByCityRepository
import com.example.weatherapp.ui.ApiParameters
import com.example.weatherapp.ui.UserPreferences
import com.example.weatherapp.ui.UserPreferencesManager
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

sealed interface WeatherUiState {
    data class Success(
        val currentWeather: CurrentWeather,
        val hourlyWeather: List<HourlyWeather>,
        val dailyWeather: List<DailyWeather>
    ) : WeatherUiState
    data class Error(val message: String) : WeatherUiState
    data object Loading : WeatherUiState
}

class WeatherViewModel(
    private val repository: IWeatherByCityRepository,
    preferencesManager: UserPreferencesManager
) : ViewModel() {

    private val _weatherState = MutableLiveData<WeatherUiState>(WeatherUiState.Loading)
    val weatherState: LiveData<WeatherUiState> = _weatherState

    init {
        preferencesManager.preferencesLiveData.observeForever { prefs ->
            loadWeather(prefs)
        }
    }

    fun loadWeather(preferences: UserPreferences) {
        viewModelScope.launch {
            _weatherState.value = WeatherUiState.Loading
            try {
                val (current, hourly, daily) = awaitAll(
                    async { loadCurrentWeather(preferences) },
                    async { loadHourlyWeather(preferences) },
                    async { loadDailyWeather(preferences) }
                )
                _weatherState.value = WeatherUiState.Success(
                    current as CurrentWeather,
                    hourly as List<HourlyWeather>,
                    daily as List<DailyWeather>
                )
            } catch (e: Exception) {
                _weatherState.value = WeatherUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    private suspend fun loadCurrentWeather(preferences: UserPreferences) =
        repository.getCurrentWeather(preferences.city.apiName, ApiParameters.currentParams)

    private suspend fun loadHourlyWeather(preferences: UserPreferences) =
        repository.getHourlyWeather(preferences.city.apiName, ApiParameters.hourlyParams)

    private suspend fun loadDailyWeather(preferences: UserPreferences) =
        repository.getDailyWeather(preferences.city.apiName, ApiParameters.dailyParams, ApiParameters.daysCountForDailyWeather)

}

class WeatherViewModelFactory(
    private val repository: WeatherByCityRepository,
    private val preferencesManager: UserPreferencesManager
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            return WeatherViewModel(repository, preferencesManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}