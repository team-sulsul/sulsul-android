package com.sulsul.feature.calendar.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sulsul.core.data.repository.local.DrinkRecordRepository
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

    val initDate = LocalDate.now()

    private val _selectedYear = MutableLiveData<Int>()
    val selectedYear: LiveData<Int> = _selectedYear

    private val _selectedMonth = MutableLiveData<Int>()
    val selectedMonth: LiveData<Int> = _selectedMonth

    private val _drinkRecordList = MutableStateFlow<List<DrinkRecord>>(emptyList())
    val drinkRecordList: StateFlow<List<DrinkRecord>> get() = _drinkRecordList

    init {
        _selectedMonth.value = initDate.monthValue
        _selectedYear.value = initDate.year

        getDrinkRecords()
    }

    fun setSelectedMonth(index: Int) {
        val selectedDate = initDate.plusMonths(index.toLong())
        _selectedMonth.value = selectedDate.monthValue
        _selectedYear.value = selectedDate.year
    }

    fun getDrinkRecords() {
        viewModelScope.launch {
            repository.getRecordAll().collect { records ->
                _drinkRecordList.value = records
            }
        }
    }
}
