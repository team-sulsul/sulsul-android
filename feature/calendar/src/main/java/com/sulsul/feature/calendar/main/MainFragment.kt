package com.sulsul.feature.calendar.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.sulsul.core.common.base.BaseFragment
import com.sulsul.core.model.DrinkInfo
import com.sulsul.feature.calendar.R
import com.sulsul.feature.calendar.databinding.FragmentMainBinding
import com.sulsul.feature.calendar.main.adapter.DrinkRankAdapter.Companion.TOP_RANK
import com.sulsul.feature.calendar.main.adapter.CalendarPagerAdapter
import com.sulsul.feature.calendar.main.adapter.DrinkRankAdapter
import com.sulsul.feature.calendar.utils.formatDateToString
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val viewModel: CalenderViewModel by activityViewModels()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initPagerCalendar()
        initObserver()
        initListener()
    }

    private fun initPagerCalendar() {
        val calendarPagerAdapter = CalendarPagerAdapter(this)
        binding.pagerCalendar.adapter = calendarPagerAdapter
        binding.pagerCalendar.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        calendarPagerAdapter.apply {
            binding.pagerCalendar.setCurrentItem(this.initialPosition, false)
        }
    }

    private fun initDrinkRank(drinkInfoList: List<DrinkInfo>) {
        val rankList = resources.getStringArray(R.array.main_top_rank).toList()
        val drinkRankAdapter = DrinkRankAdapter(rankList, drinkInfoList)
        binding.rvCalendarDrinkRank.apply {
            this.adapter = drinkRankAdapter
            this.layoutManager = GridLayoutManager(requireContext(), TOP_RANK)
        }
    }

    private fun initListener() {
        binding.btnCalendarBack.setOnClickListener {
            val curPagePosition = binding.pagerCalendar.currentItem
            binding.pagerCalendar.currentItem = curPagePosition - 1
        }

        binding.btnCalendarForward.setOnClickListener {
            val curPagePosition = binding.pagerCalendar.currentItem
            binding.pagerCalendar.currentItem = curPagePosition + 1
        }

        binding.containerCalendarContent.setOnClickListener {
            val drinkRecord = viewModel.drinkRecord.value
            val action = MainFragmentDirections.actionMainFragmentToDrinkFragment(drinkRecord)
            findNavController().navigate(action)
        }

        binding.pagerCalendar.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)

                val adjustedPosition = position - (Int.MAX_VALUE / 2)
                viewModel.setCalendarDate(adjustedPosition)
            }
        })
    }

    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.calendarYear.collect { year ->
                binding.tvCalendarYear.text = year.toString()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.calendarMonth.collect { month ->
                binding.tvCalendarMonth.text = getString(R.string.main_month, month)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.drinkRecord.collect { record ->
                if (record.drinks.isNotEmpty()) {
                    binding.tvCalendarTodayLabel.text = getString(R.string.main_today_label)
                    binding.ivCalendarDrinkRankEmpty.visibility = View.GONE
                    binding.rvCalendarDrinkRank.visibility = View.VISIBLE
                    initDrinkRank(record.drinks)
                } else  {
                    binding.tvCalendarTodayLabel.text = getString(R.string.main_today_label_empty)
                    binding.ivCalendarDrinkRankEmpty.visibility = View.VISIBLE
                    binding.rvCalendarDrinkRank.visibility = View.GONE
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.selectedDate.collect { date ->
                binding.tvCalendarDateLabel.text = formatDateToString(date)
            }
        }
    }
}
