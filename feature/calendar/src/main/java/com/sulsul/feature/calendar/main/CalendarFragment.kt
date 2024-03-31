package com.sulsul.feature.calendar.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.sulsul.core.common.base.BaseFragment
import com.sulsul.feature.calendar.R
import com.sulsul.feature.calendar.databinding.FragmentCalendarBinding
import com.sulsul.feature.calendar.main.CalendarAdapter.Companion.DAY_OF_WEEKS
import java.time.LocalDate

class CalendarFragment(
    private var pageIndex: Int,
) : BaseFragment<FragmentCalendarBinding>() {

    private lateinit var calendarAdapter: CalendarAdapter

    // ========= dummy data =========
    data class DrinkReport(
        val date: LocalDate,
        val state: Int,
        val drink: List<DrinkEntry>,
    )

    data class DrinkEntry(
        val drinkType: String,
        val totalAmount: Int,
    )

    var dummy = mutableListOf(
        DrinkReport(date = LocalDate.of(2024, 3, 1), state = 1, drink = listOf(DrinkEntry(drinkType = "소주", totalAmount = 2))),
        DrinkReport(date = LocalDate.of(2024, 3, 3), state = 2, drink = listOf(DrinkEntry(drinkType = "소주", totalAmount = 2))),
        DrinkReport(date = LocalDate.of(2024, 3, 5), state = 2, drink = listOf(DrinkEntry(drinkType = "소주", totalAmount = 2))),
    )
    // ================================

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentCalendarBinding {
        return FragmentCalendarBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCalendar()
    }

    private fun initCalendar() {
        val dayOfWeeks = resources.getStringArray(R.array.calendar_day_of_weeks).toList()
        pageIndex -= (Int.MAX_VALUE / 2)
        calendarAdapter = CalendarAdapter(dayOfWeeks, dummy)
        calendarAdapter.calendarManager.setSelectedMonth(pageIndex)

        binding.rvCalendar.apply {
            this.adapter = calendarAdapter
            this.layoutManager = GridLayoutManager(requireContext(), DAY_OF_WEEKS)
        }
    }
}
