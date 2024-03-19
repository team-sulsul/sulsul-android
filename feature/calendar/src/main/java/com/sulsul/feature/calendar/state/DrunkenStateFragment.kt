package com.sulsul.feature.calendar.state

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.sulsul.core.common.base.BaseFragment
import com.sulsul.feature.calendar.R
import com.sulsul.feature.calendar.databinding.FragmentDrunkenStateBinding

class DrunkenStateFragment : BaseFragment<FragmentDrunkenStateBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDrunkenStateBinding {
        return FragmentDrunkenStateBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvDrunkenStateDate.text = "2024년 3월 4일"

        initListener()
    }

    private fun initListener() {
        binding.btnDrunkenStateBack.setOnClickListener {
            Navigation.findNavController(it).navigateUp()
        }

        binding.ivDrunkenStateIcon1.setOnClickListener {
            with(binding) {
                ivDrunkenStateBubble1.visibility = View.VISIBLE
                ivDrunkenStateBubble2.visibility = View.INVISIBLE
                ivDrunkenStateBubble3.visibility = View.INVISIBLE
                ivDrunkenStateBubble4.visibility = View.INVISIBLE
                ivDrunkenStateBubble5.visibility = View.INVISIBLE

                ivDrunkenStateWhale.setImageResource(DrunkenState.DRUNKEN_LEVEL_1.whale)
            }
        }

        binding.ivDrunkenStateIcon2.setOnClickListener {
            with(binding) {
                ivDrunkenStateBubble1.visibility = View.INVISIBLE
                ivDrunkenStateBubble2.visibility = View.VISIBLE
                ivDrunkenStateBubble3.visibility = View.INVISIBLE
                ivDrunkenStateBubble4.visibility = View.INVISIBLE
                ivDrunkenStateBubble5.visibility = View.INVISIBLE

                ivDrunkenStateWhale.setImageResource(DrunkenState.DRUNKEN_LEVEL_2.whale)
            }
        }

        binding.ivDrunkenStateIcon3.setOnClickListener {
            with(binding) {
                ivDrunkenStateBubble1.visibility = View.INVISIBLE
                ivDrunkenStateBubble2.visibility = View.INVISIBLE
                ivDrunkenStateBubble3.visibility = View.VISIBLE
                ivDrunkenStateBubble4.visibility = View.INVISIBLE
                ivDrunkenStateBubble5.visibility = View.INVISIBLE

                ivDrunkenStateWhale.setImageResource(DrunkenState.DRUNKEN_LEVEL_3.whale)
            }
        }

        binding.ivDrunkenStateIcon4.setOnClickListener {
            with(binding) {
                ivDrunkenStateBubble1.visibility = View.INVISIBLE
                ivDrunkenStateBubble2.visibility = View.INVISIBLE
                ivDrunkenStateBubble3.visibility = View.INVISIBLE
                ivDrunkenStateBubble4.visibility = View.VISIBLE
                ivDrunkenStateBubble5.visibility = View.INVISIBLE

                ivDrunkenStateWhale.setImageResource(DrunkenState.DRUNKEN_LEVEL_4.whale)
            }
        }

        binding.ivDrunkenStateIcon5.setOnClickListener {
            with(binding) {
                ivDrunkenStateBubble1.visibility = View.INVISIBLE
                ivDrunkenStateBubble2.visibility = View.INVISIBLE
                ivDrunkenStateBubble3.visibility = View.INVISIBLE
                ivDrunkenStateBubble4.visibility = View.INVISIBLE
                ivDrunkenStateBubble5.visibility = View.VISIBLE

                ivDrunkenStateWhale.setImageResource(DrunkenState.DRUNKEN_LEVEL_5.whale)
            }
        }
    }
}
