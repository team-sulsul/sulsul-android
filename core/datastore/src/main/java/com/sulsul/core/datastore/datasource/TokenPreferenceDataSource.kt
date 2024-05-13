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
    }

    val tokenData = dataStore.data.map { preferences ->
        TokenData(
            accessToken = preferences[PreferencesKey.ACCESS_TOKEN] ?: ""
        )
    }

    suspend fun updateTokenData(accessToken: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.ACCESS_TOKEN] = accessToken
        }
    }
}
