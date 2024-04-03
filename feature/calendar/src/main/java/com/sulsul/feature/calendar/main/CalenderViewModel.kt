package com.sulsul.feature.calendar.main

import android.util.Log
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

    private val _initDate = MutableStateFlow<LocalDate>(LocalDate.now())
    val initDate: StateFlow<LocalDate> = _initDate


    private val _selectedYear = MutableLiveData<Int>()
    val selectedYear: LiveData<Int> = _selectedYear

    private val _selectedMonth = MutableLiveData<Int>()
    val selectedMonth: LiveData<Int> = _selectedMonth

    private val _drinkRecordList = MutableStateFlow<List<DrinkRecord>>(emptyList())
    val drinkRecordList: StateFlow<List<DrinkRecord>> get() = _drinkRecordList

    private val _drinkInfoList = MutableStateFlow<List<DrinkInfo>>(emptyList())
    val drinkInfoList: StateFlow<List<DrinkInfo>> get() = _drinkInfoList

    init {
        _selectedMonth.value = _initDate.value.monthValue
        _selectedYear.value = _initDate.value.year
        Log.d("###", "캘린더 뷰모델 초기화 $initDate")

        getDrinkRecords()
    }

    fun setSelectedMonth(index: Int) {
        val selectedDate = _initDate.value.plusMonths(index.toLong())
        _selectedMonth.value = selectedDate.monthValue
        _selectedYear.value = selectedDate.year
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
        _initDate.value = selectedDate
    }
}
