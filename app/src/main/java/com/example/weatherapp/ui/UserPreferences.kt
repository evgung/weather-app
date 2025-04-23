package com.example.weatherapp.ui

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.example.weatherapp.R
import com.example.weatherapp.data.weatherbycity.WeatherByCityRepository
import com.example.weatherapp.ui.enums.City
import com.example.weatherapp.ui.enums.TemperatureUnit
import com.example.weatherapp.ui.enums.WindSpeedUnit
import com.example.weatherapp.ui.viewmodels.WeatherViewModel

data class UserPreferences (
    val city: City,
    val tempUnit: TemperatureUnit,
    val windUnit: WindSpeedUnit
)

class UserPreferencesManager (
    context: Context
) : ViewModel() {

    private val listener = object : SharedPreferences.OnSharedPreferenceChangeListener {
        override fun onSharedPreferenceChanged(
            sharedPreferences: SharedPreferences?,
            key: String?
        ) {
            Log.d(this.toString(), "shared preferences key \"$key\" changed")
            loadPreferences()
        }
    }

    private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    private val _preferencesLiveData = MutableLiveData<UserPreferences>()
    val preferencesLiveData: LiveData<UserPreferences> = _preferencesLiveData
    val preferences get() = preferencesLiveData.value!!

    val CITY_KEY = context.getString(R.string.city_pref_key)
    val TEMP_UNIT_KEY = context.getString(R.string.temp_unit_pref_key)
    val WIND_SPEED_KEY = context.getString(R.string.wind_unit_pref_key)

    init {
        loadPreferences()
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
    }

    fun loadPreferences() {
        val city = sharedPreferences.getString(CITY_KEY, City.default().apiName)!!
        val tempUnit = sharedPreferences.getString(TEMP_UNIT_KEY, TemperatureUnit.default().apiName)!!
        val speedUnit = sharedPreferences.getString(WIND_SPEED_KEY, WindSpeedUnit.default().apiName)!!

        _preferencesLiveData.value = UserPreferences(
            City.findByApiName(city),
            TemperatureUnit.findByApiName(tempUnit),
            WindSpeedUnit.findByApiName(speedUnit)
        )
    }

}

class UserPreferencesManagerFactory(
    private val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserPreferencesManager::class.java)) {
            return UserPreferencesManager(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}