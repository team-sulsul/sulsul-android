package com.sulsul.feature.calendar.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sulsul.core.data.local.repository.RecordRepository
import com.sulsul.feature.calendar.enums.DrunkenStateTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class DrunkenStateViewModel @Inject constructor(
    private val repository: RecordRepository
): ViewModel() {

    var state = DrunkenStateTheme.DRUNKEN_LEVEL_DEFAULT.name
    fun updateStatus(date: LocalDate, level: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateDrunkennessLevel(date, level)
        }
    }
}
