package com.sulsul.feature.calendar.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.sulsul.core.common.base.BaseFragment
import com.sulsul.core.model.DrinkRecord
import com.sulsul.feature.calendar.R
import com.sulsul.feature.calendar.databinding.FragmentCalendarBinding
import com.sulsul.feature.calendar.main.CalendarAdapter.Companion.DAY_OF_WEEKS
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CalendarFragment(
) : BaseFragment<FragmentCalendarBinding>() {

    private lateinit var calendarAdapter: CalendarAdapter
    private val viewModel: CalenderViewModel by activityViewModels()
    private var pageIndex: Int = 0

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentCalendarBinding {
        arguments?.let {
            pageIndex = it.getInt("pageIndex", 0)
        }
        return FragmentCalendarBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.drinkRecordList.collect { records ->
                initCalendar(records)
            }
        }
    }

    private fun initCalendar(data: List<DrinkRecord>) {
        val dayOfWeeks = resources.getStringArray(R.array.calendar_day_of_weeks).toList()
        pageIndex -= (Int.MAX_VALUE / 2)
        calendarAdapter = CalendarAdapter(dayOfWeeks, data) { date, infoList ->
            viewModel.setDate(date)
            viewModel.setDrinkInfoList(infoList)
        }
        calendarAdapter.calendarManager.setSelectedMonth(pageIndex)

        binding.rvCalendar.apply {
            this.adapter = calendarAdapter
            this.layoutManager = GridLayoutManager(requireContext(), DAY_OF_WEEKS)
        }
    }
}
