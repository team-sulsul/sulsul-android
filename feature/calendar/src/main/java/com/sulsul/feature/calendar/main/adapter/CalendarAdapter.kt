package com.sulsul.feature.calendar.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sulsul.core.model.DrinkRecord
import com.sulsul.feature.calendar.R
import com.sulsul.feature.calendar.databinding.ItemDateBinding
import com.sulsul.feature.calendar.databinding.ItemDateWithImageBinding
import com.sulsul.feature.calendar.databinding.ItemDayOfWeeksBinding
import com.sulsul.feature.calendar.main.CalendarManager
import com.sulsul.feature.calendar.utils.getDrunkenStateTheme
import java.time.LocalDate

class CalendarAdapter(
    private val dayOfWeeks: List<String>,
    //private val drinkRecordList: List<DrinkRecord>,
    private val onClicked: (Int, LocalDate, DrinkRecord) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val curDate = LocalDate.now()
    private val curYear = curDate.year
    private val curMonth = curDate.month

    private var selectedPosition: Int = RecyclerView.NO_POSITION

    private var record: DrinkRecord? = null

    val calendarManager = CalendarManager()

    private val drinkRecordList = ArrayList<DrinkRecord>()

    fun updateDrinkRecordList(list: List<DrinkRecord>) {
        drinkRecordList.clear()
        drinkRecordList.addAll(list)
    }

    companion object {
        const val VIEW_TYPE_DAY_OF_WEEKS = 0
        const val VIEW_TYPE_DATE = 1
        const val VIEW_TYPE_DATE_WITH_IMAGE = 2

        const val DAY_OF_WEEKS = 7
    }

    fun selectedDate(date: Int) {
        selectedPosition = date
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        Log.d("onAttachedToRecyclerView", "onAttachedToRecyclerView")
        selectedPosition = 10
    }

    init {
        // 오늘 날짜에 표시한다
        selectedPosition = 9
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

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)
        holder.setIsRecyclable(false)
        Log.d("onViewRecycled", "${holder.adapterPosition}")
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        super.onViewAttachedToWindow(holder)
        Log.d("onViewAttachedToWindow", "${holder.adapterPosition}")
    }

    var holderSize = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        Log.d("onCreateViewHolder", "${holderSize++}")
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
            is DateWithImageViewHolder -> {
                holder.bind(calendarManager.dateList[position - DAY_OF_WEEKS], drinkRecordList)
                //holder.setIsRecyclable(false)
            }
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

        fun bind(date: Int, records: List<DrinkRecord>) {
            if (date == 0) {
                binding.containerCalendarItem.visibility = View.INVISIBLE
                return
            }

            //bsoluteAdapterPosition

            bindSibal()
            bindDate(date)
            bindStateImage(date, records)
            bindItemClickListener(date, records)


        }

        private fun bindDate(date: Int) {
            //Log.d("### date ###", "$date")
            val d = date


            binding.tvCalendarItemSmallDate.apply {
                if (isToday(d)) {
                    //Log.d("### position ###", "$adapterPosition")
                    //Log.d("### today ###", "$date")
                    setBackgroundResource(R.drawable.bg_blue300_circle)
                    setTextColor(context.getColor(com.sulsul.core.designsystem.R.color.white))
                }

                text = d.toString()
            }
        }


        private fun bindSibal() {
            if (selectedPosition == adapterPosition) {
                setSelected(true)
            } else {
                setSelected(false)
            }
        }


        private fun bindStateImage(date: Int, records: List<DrinkRecord>) {
            if (selectedPosition == adapterPosition) {
                val recorddd = records.firstOrNull { isSameDate(it.recordedAt, date) }
                record = recorddd
                setSelected(true)
            } else {
                setSelected(false)
            }


            val record = records.firstOrNull { isSameDate(it.recordedAt, date) }
            record?.let {
                val icon = getDrunkenStateTheme(it.drunkennessLevel).icon
                binding.ivCalendarItemState.setImageResource(icon)
            }
        }

        private fun bindItemClickListener(date: Int, records: List<DrinkRecord>) {
            val recorddd = records.firstOrNull { isSameDate(it.recordedAt, date) }
            binding.ivCalendarItemState.setOnClickListener {
                //val position = adapterPosition
                //record = recorddd

                val prevSelectedPosition = selectedPosition
                selectedPosition = adapterPosition
                notifyItemChanged(selectedPosition, recorddd)
                notifyItemChanged(prevSelectedPosition, record)



                val selectedDate = calendarManager.getSelectedDate(date)

                onClicked(
                    adapterPosition,
                    selectedDate,
                    recorddd ?: DrinkRecord(recordedAt = selectedDate)
                )
            }
        }

        private fun setSelected(isSelected: Boolean) {
            if (isSelected) {
                binding.ivCalendarItemState.setBackgroundResource(R.drawable.bg_blue300_circle)
            } else {
                binding.ivCalendarItemState.setBackgroundResource(com.sulsul.core.designsystem.R.color.transparent)
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

    private fun isToday(date: Int): Boolean {
        return (curYear == calendarManager.getSelectedYear()) &&
            (curMonth == calendarManager.getSelectedMonth()) &&
            (curDate.dayOfMonth == date)
    }
}
