package com.xxx.home.ui.widget

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.orhanobut.logger.Logger
import com.xxx.home.R
import kotlin.properties.Delegates

/**
 * Author: YWX
 * Date: 2021/11/17 9:29
 * Description: 圆形的可以设置颜色的view
 */
class CircleView : View {

    private var width: Float = 0.0F
    private var height: Float = 0.0F

    lateinit var mPaint: Paint

    init {
        initPaint(Color.parseColor("#ABCDEF"))
    }

    private fun initPaint(color: Int) {
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint.color = color
    }

    private var circleColor:Int = Color.parseColor("#4778FF")
    private var borderColor:Int = Color.parseColor("#4778FF")

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        context?.let {
            val ta: TypedArray = it.obtainStyledAttributes(attrs, R.styleable.CircleView)
            circleColor = ta.getColor(R.styleable.CircleView_circleColor, Color.parseColor("#4778FF"))
            borderColor = ta.getColor(R.styleable.CircleView_borderColor, circleColor)
            ta.recycle()
        }

        initPaint(circleColor)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawCircle(width / 2.0f, height / 2.0f, width.coerceAtMost(height) / 2.0F, mPaint.apply {
            color = borderColor
        })
        canvas?.drawCircle(width / 2.0f, height / 2.0f, width.coerceAtMost(height) * 0.85F / 2.0F, mPaint.apply {
            color = circleColor
        })
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        width = MeasureSpec.getSize(widthMeasureSpec).toFloat()
        height = MeasureSpec.getSize(heightMeasureSpec).toFloat()
    }

    fun setCircleColor(color: Int?, borderColor: Int? = null) {
        color?.let {
            this.circleColor = color
            this.borderColor = borderColor ?: circleColor
            initPaint(color)
            invalidate()
        }
    }
}