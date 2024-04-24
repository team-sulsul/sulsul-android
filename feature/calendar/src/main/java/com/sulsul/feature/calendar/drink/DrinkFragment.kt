package com.sulsul.feature.calendar.drink

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.sulsul.core.common.base.BaseFragment
import com.sulsul.core.designsystem.view.dialog.TwoButtonDialog
import com.sulsul.core.model.DrinkInfo
import com.sulsul.core.model.DrinkRecord
import com.sulsul.feature.calendar.R
import com.sulsul.feature.calendar.databinding.FragmentDrinkBinding
import com.sulsul.feature.calendar.drink.adapter.DrinkAdapter
import com.sulsul.feature.calendar.main.adapter.DrinkRankAdapter.Companion.TOP_RANK
import com.sulsul.feature.calendar.utils.formatDateToString
import com.sulsul.feature.calendar.utils.splitQuantity
import dagger.hilt.android.AndroidEntryPoint

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

        initDrinkList()
        initDrinkRecordId()
        initDrinkAdapter()
        initListener()
        setToolbarTitle()
        setDeleteButtonVisibility()
        setTotalDrinkQuantityForDay()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initDrinkAdapter() {
        val drinkThemeList = viewModel.drinkThemeList

        drinkAdapter = DrinkAdapter(drinkThemeList) { theme, bottles, glasses ->
            val dialog = DrinkDialog(
                theme = theme,
                bottles = bottles,
                glasses = glasses,
                onCancelClicked = {},
                onSaveClicked = { quantity ->
                    if (quantity == 0) {
                        val removedDrink = viewModel.drinks.find { it.drinkType == theme.name }
                        viewModel.drinks.remove(removedDrink)
                    } else {
                        val drink = DrinkInfo(
                            recordId = viewModel.recordId,
                            drinkType = theme.name,
                            quantity = quantity
                        )

                        viewModel.drinks.add(drink)
                    }

                    drinkAdapter.setDrinks(viewModel.drinks)
                    drinkAdapter.notifyDataSetChanged()
                    setTotalDrinkQuantityForDay()
                    Log.d("###", "drinkList ${viewModel.drinks}")
                }
            )
            dialog.show(childFragmentManager, "DIALOG_DRINK")
        }

        drinkAdapter.setDrinks(args.drinkRecord.drinks)

        binding.rvDrink.apply {
            this.adapter = drinkAdapter
            this.layoutManager = GridLayoutManager(requireContext(), TOP_RANK)
        }
    }

    private fun initDrinkList() {
        args.drinkRecord.drinks.forEach {
            viewModel.drinks.add(it)
        }
    }

    private fun initDrinkRecordId() {
        if (args.drinkRecord.drinks.isNotEmpty()) {
            viewModel.recordId = args.drinkRecord.drinks[0].recordId
        }
    }

    private fun initListener() {
        binding.btnDrinkBack.setOnClickListener {
            Navigation.findNavController(it).navigateUp()
        }
        binding.tvDrinkNext.setOnClickListener {
            if (args.drinkRecord.drinks.isEmpty()) {
                // 상태 선택 화면으로 넘어걸 때 한 번에 저장한다
                viewModel.insertDrinkRecord(
                    DrinkRecord(
                        recordedAt = args.drinkRecord.recordedAt,
                        drinks = viewModel.drinks
                    )
                )
            } else {
                viewModel.updateDrinks(
                    viewModel.drinks[0].recordId,
                    viewModel.drinks
                )
            }

            val action = DrinkFragmentDirections.actionDrinkFragmentToDrunkenStateFragment(args.drinkRecord)
            findNavController().navigate(action)
        }

        binding.tvDrinkDelete.setOnClickListener {
            val dialog = TwoButtonDialog(
                title = "삭제",
                subtitle = "모든 기록을 삭제하시겠습니까?",
                leftButton = "취소",
                rightButton = "삭제",
                onLeftButtonClicked = {},
                onRightButtonClicked = {
                    viewModel.deleteDrinkRecord(args.drinkRecord.recordedAt)
                    Navigation.findNavController(it).navigateUp()
                }
            )
            dialog.show(childFragmentManager, "DELETE_DIALOG")
        }
    }

    private fun setToolbarTitle() {
        binding.tvDrinkDate.text = formatDateToString(args.drinkRecord.recordedAt)
    }

    private fun setTotalDrinkQuantityForDay() {
        var bottles = 0
        var glasses = 0

        viewModel.drinks.forEach {
            val (b, g) = splitQuantity(it.drinkType, it.quantity)
            bottles += b
            glasses += g
        }

        binding.tvDrinkTotalLabel.text = getString(R.string.drink_total_quantity, bottles, glasses)
    }

    private fun setDeleteButtonVisibility() {
        if (viewModel.drinks.isNotEmpty()) {
            binding.tvDrinkDelete.visibility = View.VISIBLE
        } else {
            binding.tvDrinkDelete.visibility = View.INVISIBLE
        }
    }
}
