package com.sulsul.feature.report

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.sulsul.core.common.base.BaseFragment
import com.sulsul.core.data.remote.model.response.MonthlyDrunkenState
import com.sulsul.feature.report.databinding.FragmentReportBinding
import com.sulsul.feature.report.viewModel.ReportViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.LocalDate
import java.time.Month
import java.time.temporal.ChronoUnit

@AndroidEntryPoint
class ReportFragment : BaseFragment<FragmentReportBinding>() {

    private val reportViewModel: ReportViewModel by viewModels()

    private var dataList = arrayListOf(3, 5, 10) // 통신 실패를 위해 디폴트 데이터 설정
    private val entryList = arrayListOf<Entry>()
    private lateinit var curDate: LocalDate
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentReportBinding {
        return FragmentReportBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val localDate = LocalDate.now().withDayOfMonth(1) //29일 이후로 없는 달도 있기 때문에 이번 달의 1일로 설정
        getReport(localDate)
        observeReportInfo()

        initLayout(localDate)
        initClickListener()
        initLineChart()
        initLineChartMarker()
    }

    private fun initClickListener() {
        binding.apply {
            // 겁나 빨리 화살표 누르면 문제 없나..? 글고 잘...통신되나..?
            ivReportArrowLeft.setOnClickListener{
                getReport(makeDate(false))
            }
            ivReportArrowRight.setOnClickListener {
                getReport(makeDate(true))
            }
            layoutReportNoData.btnItemReportEmpty.setOnClickListener {
                val request = NavDeepLinkRequest.Builder
                    .fromUri("android-app://com.teamsulsul.sulsul/mainFragment".toUri())
                    .build()
                findNavController().navigate(request)
            }
        }
    }

    private fun initLayout(date: LocalDate) {
        setDateTitleText(date)
        setSummaryText("유저123")
        setDrinkDifferenceText()
    }

    private fun observeReportInfo() {
        lifecycleScope.launch {
            reportViewModel.reportInfo.collect { state ->
                when (state) {
                    is ReportState.Initial -> {}
                    is ReportState.Loading -> {
                        emptyViewVisible(true)
                    }
                    is ReportState.Failure -> {
                        emptyViewVisible(true)
                    }
                    is ReportState.Success -> {
                        if (state.data.monthlyDrinkData == null || state.data.monthlyDrunkenState == null) { // 기록된 술 데이터 없음
                            emptyViewVisible(true)
                        } else {
                            emptyViewVisible(false)
                            dataList.clear()
                            state.data.recentThreeMonthDrinks.forEach { dataList.add(it.times) }
                            binding.apply {
                                // 이달의 통계 요약
                                // Todo : 랜덤닉네임 response내려오도록 수정되면 유저 닉네임으로 바꾸기
                                // Todo : 술, 상태 최댓값 넣기
                                setSummaryText("유저123")

                                // 최근 3개월 음주 빈도
                                setDrinkDifferenceText()

                                // 이달의 컨디션
                                setDrunkenState(state.data.monthlyDrunkenState!!)
                            }
                        }
                    }
                }
            }
        }
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

            axisLeft.axisMinimum = 0F // y값 최솟값
            axisLeft.axisMaximum = dataList.max().toFloat() + 3.0F // 값 최댓값

            // x값 grid 설정
            xAxis.setDrawGridLines(true)
            xAxis.gridLineWidth = 0.7F
            xAxis.gridColor = ContextCompat.getColor(requireContext(), com.sulsul.core.designsystem.R.color.gray_100)

            // x축 설정
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.labelCount = dataList.size - 1
            xAxis.valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    var curMonth = LocalDate.now().monthValue
                    curMonth = 1
                    var startMonth = curMonth - 2
                    if (curMonth <= 2) startMonth += 12
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

                setDrawValues(false)
                setDrawVerticalHighlightIndicator(false)
                setDrawHorizontalHighlightIndicator(false)
            }
            setDrawMarkers(true)

