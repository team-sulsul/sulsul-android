package com.sulsul.feature.calendar.drink

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

        initRecyclerView()
        initListener()
    }

    private fun initRecyclerView() {
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

        drinkAdapter = DrinkAdapter(drinkList) {
            val dialog = DrinkDialog(
                drink = it,
                onCancelClicked = {},
                onSaveClicked = {}
            )
            dialog.show(childFragmentManager, "DRINK_DIALOG")
        }
        binding.rvDrink.apply {
            adapter = drinkAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
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
