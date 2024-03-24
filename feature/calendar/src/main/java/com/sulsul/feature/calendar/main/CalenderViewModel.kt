package com.sulsul.feature.calendar.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate

class CalenderViewModel: ViewModel() {

    val initDate = LocalDate.now()

    private val _selectedYear = MutableLiveData<Int>()
    val selectedYear: LiveData<Int> = _selectedYear

    private val _selectedMonth = MutableLiveData<Int>()
    val selectedMonth: LiveData<Int> = _selectedMonth

    init {
        _selectedMonth.value = initDate.monthValue
        _selectedYear.value = initDate.year
    }

    fun setSelectedMonth(index: Int) {
        val selectedDate = initDate.plusMonths(index.toLong())
        _selectedMonth.value = selectedDate.monthValue
        _selectedYear.value = selectedDate.year
    }
}