            data = LineData(listOf(lineDataSet))
            invalidate()
        }
    }

    private fun initLineChartMarker() {
        val marker = MonthlyReportMarkerView(requireContext(), R.layout.item_recent_month_chart_marker)
        binding.lineChartReport.marker = marker

        val lastEntry: Entry = entryList[entryList.size - 1]
        val highlight = Highlight(lastEntry.x, lastEntry.y, 0)
        highlight.dataIndex = 0
        binding.lineChartReport.highlightValue(highlight)
    }

    private fun emptyViewVisible(visible: Boolean) {
        if (visible) {
            binding.viewReportEmptyBlur.visibility = VISIBLE
            binding.layoutReportNoData.root.visibility = VISIBLE
        } else {
            binding.viewReportEmptyBlur.visibility = GONE
            binding.layoutReportNoData.root.visibility = GONE
        }
    }

    private fun getDrunkenStatePercentage(drunkenData: MonthlyDrunkenState, stateValue: Int): Int {
        val totalDrunkenState = drunkenData.apply {
            drunkenLevel1Count + drunkenLevel2Count + drunkenLevel3Count + drunkenLevel4Count + drunkenLevel5Count
        }.toString().toInt()
        return stateValue / totalDrunkenState
    }

    private fun setDrinkDifferenceText() {
        val drinkDifference = dataList[dataList.size - 1] - dataList[dataList.size - 2]
        val differenceString = if (drinkDifference > 0) { "더" } else { "덜" }
        binding.tvReportRecentMonthSummaryAmount.text = Html.fromHtml(getString(R.string.report_recent_month_amount, drinkDifference, differenceString))
    }

    private fun setDrunkenState(monthlyDrunkenState: MonthlyDrunkenState) {
        binding.apply {
            layoutReportDrunkenStateBar.tvReportDrunkenState1Value.text = monthlyDrunkenState!!.drunkenLevel1Count.toString()
            layoutReportDrunkenStateBar.tvReportDrunkenState2Value.text = monthlyDrunkenState.drunkenLevel2Count.toString()
            layoutReportDrunkenStateBar.tvReportDrunkenState3Value.text = monthlyDrunkenState.drunkenLevel3Count.toString()
            layoutReportDrunkenStateBar.tvReportDrunkenState4Value.text = monthlyDrunkenState.drunkenLevel4Count.toString()
            layoutReportDrunkenStateBar.tvReportDrunkenState5Value.text = monthlyDrunkenState.drunkenLevel5Count.toString()

            layoutReportDrunkenStateBar.pbReportDrunkenState1.progress =
                getDrunkenStatePercentage(monthlyDrunkenState, monthlyDrunkenState.drunkenLevel1Count)
            layoutReportDrunkenStateBar.pbReportDrunkenState2.progress =
                getDrunkenStatePercentage(monthlyDrunkenState, monthlyDrunkenState.drunkenLevel2Count)
            layoutReportDrunkenStateBar.pbReportDrunkenState3.progress =
                getDrunkenStatePercentage(monthlyDrunkenState, monthlyDrunkenState.drunkenLevel3Count)
            layoutReportDrunkenStateBar.pbReportDrunkenState4.progress =
                getDrunkenStatePercentage(monthlyDrunkenState, monthlyDrunkenState.drunkenLevel4Count)
            layoutReportDrunkenStateBar.pbReportDrunkenState5.progress =
                getDrunkenStatePercentage(monthlyDrunkenState, monthlyDrunkenState.drunkenLevel5Count)
        }
    }

    private fun getReport(date: LocalDate) {
        curDate = date
        Timber.tag("request Date").d(curDate.toString())
        reportViewModel.getReport(curDate.toString())
    }

    private fun makeDate(isNext: Boolean): LocalDate {
        val date = if (isNext) {
            curDate.plus(1, ChronoUnit.MONTHS)
        } else {
            curDate.minus(1, ChronoUnit.MONTHS)
        }
        setDateTitleText(date)
        return date
    }

    private fun setDateTitleText(date: LocalDate) {
        binding.tvReportTitle.text = getString(R.string.report_title, date.year, Month.valueOf(date.month.toString()).value)
    }

    private fun setSummaryText(userName: String) {
        binding.apply {
            tvReportSummaryUser.text = getString(R.string.report_summary_user, userName)
        }
    }
}
