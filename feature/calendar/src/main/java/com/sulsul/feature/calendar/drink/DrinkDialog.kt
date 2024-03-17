package com.sulsul.feature.calendar.drink

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.sulsul.feature.calendar.databinding.DialogDrinkBinding

class DrinkDialog(
    private val drink: Drink,
    private val onCancelClicked: () -> Unit,
    private val onSaveClicked: () -> Unit
) : DialogFragment() {

    private lateinit var binding:DialogDrinkBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DialogDrinkBinding.inflate(inflater, container, false)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)

        init()

        return binding.root
    }

    private fun init() {
        with(binding) {
            tvDialogDrinkTitle.text = drink.title
            context?.let { tvDialogDrinkTitle.setTextColor(it.getColor(drink.mainColor)) }

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
                onSaveClicked()
                dismiss()
            }
            tvDialogDrinkCancel.setOnClickListener {
                onCancelClicked()
                dismiss()
            }
        }
    }
}
