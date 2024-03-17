package com.sulsul.feature.calendar.drink

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sulsul.feature.calendar.databinding.ItemDrinkBinding

class DrinkAdapter(
    private val drinkList: List<Drink>,
    private val onClick: (drink: Drink) -> Unit
) : RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolder {
        val binding =
            ItemDrinkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DrinkViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DrinkViewHolder, position: Int) {
        holder.bind(drinkList[position])
    }

    override fun getItemCount(): Int {
        return drinkList.size
    }

    inner class DrinkViewHolder(private val binding: ItemDrinkBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(drink: Drink) {
            with(binding) {
                containerDrinkItem.setBackgroundResource(drink.background)
                ivDrinkItem.setBackgroundResource(drink.mainImage)
                tvDrinkItem.setText(drink.title)
                tvDrinkItem.setBackgroundResource(drink.buttonColor)

                containerDrinkItem.setOnClickListener {
                    containerDrinkItem.isSelected = !containerDrinkItem.isSelected
                    onClick(drink)
                }
            }
        }
    }
}
