package com.example.weatherapp.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.R
import com.example.weatherapp.data.city.api.NetworkCityClient
import com.example.weatherapp.data.weather.api.NetworkWeatherClient
import com.example.weatherapp.data.weather.model.processed.CurrentWeather
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.data.weatherbycity.WeatherByCityRepository
import com.example.weatherapp.ui.viewmodels.WeatherUiState
import com.example.weatherapp.ui.viewmodels.WeatherViewModel
import com.example.weatherapp.ui.viewmodels.WeatherViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val weatherRepository by lazy {
        WeatherByCityRepository(
            NetworkWeatherClient().repository,
            NetworkCityClient().repository
        )
    }

    private val viewModel: WeatherViewModel by viewModels {
        WeatherViewModelFactory(weatherRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.weatherState.observe(this) { state ->
            when (state) {
                is WeatherUiState.Success -> showWeather(state)
                is WeatherUiState.Loading -> showLoading()
                is WeatherUiState.Error -> showError(state.message)
            }
        }

    }

    private fun showError(message: String) {

    }

    private fun showLoading() {

    }

    private fun showWeather(state: WeatherUiState.Success) {
        showCurrentWeather(state.currentWeather)
    }

    private fun showCurrentWeather(weather: CurrentWeather) {
        with (binding) {
            tvTemperature.text = getStringWithoutSpace(weather.temperature, viewModel.currentTempUnit.displayName)
            tvWeather.text = weather.weatherType
            tvApparentTemperature.text = getStringWithoutSpace(weather.apparentTemperature, viewModel.currentTempUnit.displayName)
            tvHumidity.text = getString(R.string.humidity, weather.relativeHumidity)
            tvPressure.text = getStringWithSpace(weather.pressure, "мм рт.ст.")
        }
    }

    private fun getStringWithSpace(value: Int?, unit: String)
        = getString(R.string.weather_with_space, value, unit)

    private fun getStringWithoutSpace(value: Int?, unit: String)
        = getString(R.string.weather_without_space, value, unit)
}