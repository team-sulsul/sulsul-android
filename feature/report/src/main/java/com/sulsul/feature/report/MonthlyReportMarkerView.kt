package com.sulsul.feature.report

import android.content.Context
import android.widget.TextView
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF

class MonthlyReportMarkerView(context: Context?, layoutResource: Int) : MarkerView(context, layoutResource) {
    private var tvContent: TextView = findViewById(R.id.tv_item_recent_month_chart_marker)
    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        tvContent.text = e?.y?.toInt().toString()
        super.refreshContent(e, highlight)
    }

    override fun getOffset(): MPPointF? {
        return MPPointF(-(width / 2).toFloat(), -height.toFloat())
    }
}
