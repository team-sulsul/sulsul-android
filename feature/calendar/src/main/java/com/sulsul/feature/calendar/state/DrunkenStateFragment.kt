package com.sulsul.feature.calendar.state

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.sulsul.core.common.base.BaseFragment
import com.sulsul.feature.calendar.databinding.FragmentDrunkenStateBinding
import com.sulsul.feature.calendar.enums.DrunkenStateTheme
import com.sulsul.feature.calendar.utils.formatDateToString
import com.sulsul.feature.calendar.utils.getDrunkenStateTheme

class DrunkenStateFragment : BaseFragment<FragmentDrunkenStateBinding>() {

    private val args: DrunkenStateFragmentArgs by navArgs()
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDrunkenStateBinding {
        return FragmentDrunkenStateBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbarTitle()
        initListener()
        setDrunkenStateView(getDrunkenStateTheme(args.drinkRecord.drunkennessLevel))
    }

    private fun initListener() {
        binding.btnDrunkenStateBack.setOnClickListener {
            Navigation.findNavController(it).navigateUp()
        }

        binding.ivDrunkenStateIcon1.setOnClickListener {
            setDrunkenStateView(DrunkenStateTheme.DRUNKEN_LEVEL_1)
        }

        binding.ivDrunkenStateIcon2.setOnClickListener {
            setDrunkenStateView(DrunkenStateTheme.DRUNKEN_LEVEL_2)
        }

        binding.ivDrunkenStateIcon3.setOnClickListener {
            setDrunkenStateView(DrunkenStateTheme.DRUNKEN_LEVEL_3)
        }

        binding.ivDrunkenStateIcon4.setOnClickListener {
            setDrunkenStateView(DrunkenStateTheme.DRUNKEN_LEVEL_4)
        }

        binding.ivDrunkenStateIcon5.setOnClickListener {
            setDrunkenStateView(DrunkenStateTheme.DRUNKEN_LEVEL_5)
        }
    }

    private fun setDrunkenStateView(value: DrunkenStateTheme) {
        with(binding) {
            val bubbleViews = listOf(
                ivDrunkenStateBubble1,
                ivDrunkenStateBubble2,
                ivDrunkenStateBubble3,
                ivDrunkenStateBubble4,
                ivDrunkenStateBubble5
            )

            bubbleViews.forEachIndexed { index, bubbleView ->
                bubbleView.visibility = if (index == value.ordinal - 1) View.VISIBLE else View.INVISIBLE
            }

            ivDrunkenStateWhale.setImageResource(value.whale)
        }
//        when (value) {
//            DrunkenStateTheme.DRUNKEN_LEVEL_1 -> {
//                with(binding) {
//                    ivDrunkenStateBubble1.visibility = View.VISIBLE
//                    ivDrunkenStateBubble2.visibility = View.INVISIBLE
//                    ivDrunkenStateBubble3.visibility = View.INVISIBLE
//                    ivDrunkenStateBubble4.visibility = View.INVISIBLE
//                    ivDrunkenStateBubble5.visibility = View.INVISIBLE
//
//                    ivDrunkenStateWhale.setImageResource(value.whale)
//                }
//            }
//            DrunkenStateTheme.DRUNKEN_LEVEL_2 -> {
//                with(binding) {
//                    ivDrunkenStateBubble1.visibility = View.INVISIBLE
//                    ivDrunkenStateBubble2.visibility = View.VISIBLE
//                    ivDrunkenStateBubble3.visibility = View.INVISIBLE
//                    ivDrunkenStateBubble4.visibility = View.INVISIBLE
//                    ivDrunkenStateBubble5.visibility = View.INVISIBLE
//
//                    ivDrunkenStateWhale.setImageResource(value.whale)
//                }
//            }
//            DrunkenStateTheme.DRUNKEN_LEVEL_3 -> {
//                with(binding) {
//                    ivDrunkenStateBubble1.visibility = View.INVISIBLE
//                    ivDrunkenStateBubble2.visibility = View.INVISIBLE
//                    ivDrunkenStateBubble3.visibility = View.VISIBLE
//                    ivDrunkenStateBubble4.visibility = View.INVISIBLE
//                    ivDrunkenStateBubble5.visibility = View.INVISIBLE
//
//                    ivDrunkenStateWhale.setImageResource(value.whale)
//                }
//            }
//            DrunkenStateTheme.DRUNKEN_LEVEL_4 -> {
//                with(binding) {
//                    ivDrunkenStateBubble1.visibility = View.INVISIBLE
//                    ivDrunkenStateBubble2.visibility = View.INVISIBLE
//                    ivDrunkenStateBubble3.visibility = View.INVISIBLE
//                    ivDrunkenStateBubble4.visibility = View.VISIBLE
//                    ivDrunkenStateBubble5.visibility = View.INVISIBLE
//
//                    ivDrunkenStateWhale.setImageResource(value.whale)
//                }
//            }
//            DrunkenStateTheme.DRUNKEN_LEVEL_5 -> {
//                with(binding) {
//                    ivDrunkenStateBubble1.visibility = View.INVISIBLE
//                    ivDrunkenStateBubble2.visibility = View.INVISIBLE
//                    ivDrunkenStateBubble3.visibility = View.INVISIBLE
//                    ivDrunkenStateBubble4.visibility = View.INVISIBLE
//                    ivDrunkenStateBubble5.visibility = View.VISIBLE
//
//                    ivDrunkenStateWhale.setImageResource(value.whale)
//                }
//            }
//            else -> {
//                with(binding) {
//                    ivDrunkenStateBubble1.visibility = View.INVISIBLE
//                    ivDrunkenStateBubble2.visibility = View.INVISIBLE
//                    ivDrunkenStateBubble3.visibility = View.INVISIBLE
//                    ivDrunkenStateBubble4.visibility = View.INVISIBLE
//                    ivDrunkenStateBubble5.visibility = View.INVISIBLE
//
//                    ivDrunkenStateWhale.setImageResource(DrunkenStateTheme.DRUNKEN_DEFAULT.whale)
//                }
//            }
//        }
    }

    private fun setToolbarTitle() {
        binding.tvDrunkenStateDate.text = formatDateToString(args.drinkRecord.recordedAt)
    }
}
