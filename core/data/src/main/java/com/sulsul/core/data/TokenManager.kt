package com.sulsul.core.data

import com.sulsul.core.datastore.datasource.TokenPreferenceDataSource
import com.sulsul.core.datastore.model.TokenData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class TokenManager @Inject constructor(
    private val preferencesDataStore: TokenPreferenceDataSource
){
    fun getTokenData(): Flow<TokenData> {
        return preferencesDataStore.tokenData.map {
                tokenData -> TokenData(tokenData.accessToken)
        }
    }

     suspend fun updateTokenData(accessToken: String) {
        preferencesDataStore.updateTokenData(accessToken = accessToken)
        Timber.tag("updateToken").d("token updated")
    }
}

