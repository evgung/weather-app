package com.example.weatherapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.data.city.api.NetworkCityClient
import com.example.weatherapp.data.weather.api.NetworkWeatherClient
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.data.weatherbycity.WeatherByCityRepository
import com.example.weatherapp.ui.viewmodels.WeatherViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var weatherRepository: WeatherByCityRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        weatherRepository = WeatherByCityRepository(
            NetworkWeatherClient().repository,
            NetworkCityClient().repository,
        )

        val m = WeatherViewModel(weatherRepository)

    }
}