package com.sulsul.core.designsystem.view.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.sulsul.core.designsystem.databinding.DialogTwoButtonBinding

class TwoButtonDialog(
    private val title: String,
    private val subtitle: String,
    private val leftButton: String,
    private val rightButton: String,
    private val onLeftButtonClicked: () -> Unit,
    private val onRightButtonClicked: () -> Unit,
) : DialogFragment() {

    private lateinit var binding: DialogTwoButtonBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DialogTwoButtonBinding.inflate(inflater, container, false)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        init()

        return binding.root
    }

    private fun init() {
        with(binding) {
            tvDialog2Title.text = title
            tvDialog2Subtitle.text = subtitle
            btnDialog2Left.text = leftButton
            btnDialog2Right.text = rightButton
            btnDialog2Left.setOnClickListener {
                onLeftButtonClicked()
                dismiss()
            }
            btnDialog2Right.setOnClickListener {
                onRightButtonClicked()
                dismiss()
            }
        }
    }
}
