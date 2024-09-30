package com.sulsul.feature.calendar.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sulsul.core.data.local.repository.RecordLocalRepository
import com.sulsul.core.model.DrinkInfo
import com.sulsul.core.model.DrinkRecord
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalenderViewModel @Inject constructor(
    private val repository: RecordLocalRepository,
) : ViewModel() {

    private val calendarDate = LocalDate.now()

    private val _calendarYear = MutableStateFlow<Int>(calendarDate.year)
    val calendarYear: StateFlow<Int> = _calendarYear

    private val _calendarMonth = MutableStateFlow<Int>(calendarDate.monthValue)
    val calendarMonth: StateFlow<Int> = _calendarMonth

    private val _selectedDate = MutableStateFlow<LocalDate>(LocalDate.now())
    val selectedDate: StateFlow<LocalDate> = _selectedDate

    private val _recordList = MutableStateFlow<List<DrinkRecord>>(emptyList())
    val recordList: StateFlow<List<DrinkRecord>> = _recordList

    private var _drinkRecord = MutableStateFlow<DrinkRecord>(DrinkRecord())
    val drinkRecord: StateFlow<DrinkRecord> = _drinkRecord

    private val _drinkInfoList = MutableStateFlow<List<DrinkInfo>>(emptyList())
    val drinkInfoList: StateFlow<List<DrinkInfo>> = _drinkInfoList

    var pageIndex = 0

    private var _isLoaded = MutableLiveData(false)
    val isLoaded: LiveData<Boolean> = _isLoaded

    var position = -1

    init {
        getDrinkRecords()
    }

    fun setCalendarDate(index: Int) {
        val currentDate = calendarDate.plusMonths(index.toLong())
        _calendarYear.value = currentDate.year
        _calendarMonth.value = currentDate.monthValue
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("CalenderViewModel", "onCleared")
    }

    private fun getDrinkRecords() {
        viewModelScope.launch {
//            repository.getRecordAll().collect { records ->
//                _recordList.value = records
//                Log.d("###", "$records")
//                getDrinkInfoById(records.last().id)
//                _isLoaded.value = true
//            }
            repository.getRecord().collect { records ->
                _recordList.value = records
                Log.d("###", "$records")

                _isLoaded.value = true
            }
        }
    }

    fun getDrinkInfoById(id: Long) {
        viewModelScope.launch {
            repository.getDrinkInfoList(id).collect {
                drinkRecord
                _drinkInfoList.value = it

                // 넘길 기록 세팅해주어야 함!
                _drinkRecord.value.drinks = it
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
