package com.sulsul.feature.calendar.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sulsul.feature.calendar.R
import com.sulsul.feature.calendar.databinding.ItemDateBinding
import com.sulsul.feature.calendar.databinding.ItemDateWithImageBinding
import com.sulsul.feature.calendar.databinding.ItemDayOfWeeksBinding
import java.time.LocalDate

class CalendarAdapter(
    private val dayOfWeeks: List<String>,
    private val drinkReportList: List<CalendarFragment.DrinkReport>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val curDate = LocalDate.now()
    private val curYear = curDate.year
    private val curMonth = curDate.month

    val calendarManager = CalendarManager()

    companion object {
        const val VIEW_TYPE_DAY_OF_WEEKS = 0
        const val VIEW_TYPE_DATE = 1
        const val VIEW_TYPE_DATE_WITH_IMAGE = 2

        const val DAY_OF_WEEKS = 7
    }

    override fun getItemViewType(position: Int): Int {
        val isAfterCurYear = (curYear < calendarManager.getSelectedYear())
        val isAfterCurMonth = (curMonth < calendarManager.getSelectedMonth())
        val isSameYear = (curYear == calendarManager.getSelectedYear())
        val isSameMonth = (curMonth == calendarManager.getSelectedMonth())
        val todayPosition = calendarManager.dateList.indexOf(curDate.dayOfMonth) + DAY_OF_WEEKS

        return when {
            (position < DAY_OF_WEEKS) -> VIEW_TYPE_DAY_OF_WEEKS
            (isAfterCurYear || (isSameYear && isAfterCurMonth)) -> VIEW_TYPE_DATE
            (isSameYear && isSameMonth) -> {
                if (position <= todayPosition) {
                    VIEW_TYPE_DATE_WITH_IMAGE
                } else {
                    VIEW_TYPE_DATE
                }
            }

            else -> VIEW_TYPE_DATE_WITH_IMAGE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_DAY_OF_WEEKS -> {
                val binding = ItemDayOfWeeksBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                DayViewHolder(binding)
            }

            VIEW_TYPE_DATE_WITH_IMAGE -> {
                val binding = ItemDateWithImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                DateWithImageViewHolder(binding)
            }

            else -> {
                val binding = ItemDateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                DateViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DayViewHolder -> holder.bind(dayOfWeeks[position])
            is DateWithImageViewHolder -> holder.bind(calendarManager.dateList[position - DAY_OF_WEEKS], drinkReportList)
            is DateViewHolder -> holder.bind(calendarManager.dateList[position - DAY_OF_WEEKS])
        }
    }

    override fun getItemCount(): Int {
        return calendarManager.dateList.size + DAY_OF_WEEKS
    }

    inner class DayViewHolder(private val binding: ItemDayOfWeeksBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dayOfWeeks: String) {
            binding.tvCalendarItemDayOfWeeks.text = dayOfWeeks
        }
    }

    inner class DateWithImageViewHolder(private val binding: ItemDateWithImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(date: Int, drink: List<CalendarFragment.DrinkReport>) {
            if (date == 0) {
                binding.containerCalendarItem.visibility = View.INVISIBLE
            } else {
                // 날짜
                binding.tvCalendarItemSmallDate.apply {
                    if (isSameDate(curDate, date)) {
                        setBackgroundResource(R.drawable.bg_blue300_circle)
                        setTextColor(context.getColor(com.sulsul.core.designsystem.R.color.white))
                    }
                    this.text = date.toString()
                }
                // 상태 이미지
                binding.ivCalendarItemState.apply {
                    drink.forEach {
                        if (isSameDate(it.date, date)) {
                            this.setImageResource(com.sulsul.core.designsystem.R.drawable.ic_drunken_state_1)
                        }
                    }

                    this.setOnClickListener {
                        // TODO: 선택한 날짜의 데이터를 MainFragment에 전달
                    }
                }
            }
        }
    }

    inner class DateViewHolder(private val binding: ItemDateBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(date: Int) {
            if (date == 0) {
                binding.tvCalendarItemLargeDate.visibility = View.INVISIBLE
            } else {
                binding.tvCalendarItemLargeDate.text = date.toString()
            }
        }
    }

    private fun isSameDate(date: LocalDate, itemDate: Int): Boolean {
        return (date.year == calendarManager.getSelectedYear()) &&
            (date.month == calendarManager.getSelectedMonth()) &&
            (date.dayOfMonth == itemDate)
    }
}
