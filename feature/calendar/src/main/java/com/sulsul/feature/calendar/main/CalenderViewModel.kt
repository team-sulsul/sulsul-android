package com.sulsul.feature.calendar.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sulsul.core.data.local.repository.RecordRepository
import com.sulsul.core.model.DrinkRecord
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalenderViewModel @Inject constructor(
    private val repository: RecordRepository,
) : ViewModel() {

    private val calendarDate = LocalDate.now()

    private val _calendarYear = MutableStateFlow<Int>(calendarDate.year)
    val calendarYear: StateFlow<Int> = _calendarYear

    private val _calendarMonth = MutableStateFlow<Int>(calendarDate.monthValue)
    val calendarMonth: StateFlow<Int> = _calendarMonth

    private val _selectedDate = MutableStateFlow<LocalDate>(LocalDate.now())
    val selectedDate: StateFlow<LocalDate> = _selectedDate

    private val _drinkRecordList = MutableStateFlow<List<DrinkRecord>>(emptyList())
    val drinkRecordList: StateFlow<List<DrinkRecord>> = _drinkRecordList

    private val _drinkRecord = MutableStateFlow<DrinkRecord>(DrinkRecord())
    val drinkRecord: StateFlow<DrinkRecord> = _drinkRecord

    var pageIndex = 0

    private var _isLoaded = MutableLiveData(false)
    val isLoaded: LiveData<Boolean> = _isLoaded

    init {
        getDrinkRecords()
    }

    fun setCalendarDate(index: Int) {
        val currentDate = calendarDate.plusMonths(index.toLong())
        _calendarYear.value = currentDate.year
        _calendarMonth.value = currentDate.monthValue
    }

    private fun getDrinkRecords() {
        viewModelScope.launch {
            repository.getRecordAll().collect { records ->
                _drinkRecordList.value = records
                Log.d("###", "$records")
                _isLoaded.value = true
            }
        }
    }

    fun setRecord(drinkRecord: DrinkRecord) {
        _drinkRecord.value = drinkRecord
    }

    fun setDate(selectedDate: LocalDate) {
        _selectedDate.value = selectedDate
    }
}
