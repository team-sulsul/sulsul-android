package com.sulsul.feature.calendar.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sulsul.core.data.local.repository.RecordLocalRepository
import com.sulsul.core.model.DrinkRecord
import com.sulsul.feature.calendar.enums.DrunkenStateTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class DrunkenStateViewModel @Inject constructor(
    private val repository: RecordLocalRepository,
    private val remoteRepository: com.sulsul.core.data.remote.repository.RecordRemoteRepository
) : ViewModel() {

    var state = DrunkenStateTheme.DRUNKEN_LEVEL_DEFAULT.name
    fun updateStatus(date: LocalDate, level: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateDrunkennessLevel(date, level)
        }
    }

    fun postStateRecord(drinkRecord: DrinkRecord, callback: () -> Unit) {
        try {
            viewModelScope.launch {
                remoteRepository.postStateRecord(drinkRecord).collect {
//                    if (it.resultCode == 200) {
//                        Log.d("###", "code: ${it.resultData}")
//                        callback()
//                    } else {
//                        callback()
//                    }
                    callback()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            callback()
        }
    }
}
