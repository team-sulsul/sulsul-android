package com.sulsul.feature.calendar.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class CalendarPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val pageCount = Int.MAX_VALUE
    val initialPosition = Int.MAX_VALUE / 2
    override fun getItemCount(): Int {
        return pageCount
    }

    override fun createFragment(position: Int): Fragment {
        return CalendarFragment(position)
    }
}
