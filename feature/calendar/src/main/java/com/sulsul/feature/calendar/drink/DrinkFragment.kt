package com.sulsul.feature.calendar.drink

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.sulsul.core.common.base.BaseFragment
import com.sulsul.core.model.DrinkInfo
import com.sulsul.core.model.DrinkRecord
import com.sulsul.feature.calendar.R
import com.sulsul.feature.calendar.databinding.FragmentDrinkBinding
import com.sulsul.feature.calendar.drink.adapter.DrinkAdapter
import com.sulsul.feature.calendar.main.adapter.DrinkRankAdapter.Companion.TOP_RANK
import com.sulsul.feature.calendar.utils.calculateQuantity
import com.sulsul.feature.calendar.utils.formatDateToString
import com.sulsul.feature.calendar.utils.splitQuantity
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate

@AndroidEntryPoint
class DrinkFragment : BaseFragment<FragmentDrinkBinding>() {

    private lateinit var drinkAdapter: DrinkAdapter
    private val viewModel: DrinkViewModel by viewModels()
    private val args: DrinkFragmentArgs by navArgs()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentDrinkBinding {
        return FragmentDrinkBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbarTitle()
        setTotalDrinkQuantityForDay()
        initDrink()
        initListener()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initDrink() {
        val drinkThemeList = viewModel.drinkThemeList

        drinkAdapter = DrinkAdapter(drinkThemeList, args.drinkRecord.drinks) { theme, bottles, glasses ->
            val dialog = DrinkDialog(
                theme = theme,
                bottles = bottles,
                glasses = glasses,
                onCancelClicked = {},
                onSaveClicked = { bottles, glasses ->
                    if (bottles == 0 && glasses == 0) {
                        // TODO: 삭제를 의미한다
                    }
                    val data = DrinkInfo(
                        drinkType = theme.name,
                        quantity = calculateQuantity(theme.name, bottles, glasses)
                    )

                    viewModel.addDrinkRecord(
                        DrinkRecord(
                            recordedAt = LocalDate.now(),
                            drunkennessLevel = "DRUNKEN_DEFAULT",
                            drinks = listOf(data)
                        )
                    )

                    drinkAdapter.notifyDataSetChanged()
                }
            )
            dialog.show(childFragmentManager, "DIALOG_DRINK")
        }

        binding.rvDrink.apply {
            this.adapter = drinkAdapter
            this.layoutManager = GridLayoutManager(requireContext(), TOP_RANK)
        }
    }

    private fun initListener() {
        binding.btnDrinkBack.setOnClickListener {
            Navigation.findNavController(it).navigateUp()
        }
        binding.tvDrinkNext.setOnClickListener {
            val action = DrinkFragmentDirections.actionDrinkFragmentToDrunkenStateFragment(args.drinkRecord)
            findNavController().navigate(action)
        }
    }

    private fun setToolbarTitle() {
        binding.tvDrinkDate.text = formatDateToString(args.drinkRecord.recordedAt)
    }

    private fun setTotalDrinkQuantityForDay() {
        var bottles = 0
        var glasses = 0

        args.drinkRecord.drinks.forEach {
            val (b, g) = splitQuantity(it.drinkType, it.quantity)
            bottles += b
            glasses += g
        }

        binding.tvDrinkTotalLabel.text = getString(R.string.drink_total_quantity, bottles, glasses)
    }
}
