package com.sulsul.feature.calendar.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.sulsul.core.common.base.BaseFragment
import com.sulsul.feature.calendar.R
import com.sulsul.feature.calendar.databinding.FragmentMainBinding
import java.time.format.DateTimeFormatter
import java.util.Locale

class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val viewModel: CalenderViewModel by viewModels()

    // ========= dummy data =========
    data class DrinksRank(
        val title: String,
        val image: Int,
        val amount: String,
    )

    var dummy = mutableListOf(
        DrinksRank(
            title = "소주",
            image = R.drawable.img_soju_selected,
            amount = "1병 2잔"
        ),
        DrinksRank(
            title = "소맥",
            image = R.drawable.img_sojubeer_selected,
            amount = "5잔"
        ),
    )
    //================================

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initPagerCalendar()
        initDrinkRank()
        initObserver()
        initListener()
        setSelectedContent()
    }

    private fun initPagerCalendar() {
        val calendarPagerAdapter = CalendarPagerAdapter(this)
        binding.pagerCalendar.adapter = calendarPagerAdapter
        binding.pagerCalendar.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        calendarPagerAdapter.apply {
            binding.pagerCalendar.setCurrentItem(this.initialPosition, false)
        }
    }

    private fun initDrinkRank() {
        binding.ivCalendarDrinkRankEmpty.visibility = View.GONE
        binding.tvCalendarTodayLabel.text = getString(R.string.main_today_label)
        val rankList = resources.getStringArray(R.array.main_top_rank).toList()
        val drinkRankAdapter = DrinkRankAdapter(rankList, dummy)
        binding.rvCalendarDrinkRank.apply {
            this.adapter = drinkRankAdapter
            this.layoutManager = GridLayoutManager(context, 3)
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
            Navigation.findNavController(it).navigate(R.id.action_mainFragment_to_drinkFragment)
        }
        binding.pagerCalendar.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)

                val adjustedPosition = position - (Int.MAX_VALUE / 2)
                viewModel.setSelectedMonth(adjustedPosition)
            }
        })
    }

    private fun initObserver() {
        viewModel.selectedMonth.observe(viewLifecycleOwner) {
            binding.tvCalendarMonth.text = getString(R.string.main_month, it)
        }
        viewModel.selectedYear.observe(viewLifecycleOwner) {
            binding.tvCalendarYear.text = it.toString()
        }
    }

    private fun setSelectedContent() {
        val formattedDate = viewModel.initDate.format(DateTimeFormatter.ofPattern("yyyy년 M월 d일", Locale.KOREAN))
        binding.tvCalendarDateLabel.text = formattedDate
    }
}
