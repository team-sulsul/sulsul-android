package com.sulsul.feature.calendar.drink

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.sulsul.core.common.base.BaseFragment
import com.sulsul.feature.calendar.R
import com.sulsul.feature.calendar.databinding.FragmentDrinkBinding

class DrinkFragment : BaseFragment<FragmentDrinkBinding>() {
    private lateinit var drinkAdapter: DrinkAdapter

    val dummy = listOf(DrinkInfo(title = "소주", bottleCnt = 1, glassCnt = 1))

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentDrinkBinding {
        return FragmentDrinkBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvDrinkDate.text = "2024년 3월 4일"
        binding.tvDrinkTotalLabel.text = "이날 마신 술 0병 0잔"

        initDrink()
        initListener()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initDrink() {
        val drinkList = listOf<Drink>(
            Drink.SOJU,
            Drink.BEER,
            Drink.SOJUBEER,
            Drink.WINE,
            Drink.RICE_WINE,
            Drink.COCKTAIL,
            Drink.WHISKY,
            Drink.VODKA,
            Drink.SAKE
        )

        drinkAdapter = DrinkAdapter(drinkList, dummy) {
            val dialog = DrinkDialog(
                drink = it,
                onCancelClicked = {},
                onSaveClicked = { bottle, glass ->
                    it.bottleCnt = bottle
                    it.glassCnt = glass
                    it.title = it.title

                    if (bottle != 0 && glass != 0) {
                        it.title += " ${bottle}병 ${glass}잔"
                        it.isSelected = true
                    } else if (bottle != 0 && glass == 0) {
                        it.title += " ${bottle}병"
                        it.isSelected = true
                    } else if (bottle == 0 && glass != 0) {
                        it.title += " ${glass}잔"
                        it.isSelected = true
                    } else {
                        it.isSelected = false
                    }

                    drinkAdapter.notifyDataSetChanged()
                }
            )
            dialog.show(childFragmentManager, "DIALOG_DRINK")
        }

        binding.rvDrink.apply {
            this.adapter = drinkAdapter
            this.layoutManager = GridLayoutManager(requireContext(), 3)
        }
    }

    private fun initListener() {
        binding.btnDrinkBack.setOnClickListener {
            Navigation.findNavController(it).navigateUp()
        }
        binding.tvDrinkNext.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_drinkFragment_to_drunkenStateFragment)
        }
    }
}
