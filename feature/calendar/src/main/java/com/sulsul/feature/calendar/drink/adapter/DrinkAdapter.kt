package com.sulsul.feature.calendar.drink.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sulsul.core.model.DrinkInfo
import com.sulsul.feature.calendar.databinding.ItemDrinkBinding
import com.sulsul.feature.calendar.enums.DrinkTheme
import com.sulsul.feature.calendar.utils.buildDrinkText
import com.sulsul.feature.calendar.utils.splitQuantity

class DrinkAdapter(
    private val drinkThemeList: List<DrinkTheme>,
    private val onClicked: (drinkTheme: DrinkTheme, bottles: Int, glasses: Int) -> Unit
) : RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder>() {

    private var drinkInfoList: List<DrinkInfo> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolder {
        val binding =
            ItemDrinkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DrinkViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DrinkViewHolder, position: Int) {
        holder.bind(drinkThemeList[position])
    }

    override fun getItemCount(): Int {
        return drinkThemeList.size
    }

    inner class DrinkViewHolder(private val binding: ItemDrinkBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(theme: DrinkTheme) {
            with(binding) {
                var bottles = 0
                var glasses = 0
                var isSelected = false

                containerDrinkItem.setBackgroundResource(theme.backgroundColor)
                ivDrinkItem.setBackgroundResource(theme.selectorImage)
                tvDrinkItem.setBackgroundResource(theme.buttonColor)
                tvDrinkItem.text = theme.drinkName

                drinkInfoList
                    .filter { it.drinkType == theme.name }
                    .forEach { lastDrinkInfo ->
                        val drinkText = buildDrinkText(lastDrinkInfo.drinkType, lastDrinkInfo.quantity)
                        bottles = splitQuantity(theme.name, lastDrinkInfo.quantity).first
                        glasses = splitQuantity(theme.name, lastDrinkInfo.quantity).second

                        tvDrinkItem.text = drinkText
                        isSelected = true
                    }

                containerDrinkItem.isSelected = isSelected

                containerDrinkItem.setOnClickListener {
                    onClicked(theme, bottles, glasses)
                }
            }
        }
    }

    fun setDrinks(drinks: List<DrinkInfo>) {
        drinkInfoList = drinks
    }
}
