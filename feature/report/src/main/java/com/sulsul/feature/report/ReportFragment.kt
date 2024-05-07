package com.sulsul.feature.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.DefaultValueFormatter
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.sulsul.core.common.base.BaseFragment
import com.sulsul.feature.report.databinding.FragmentReportBinding
import java.time.LocalDate

class ReportFragment : BaseFragment<FragmentReportBinding>() {

    private val dataList = arrayListOf(
        3, 5, 10
    )

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentReportBinding {
        return FragmentReportBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLineChart()
        initLineChartMarker()
    }

    private fun initLineChart() {
        binding.lineChartReport.apply {
            // zoom disabled
            setPinchZoom(false)
            setScaleEnabled(false)
            isDoubleTapToZoomEnabled = false

            axisRight.isEnabled = false
            axisLeft.isEnabled = false
            legend.isEnabled = false
            xAxis.isEnabled = true
            extraTopOffset = 10F
            description.isEnabled = false
//            isHighlightPerDragEnabled = false
//            isHighlightPerTapEnabled = false

            // x값 grid 설정
            xAxis.setDrawGridLines(true)
            xAxis.gridLineWidth = 0.7F
            xAxis.gridColor = ContextCompat.getColor(requireContext(), com.sulsul.core.designsystem.R.color.gray_100)

            // x축 설정
            axisLeft.axisMinimum = 0F
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.labelCount = dataList.size - 1
            xAxis.valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    var curMonth = LocalDate.now().monthValue
                    curMonth = 1
                    var startMonth = curMonth - 2
                    if (curMonth <= 2) {
                       startMonth += 12
                    }
                    // Todo : 월 수정 필요
                    return (startMonth + value).toInt().toString() + "월"
                }
            }
            xAxis.textColor = ContextCompat.getColor(requireContext(), com.sulsul.core.designsystem.R.color.gray_400)
            xAxis.textSize = 11.0F
            xAxis.axisLineColor = ContextCompat.getColor(requireContext(), com.sulsul.core.designsystem.R.color.gray_100)
            xAxis.axisLineWidth = 1.2F
            extraBottomOffset = 10F
            xAxis.setDrawLabels(true)

            // 데이터 line
            val entryList = arrayListOf<Entry>()
            dataList.forEachIndexed { index, d ->
                entryList.add(Entry(index.toFloat(), d.toFloat()))
            }
            val lineDataSet = LineDataSet(entryList, "data").apply {
                // 원 설정
                circleRadius = 6.5F
                circleHoleRadius = 4.0F
                setCircleColor(ContextCompat.getColor(requireContext(), com.sulsul.core.designsystem.R.color.blue_200))
                circleHoleColor = ContextCompat.getColor(requireContext(), com.sulsul.core.designsystem.R.color.white)

                valueTextSize = 11.0F
                valueTextColor = ContextCompat.getColor(requireContext(), com.sulsul.core.designsystem.R.color.blue_200)

                lineWidth = 2.5F
                color = ContextCompat.getColor(requireContext(), com.sulsul.core.designsystem.R.color.blue_200)
            }
            lineDataSet.valueFormatter = DefaultValueFormatter(0)
            data = LineData(listOf(lineDataSet))
            invalidate()
        }
    }

    private fun initLineChartMarker() {
        val marker = RecentMonthMarker(requireContext(), R.layout.item_recent_month_chart_marker)
        binding.lineChartReport.marker = marker
    }
}
