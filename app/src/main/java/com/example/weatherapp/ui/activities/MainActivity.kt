package com.example.weatherapp.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.city.api.NetworkCityClient
import com.example.weatherapp.data.weather.api.NetworkWeatherClient
import com.example.weatherapp.data.weather.model.processed.CurrentWeather
import com.example.weatherapp.data.weatherbycity.WeatherByCityRepository
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.ui.viewmodels.UserPreferencesManager
import com.example.weatherapp.ui.viewmodels.UserPreferencesManagerFactory
import com.example.weatherapp.ui.adapters.HourlyWeatherAdapter
import com.example.weatherapp.ui.objects.WeatherConverter
import com.example.weatherapp.ui.viewmodels.WeatherUiState
import com.example.weatherapp.ui.viewmodels.WeatherViewModel
import com.example.weatherapp.ui.viewmodels.WeatherViewModelFactory
import kotlin.math.roundToInt


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

    private val weatherConverter = WeatherConverter(this)

    private val hourlyScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            binding.swipeRefreshLayout.isEnabled = newState == RecyclerView.SCROLL_STATE_IDLE
        }
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
            errorCard.visibility = View.VISIBLE
            tvError.text = message
            weatherLayout.visibility = View.GONE
        }
    }

    private fun showLoading() {
        with (binding) {
            swipeRefreshLayout.isRefreshing = true
            errorCard.visibility = View.GONE
            weatherLayout.visibility = View.GONE
        }
    }

    private fun showWeather(state: WeatherUiState.Success) {
        with (binding) {
            swipeRefreshLayout.isRefreshing = false
            errorCard.visibility = View.GONE
            weatherLayout.visibility = View.VISIBLE
            tvCity.text = userPrefManager.preferences.city.displayName
        }

        showCurrentWeather(state.weather.current)
        binding.hourlyRecyclerView.adapter = HourlyWeatherAdapter(state.weather.hourly, weatherConverter)
        binding.hourlyRecyclerView.addOnScrollListener(hourlyScrollListener)
    }

    private fun showCurrentWeather(weather: CurrentWeather) {
        with (binding) {
            tvTemperature.text = weatherConverter.temperatureToString(weather.temperature)
            tvWeather.text = weatherConverter.weatherCodeToString(weather.weatherCode)
            tvApparentTemperature.text = weatherConverter.temperatureToString(weather.apparentTemperature)
            tvHumidity.text = getString(R.string.humidity, weather.relativeHumidity)
            tvPressure.text = getString(R.string.pressure, weatherConverter.pressureTommhg(weather.pressure))
            tvWind.text = getString(R.string.weather_with_space, weather.windSpeed?.roundToInt(), userPrefManager.preferences.windUnit.displayName)
        }
    }
}