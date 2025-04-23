package com.example.weatherapp.ui.activities

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.weatherapp.R
import com.example.weatherapp.ui.UserPreferencesManager
import com.example.weatherapp.ui.UserPreferencesManagerFactory
import com.example.weatherapp.ui.enums.City

class PrefFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

        val city = findPreference<ListPreference>(getString(R.string.city_pref_key))
        city?.apply {
            entries = City.getDisplayNames()
            entryValues = City.getApiNames()
        }
    }

}