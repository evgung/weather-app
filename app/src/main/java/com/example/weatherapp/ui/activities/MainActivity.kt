package com.example.weatherapp.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.preference.PreferenceManager
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.R
import com.example.weatherapp.data.city.api.NetworkCityClient
import com.example.weatherapp.data.weather.api.NetworkWeatherClient
import com.example.weatherapp.data.weather.model.processed.CurrentWeather
import com.example.weatherapp.data.weatherbycity.WeatherByCityRepository
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.ui.UserPreferencesManager
import com.example.weatherapp.ui.UserPreferencesManagerFactory
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

    private val userPrefManager: UserPreferencesManager by viewModels {
        UserPreferencesManagerFactory(this)
    }

    private val weatherViewModel: WeatherViewModel by viewModels {
        WeatherViewModelFactory(weatherRepository, userPrefManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        weatherViewModel.weatherState.observe(this) { state ->
            when (state) {
                is WeatherUiState.Success -> showWeather(state)
                is WeatherUiState.Loading -> showLoading()
                is WeatherUiState.Error -> showError(state.message)
            }
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            weatherViewModel.loadWeather(userPrefManager.preferences)
        }

        binding.btnSettings.setOnClickListener {
            startActivity(Intent(this, PrefActivity::class.java))
        }
    }

    private fun showError(message: String) {
        with (binding) {
            swipeRefreshLayout.isRefreshing = false
            tvError.visibility = View.VISIBLE
            tvError.text = message
            weatherLayout.visibility = View.GONE
        }
    }

    private fun showLoading() {
        with (binding) {
            swipeRefreshLayout.isRefreshing = true
            tvError.visibility = View.GONE
            weatherLayout.visibility = View.GONE
        }
    }

    private fun showWeather(state: WeatherUiState.Success) {
        with (binding) {
            swipeRefreshLayout.isRefreshing = false
            tvError.visibility = View.GONE
            weatherLayout.visibility = View.VISIBLE
            tvCity.text = userPrefManager.preferences.city.displayName
        }

        showCurrentWeather(state.currentWeather)
    }

    private fun showCurrentWeather(weather: CurrentWeather) {
        with (binding) {
            tvTemperature.text = getString(R.string.weather_without_space, weather.temperature, userPrefManager.preferences.tempUnit.displayName)
            tvWeather.text = weather.weatherType
            tvApparentTemperature.text = getString(R.string.weather_without_space, weather.apparentTemperature, userPrefManager.preferences.tempUnit.displayName)
            tvHumidity.text = getString(R.string.humidity, weather.relativeHumidity)
            tvPressure.text = getString(R.string.pressure, weather.pressure)
            tvWind.text = getString(R.string.weather_with_space, weather.windSpeed, userPrefManager.preferences.windUnit.displayName)
        }
    }
}