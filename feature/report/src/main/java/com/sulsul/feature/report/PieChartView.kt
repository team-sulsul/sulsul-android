package com.sulsul.feature.report

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import timber.log.Timber
import kotlin.math.*

class PieChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var data: List<Float> = listOf()
    private var colors: List<Int> = listOf()

    // 각 영역 사이 흰 선 추가
    private val gapWidth = 2f
    private val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
    }

    // 각 섹션 위에 띄울 하이라이트 원
    private val circlePaint = Paint().apply {
        isAntiAlias = true
        color = Color.BLACK
    }

    // 중앙에 흰 원 배치
    private val centerCirclePaint = Paint().apply {
        isAntiAlias = true
        color = Color.WHITE
    }

    // 하이라이트 원 위에 그릴 40dp 원
    private val additionalCirclePaint = Paint().apply {
        isAntiAlias = true
        color = Color.WHITE  // 원하는 색상으로 변경 가능
    }

    private val density = context.resources.displayMetrics.density

    private val centerCircleRadiusDp = 58f
    private val centerCircleRadiusPx = centerCircleRadiusDp * density

    private val additionalCircleRadiusDp = 26f
    private val additionalCircleRadiusPx = additionalCircleRadiusDp * density

    // 어떤 섹션 위에 원을 표시할지 체크
    private var highlightedIndex: Int? = null

    // 차트에 데이터, 색상 설정
    fun setData(data: List<Float>, colors: List<Int>) {
        // 데이터 내림차순으로 정렬, 색상 정렬
        val sortedPairs = data.zip(colors).sortedByDescending { it.first }
        this.data = sortedPairs.map { it.first }
        this.colors = sortedPairs.map { it.second }
        // 가장 큰 섹션 인덱스를 highlightedIndex로 세팅
        highlightedIndex = this.data.indexOfFirst { it == this.data.maxOrNull() }
        invalidate()  // 뷰를 다시 그려서 업데이트
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.let {
            if (data.isNotEmpty()) {
                val total = data.sum()
                var startAngle = -90f // 차트를 12시 방향에서 시작 (기본은 3시 시작임)

                // circlePaint의 반지름 계산
                val highlightCircleRadius = (min(width, height) / 2 * 0.44f)  // 노란 원의 반지름 비율

                // pie chart의 지름에서 circlePaint의 지름을 빼서 결정
                val diameter = min(width, height).toFloat() - highlightCircleRadius
                val padding = gapWidth * 2  // 흰 부분 padding

                // centerX, centerY를 구해 원을 화면 중앙에 맞추기 위한 좌표 계산
                val centerX = width / 2f
                val centerY = height / 2f

                // 원의 좌상단, 우하단 좌표를 계산하여 가운데 정렬
                val rect = RectF(
                    centerX - diameter / 2 + padding,
                    centerY - diameter / 2 + padding,
                    centerX + diameter / 2 - padding,
                    centerY + diameter / 2 - padding
                )

                // pie chart 그리기
                for (i in data.indices) {
                    val sweepAngle = (data[i] / total) * 360f
                    paint.color = colors.getOrElse(i) { Color.GRAY }

                    // 흰 부분 그리기
                    it.drawArc(rect, startAngle + gapWidth / 2, sweepAngle - gapWidth, true, paint)

                    startAngle += sweepAngle
                }

                // 가운데 흰 원 그리기
                it.drawCircle(rect.centerX(), rect.centerY(), centerCircleRadiusPx, centerCirclePaint)

                // 섹션 위에 띄울 하이라이트 원
                highlightedIndex?.let { index ->
                    drawCircleAtSection(it, rect, index, total)
                }
            }
        }
    }


    // 하이라이트 원 그리기
    private fun drawCircleAtSection(canvas: Canvas, rect: RectF, sectionIndex: Int, total: Float) {
        var startAngle = -90f
        var sweepAngle: Float

        // 섹션의 시작 각도 구하기
        for (i in 0 until sectionIndex) {
            sweepAngle = (data[i] / total) * 360f
            startAngle += sweepAngle
        }

        // 섹션 중앙 각도 구하기
        sweepAngle = (data[sectionIndex] / total) * 360f
        val middleAngle = startAngle + sweepAngle / 2

        // 하이라이트 원 반지름 세팅
        val pieRadius = (rect.width() / 2)
        val circleRadiusOffset = pieRadius * 0.87f  // pie chart의 반지름의 0.9F에 하이라이트 원의 중심 두기

        // 하이라이트 원 중심 좌표 계산
        val centerX = rect.centerX() + circleRadiusOffset * cos(Math.toRadians(middleAngle.toDouble())).toFloat()
        val centerY = rect.centerY() + circleRadiusOffset * sin(Math.toRadians(middleAngle.toDouble())).toFloat()

        // 하이라이트 원 그리기
        canvas.drawCircle(centerX, centerY, pieRadius * 0.44f, circlePaint)

        // 하라이트 원 안에 흰 원
        canvas.drawCircle(centerX, centerY, additionalCircleRadiusPx, additionalCirclePaint)

        val sectionLabel = "소주"  // 해당 섹션의 이름 또는 값
        val amount = "68%"  // 해당 섹션의 이름 또는 값
        Timber.tag("piechartview").d("$centerX, $centerY")
        showTwoTextViewsAtPosition(sectionLabel, amount, centerX, centerY)
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val touchX = event.x
            val touchY = event.y

            val total = data.sum()
            var startAngle = -90f

            // 그릴 영역 정의 (정사각형)
            val diameter = min(width, height).toFloat()
            val padding = gapWidth * 2  // 흰 부분 padding

            // centerX, centerY를 구해 원을 화면 중앙에 맞추기 위한 좌표 계산
            val centerX = width / 2f
            val centerY = height / 2f

            // pie chart의 중심에 맞춰 좌표 계산
            val rect = RectF(
                centerX - diameter / 2 + padding,
                centerY - diameter / 2 + padding,
                centerX + diameter / 2 - padding,
                centerY + diameter / 2 - padding
            )

            // todo : 가장 넓은 부분 터치이벤트가 간헐적으로 안 먹힘
            for (i in data.indices) {
                val sweepAngle = (data[i] / total) * 360f
                val endAngle = startAngle + sweepAngle

                // 터치된 각도 계산
                var angle = Math.toDegrees(atan2((touchY - rect.centerY()).toDouble(), (touchX - rect.centerX()).toDouble())).toFloat()

                if (angle < 0) {
                    angle += 360f
                }

                // 터치가 해당 섹션 내에 있는지 확인
                if (angle in startAngle..endAngle) {
                    // 터치한 영역이 pie chart 반지름 내에 있는지 확인 (pie chart 밖이면 터치로 인식하지 않기 위함)
                    val distanceFromCenter = sqrt((touchX - rect.centerX()).toDouble().pow(2.0) + (touchY - rect.centerY()).toDouble().pow(2.0)).toFloat()
                    if (distanceFromCenter <= rect.width() / 2) {
                        // 하이라이트 섹션 업데이트
                        highlightedIndex = i
                        invalidate() // 뷰를 다시 그려서 하이라이트 원 업데이트
                        return true
                    }
                }

                startAngle += sweepAngle
            }
        }
        return super.onTouchEvent(event)
    }

    // 두 개의 텍스트뷰를 터치한 좌표에 표시하는 메서드
    private fun showTwoTextViewsAtPosition(text1: String, text2: String, x: Float, y: Float) {
        val parentViewGroup = this.parent as ViewGroup  // PieChartView의 부모 ViewGroup 가져오기
        removeChild(parentViewGroup, "highlightText1")
        removeChild(parentViewGroup, "highlightText2")

        // 첫 번째 TextView 추가
        var textView1 = parentViewGroup.findViewWithTag<TextView>("highlightText1")
        if (textView1 == null) {
            textView1 = TextView(context).apply {
                tag = "highlightText1"
                textSize = 12f
                typeface = ResourcesCompat.getFont(context, com.sulsul.core.designsystem.R.font.pretendard_semibold)
                setTextColor(Color.BLACK)
                setBackgroundColor(Color.TRANSPARENT)
            }
            parentViewGroup.addView(textView1)
        }

        // 두 번째 TextView 추가
        var textView2 = parentViewGroup.findViewWithTag<TextView>("highlightText2")
        if (textView2 == null) {
            textView2 = TextView(context).apply {
                tag = "highlightText2"
                textSize = 14f
                typeface = ResourcesCompat.getFont(context, com.sulsul.core.designsystem.R.font.pretendard_semibold)
                setTextColor(Color.BLUE)
                setBackgroundColor(Color.TRANSPARENT)
            }
            parentViewGroup.addView(textView2)
        }

        // 첫 번째 텍스트 설정 및 위치 지정
        textView1.text = text1
        textView1.measure(0, 0)  // TextView의 높이를 측정
        textView1.x = x - textView1.measuredWidth / 2  // 중앙 정렬
        textView1.y = y - textView1.measuredHeight
        Timber.tag("piechartview xy").d("${textView1.x}, ${textView1.y}")
        Timber.tag("piechartview textview").d("${textView1.measuredWidth}, ${textView1.measuredHeight}")
        textView1.visibility = View.VISIBLE

        // 두 번째 텍스트 설정 및 위치 지정 (첫 번째 텍스트뷰 바로 아래)
        textView2.text = text2
        textView2.measure(0, 0)  // TextView의 높이를 측정
        textView2.x = x - textView2.measuredWidth / 2  // 중앙 정렬
        textView2.y = textView1.y + textView1.measuredHeight // 첫 번째 텍스트뷰의 바로 아래에 배치
        textView2.visibility = View.VISIBLE
    }

    private fun removeChild(parentViewGroup: ViewGroup, tag: String) {
        if (parentViewGroup.childCount > 0) {
            for (i in 0 until parentViewGroup.childCount) {
                val child = parentViewGroup.getChildAt(i)
                if (child is TextView && child.tag == tag) {
                    parentViewGroup.removeView(child)
                }
            }
        }
    }
}
