package com.sulsul.feature.calendar.drink

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.sulsul.feature.calendar.R
import com.sulsul.feature.calendar.databinding.DialogDrinkBinding
import com.sulsul.feature.calendar.enums.DrinkTheme
import com.sulsul.feature.calendar.enums.DrinkUnitRatio

class DrinkDialog(
    private val theme: DrinkTheme,
    private var bottles: Int = 0,
    private var glasses: Int = 0,
    private val onCancelClicked: () -> Unit,
    private val onSaveClicked: (Int, Int) -> Unit
) : DialogFragment() {

    private lateinit var binding: DialogDrinkBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DialogDrinkBinding.inflate(inflater, container, false)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        init()

        return binding.root
    }

    private fun init() {
        setTitle()
        setDrinkInfo()
        setDrinkImage()
        setInitialCount()
        setupCounterListeners()
        initClickListeners()
    }

    private fun setTitle() {
        binding.tvDialogDrinkTitle.text = theme.drinkName
        binding.tvDialogDrinkTitle.setTextColor(theme.textColor)
    }

    private fun setDrinkImage() {
        if (theme.bottleImage == null) {
            binding.containerDialogDrinkBottle.visibility = View.GONE
            binding.dividerDialogDrink.visibility = View.GONE
        } else {
            binding.containerDialogDrinkGlass.visibility = View.VISIBLE
            binding.dividerDialogDrink.visibility = View.VISIBLE
            binding.ivDialogDrinkBottle.setImageResource(theme.bottleImage)
        }

        binding.ivDialogDrinkGlass.setImageResource(theme.glassImage)
    }

    private fun setDrinkInfo() {
        val ratio = DrinkUnitRatio.valueOf(theme.name).glassPerBottle

        if (ratio != null) {
            binding.tvDialogDrinkInfo.visibility = View.VISIBLE
            binding.tvDialogDrinkInfo.text = getString(R.string.dialog_drink_info, theme.drinkName, ratio)
        } else  {
            binding.tvDialogDrinkInfo.visibility = View.GONE
        }
    }

    private fun setInitialCount() {
        binding.tvDialogDrinkBottleCount.text = getString(R.string.dialog_drink_bottle_cnt, bottles)
        binding.tvDialogDrinkGlassCount.text = getString(R.string.dialog_drink_glass_cnt, glasses)
    }

    private fun initClickListeners() {
        binding.tvDialogDrinkSave.setOnClickListener {
            onSaveClicked(bottles, glasses)
            dismiss()
        }
        binding.tvDialogDrinkCancel.setOnClickListener {
            onCancelClicked()
            dismiss()
        }
    }

    private fun setupCounterListeners() {
        binding.ivDialogDrinkBottlePlus.setOnClickListener {
            changeBottleCount(1)
        }
        binding.ivDialogDrinkBottleMinus.setOnClickListener {
            changeBottleCount(-1)
        }
        binding.ivDialogDrinkGlassPlus.setOnClickListener {
            changeGlassCount(1)
        }
        binding.ivDialogDrinkGlassMinus.setOnClickListener {
            changeGlassCount(-1)
        }
    }

    private fun changeBottleCount(change: Int) {
        if (bottles + change >= 0) {
            bottles += change
            binding.tvDialogDrinkBottleCount.text = getString(R.string.dialog_drink_bottle_cnt, bottles)
        }
    }

    private fun changeGlassCount(change: Int) {
        if (glasses + change >= 0) {
            glasses += change
            binding.tvDialogDrinkGlassCount.text = getString(R.string.dialog_drink_glass_cnt, glasses)
        }
    }
}
