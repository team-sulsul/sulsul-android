package com.sulsul.feature.calendar.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sulsul.core.data.repository.local.DrinkRecordRepository
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
    private val repository: DrinkRecordRepository,
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

    private val _drinkInfoList = MutableStateFlow<List<DrinkInfo>>(emptyList())
    val drinkInfoList: StateFlow<List<DrinkInfo>> = _drinkInfoList

    init {
        getDrinkRecords()
    }

    fun setCalendarDate(index: Int) {
        val currentDate = calendarDate.plusMonths(index.toLong())
        _calendarYear.value = currentDate.year
        _calendarMonth.value = currentDate.monthValue
    }

    fun getDrinkRecords() {
        viewModelScope.launch {
//            repository.getRecordAll().collect { records ->
//                _drinkRecordList.value = records
//            }

            val records = listOf(
                DrinkRecord(
                    id = 1,
                    recordedAt = LocalDate.of(2024, 4, 1),
                    drunkennessLevel = "DRUNKEN_LEVEL_4",
                    drinks = listOf(
                        DrinkInfo(
                            recordId = 1,
                            drinkType = "SOJU",
                            quantity = 21
                        ),
                        DrinkInfo(
                            recordId = 1,
                            drinkType = "SOJUBEER",
                            quantity = 10
                        ),
                        DrinkInfo(
                            recordId = 1,
                            drinkType = "RICE_WINE",
                            quantity = 3
                        )
                    )
                ),
                DrinkRecord(
                    id = 1,
                    recordedAt = LocalDate.of(2024, 4, 2),
                    drunkennessLevel = "DRUNKEN_LEVEL_3",
                    drinks = listOf(
                        DrinkInfo(
                            recordId = 1,
                            drinkType = "SAKE",
                            quantity = 22
                        ),
                        DrinkInfo(
                            recordId = 1,
                            drinkType = "SOJUBEER",
                            quantity = 10
                        )
                    )
                ),

            )
            _drinkRecordList.value = records
        }
    }

    fun setDrinkInfoList(drinkInfoList: List<DrinkInfo>) {
        _drinkInfoList.value = drinkInfoList
    }

    fun setDate(selectedDate: LocalDate) {
        _selectedDate.value = selectedDate
    }
}
