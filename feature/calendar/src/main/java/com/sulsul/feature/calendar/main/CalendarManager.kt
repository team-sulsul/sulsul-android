package com.sulsul.feature.calendar.main

import java.time.LocalDate
import java.time.Month

class CalendarManager {

    private var selectedDate = LocalDate.now()
    val dateList = arrayListOf<Int>()

    fun initMonthData() {
        dateList.clear()
        val firstDayOfMonth = selectedDate.withDayOfMonth(1).dayOfWeek.value

        for (i in 1 until firstDayOfMonth) {
            dateList.add(0)
        }
        for (i in 1..selectedDate.lengthOfMonth()) {
            dateList.add(i)
        }
    }

    fun setSelectedMonth(index: Int) {
        selectedDate = selectedDate.plusMonths(index.toLong())
        initMonthData()
    }

    fun getSelectedMonth(): Month {
        return selectedDate.month
    }

    fun getSelectedYear(): Int {
        return selectedDate.year
    }
}
