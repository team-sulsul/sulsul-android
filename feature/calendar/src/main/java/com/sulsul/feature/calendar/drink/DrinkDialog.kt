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

class DrinkDialog(
    private val drink: Drink,
    private val onCancelClicked: () -> Unit,
    private val onSaveClicked: (Int, Int) -> Unit
) : DialogFragment() {

    private lateinit var binding: DialogDrinkBinding
    private var bottleCnt = 0
    private var glassCnt = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DialogDrinkBinding.inflate(inflater, container, false)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        init()
        initListener()

        return binding.root
    }

    private fun init() {
        with(binding) {
            tvDialogDrinkTitle.text = drink.title
            context?.let { tvDialogDrinkTitle.setTextColor(it.getColor(drink.mainColor)) }
            tvDialogDrinkBottleCount.text = getString(R.string.dialog_drink_bottle_cnt, bottleCnt)
            tvDialogDrinkGlassCount.text = getString(R.string.dialog_drink_glass_cnt, glassCnt)

            if (drink.bottleImage == null) {
                containerDialogDrinkBottle.visibility = View.GONE
                dividerDialogDrink.visibility = View.GONE
            } else {
                containerDialogDrinkGlass.visibility = View.VISIBLE
                dividerDialogDrink.visibility = View.VISIBLE
                ivDialogDrinkBottle.setImageResource(drink.bottleImage)
            }

            ivDialogDrinkGlass.setImageResource(drink.glassImage)

            tvDialogDrinkSave.setOnClickListener {
                onSaveClicked(bottleCnt, glassCnt)
                dismiss()
            }
            tvDialogDrinkCancel.setOnClickListener {
                onCancelClicked()
                dismiss()
            }
        }
    }

    private fun initListener() {
        binding.ivDialogDrinkBottlePlus.setOnClickListener {
            bottleCnt++
            binding.tvDialogDrinkBottleCount.text = getString(R.string.dialog_drink_bottle_cnt, bottleCnt)
        }
        binding.ivDialogDrinkBottleMinus.setOnClickListener {
            if (bottleCnt > 0) {
                bottleCnt--
                binding.tvDialogDrinkBottleCount.text = getString(R.string.dialog_drink_bottle_cnt, bottleCnt)
            }
        }
        binding.ivDialogDrinkGlassPlus.setOnClickListener {
            glassCnt++
            binding.tvDialogDrinkGlassCount.text = getString(R.string.dialog_drink_glass_cnt, glassCnt)
        }
        binding.ivDialogDrinkGlassMinus.setOnClickListener {
            if (glassCnt > 0) {
                glassCnt--
                binding.tvDialogDrinkGlassCount.text = getString(R.string.dialog_drink_glass_cnt, glassCnt)
            }
        }
    }
}
