package com.sulsul.feature.calendar.state

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sulsul.core.common.base.BaseFragment
import com.sulsul.feature.calendar.R
import com.sulsul.feature.calendar.databinding.FragmentDrunkenStateBinding
import com.sulsul.feature.calendar.enums.DrunkenStateTheme
import com.sulsul.feature.calendar.utils.formatDateToString
import com.sulsul.feature.calendar.utils.getDrunkenStateTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DrunkenStateFragment : BaseFragment<FragmentDrunkenStateBinding>() {

    private val args: DrunkenStateFragmentArgs by navArgs()
    private val viewModel: DrunkenStateViewModel by viewModels()
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDrunkenStateBinding {
        return FragmentDrunkenStateBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbarTitle()
        initState()
        initListener()
        setDrunkenStateView(getDrunkenStateTheme(args.drinkRecord.drunkennessLevel))
    }

    private fun initState() {
        viewModel.state = args.drinkRecord.drunkennessLevel
    }

    private fun initListener() {
        binding.btnDrunkenStateBack.setOnClickListener {
            Navigation.findNavController(it).navigateUp()
        }

        binding.ivDrunkenStateIcon1.setOnClickListener {
            setDrunkenStateView(DrunkenStateTheme.DRUNKEN_LEVEL_1)
            viewModel.state = DrunkenStateTheme.DRUNKEN_LEVEL_1.name
        }

        binding.ivDrunkenStateIcon2.setOnClickListener {
            setDrunkenStateView(DrunkenStateTheme.DRUNKEN_LEVEL_2)
            viewModel.state = DrunkenStateTheme.DRUNKEN_LEVEL_2.name
        }

        binding.ivDrunkenStateIcon3.setOnClickListener {
            setDrunkenStateView(DrunkenStateTheme.DRUNKEN_LEVEL_3)
            viewModel.state = DrunkenStateTheme.DRUNKEN_LEVEL_3.name
        }

        binding.ivDrunkenStateIcon4.setOnClickListener {
            setDrunkenStateView(DrunkenStateTheme.DRUNKEN_LEVEL_4)
            viewModel.state = DrunkenStateTheme.DRUNKEN_LEVEL_4.name
        }

        binding.ivDrunkenStateIcon5.setOnClickListener {
            setDrunkenStateView(DrunkenStateTheme.DRUNKEN_LEVEL_5)
            viewModel.state = DrunkenStateTheme.DRUNKEN_LEVEL_5.name
        }

        binding.tvDrunkenStateSave.setOnClickListener {
            viewModel.updateStatus(args.drinkRecord.recordedAt, viewModel.state)
            findNavController().popBackStack(R.id.mainFragment, false)
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
    }

    private fun setToolbarTitle() {
        binding.tvDrunkenStateDate.text = formatDateToString(args.drinkRecord.recordedAt)
    }
}
