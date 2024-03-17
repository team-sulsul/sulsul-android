package com.sulsul.feature.calendar.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sulsul.feature.calendar.R
import com.sulsul.feature.calendar.databinding.ItemDateBinding
import com.sulsul.feature.calendar.databinding.ItemDateWithImageBinding
import com.sulsul.feature.calendar.databinding.ItemDayBinding

class CalendarAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val dateList = listOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31)
    private val day = listOf("월", "화", "수", "목", "금", "토", "일")
    private val today = 11

    companion object {
        const val VIEW_TYPE_DAY = 0
        const val VIEW_TYPE_DATE = 1
        const val VIEW_TYPE_DATE_WITH_IMAGE = 2
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < 7) {
            VIEW_TYPE_DAY
        } else if (position < 6 + today) {
            VIEW_TYPE_DATE_WITH_IMAGE
        } else {
            VIEW_TYPE_DATE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            VIEW_TYPE_DAY -> {
                val binding = ItemDayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
            is DayViewHolder -> holder.bind(day[position])
            is DateWithImageViewHolder -> holder.bind(dateList[position - 7])
            is DateViewHolder -> holder.bind(dateList[position - 6 - today])
        }
    }

    override fun getItemCount(): Int {
        return dateList.size + 7
    }


    inner class DayViewHolder(private val binding: ItemDayBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(day: String) {
            binding.tvCalendarItemDay.text = day
        }

    }

    inner class DateWithImageViewHolder(private val binding: ItemDateWithImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(date: Int) {
            binding.tvCalendarItemSmallDate.apply {
                if (date == today - 1) {
                    setBackgroundResource(R.drawable.bg_blue300_circle)
                    setTextColor(context.getColor(com.sulsul.core.designsystem.R.color.white))
                }
                text = date.toString()
            }
        }
    }

    inner class DateViewHolder(private val binding: ItemDateBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(date: Int) {
            binding.tvCalendarItemLargeDate.text = date.toString()
        }
    }
}
