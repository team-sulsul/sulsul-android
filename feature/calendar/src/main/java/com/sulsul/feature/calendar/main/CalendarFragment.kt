package com.sulsul.feature.calendar.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.sulsul.core.common.base.BaseFragment
import com.sulsul.feature.calendar.databinding.FragmentCalendarBinding

class CalendarFragment : BaseFragment<FragmentCalendarBinding>() {

    private lateinit var calendarAdapter: CalendarAdapter
    private var curMonth= 2
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCalendarBinding {
        return FragmentCalendarBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCalendar()
        binding.tvCalendarMonth.text = "${curMonth}월"
        binding.tvCalendarYear.text = "2024"
        initListener()
    }

    private fun initCalendar() {
        calendarAdapter = CalendarAdapter()
        binding.rvCalendar.apply {
            adapter = calendarAdapter
            layoutManager = GridLayoutManager(requireContext(), 7)
        }
    }

    private fun initListener() {
        binding.btnCalendarBack.setOnClickListener {

        }
    }
}
