package com.sulsul.feature.calendar.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sulsul.feature.calendar.databinding.ItemDrinkRankBinding
import com.sulsul.feature.calendar.databinding.ItemDrinkRankEmptyBinding

class DrinkRankAdapter(
    private val rankList: List<String>,
    private val rankData: List<MainFragment.DrinksRank>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_RANK = 0
        const val VIEW_TYPE_EMPTY_RANK = 1

        const val TOP_RANK = 3
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < rankData.size) {
            VIEW_TYPE_RANK
        } else {
            VIEW_TYPE_EMPTY_RANK
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_RANK -> {
                val binding = ItemDrinkRankBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                RankViewHolder(binding)
            }
            else -> {
                val binding = ItemDrinkRankEmptyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                EmptyRankViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RankViewHolder -> holder.bind(rankList[position], rankData[position])
        }
    }

    override fun getItemCount(): Int {
        return TOP_RANK
    }

    inner class RankViewHolder(private val binding: ItemDrinkRankBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(rank: String, drink: MainFragment.DrinksRank) {
            binding.tvCalendarItemDrinkRankLabel.text = rank
            binding.ivCalendarItemDrinkRank.setImageResource(drink.image)
            binding.tvCalendarItemDrinkRankType.text = drink.title
            binding.tvCalendarItemDrinkRankAmount.text = drink.amount
        }
    }

    inner class EmptyRankViewHolder(private val binding: ItemDrinkRankEmptyBinding) : RecyclerView.ViewHolder(binding.root)
}
