package com.sulsul.core.designsystem.view.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.sulsul.core.designsystem.databinding.DialogOneButtonBinding

class OneButtonDialog(
    private val title: String,
    private val subtitle: String,
    private val button: String,
    private val onButtonClicked: () -> Unit,
) : DialogFragment() {

    private lateinit var binding: DialogOneButtonBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DialogOneButtonBinding.inflate(inflater, container, false)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        init()

        return binding.root
    }

    private fun init() {
        with(binding) {
            tvDialog1Title.text = title
            tvDialog1Subtitle.text = subtitle
            btnDialog1.text = button
            btnDialog1.setOnClickListener {
                onButtonClicked()
                dismiss()
            }
        }
    }
}
