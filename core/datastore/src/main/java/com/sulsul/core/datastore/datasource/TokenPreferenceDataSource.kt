package com.sulsul.core.datastore.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.sulsul.core.datastore.model.TokenData
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Named

class TokenPreferenceDataSource @Inject constructor(
    @Named("token") private val dataStore: DataStore<Preferences>
) {
    object PreferencesKey {
        val ACCESS_TOKEN = stringPreferencesKey("ACCESS_TOKEN")
        val REFRESH_TOKEN = stringPreferencesKey("REFRESH_TOKEN")
    }

    val tokenData = dataStore.data.map { preferences ->
        TokenData(
            accessToken = preferences[PreferencesKey.ACCESS_TOKEN] ?: "",
            refreshToken = preferences[PreferencesKey.REFRESH_TOKEN] ?: ""
        )
    }

    suspend fun updateTokenData(accessToken: String, refreshToken: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.ACCESS_TOKEN] = accessToken
            preferences[PreferencesKey.REFRESH_TOKEN] = refreshToken
        }
    }
}
