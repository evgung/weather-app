package com.example.weatherapp.ui.activities

import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import com.example.weatherapp.R
import com.example.weatherapp.ui.enums.City
import com.example.weatherapp.ui.enums.TemperatureUnit
import com.example.weatherapp.ui.enums.WindSpeedUnit

class PrefFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

        val city = findPreference<ListPreference>(getString(R.string.city_pref_key))
        city?.apply {
            entries = City.getDisplayNames()
            entryValues = City.getApiNames()
        }

        val temp = findPreference<ListPreference>(getString(R.string.temp_unit_pref_key))
        temp?.apply {
            entries = TemperatureUnit.getDisplayNames()
            entryValues = TemperatureUnit.getApiNames()
        }

        val wind = findPreference<ListPreference>(getString(R.string.wind_unit_pref_key))
        wind?.apply {
            entries = WindSpeedUnit.getDisplayNames()
            entryValues = WindSpeedUnit.getApiNames()
        }
    }

}