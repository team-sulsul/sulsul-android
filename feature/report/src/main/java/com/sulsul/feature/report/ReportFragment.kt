package com.sulsul.feature.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sulsul.core.common.base.BaseFragment
import com.sulsul.feature.report.databinding.FragmentReportBinding

class ReportFragment : BaseFragment<FragmentReportBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentReportBinding {
        return FragmentReportBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
