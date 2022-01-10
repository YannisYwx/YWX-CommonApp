package com.ywx.common.widget

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.ywx.common.R
import com.ywx.common.ext.logE
import com.ywx.common.ext.view.correctTextYCoordinates
import kotlin.math.abs


/**
 * Author: YWX
 * Date: 2021/12/29 15:07
 * Description: 滑动选择控件
 */
class Slided2SelectedView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {

    companion object {
        /**
         * 圆角的缩放大小 相对于View的高度
         */
        const val CORNER_RADIUS_SCALE_H = 8.0f / 36.0f

        /**
         * 字体大小相对于控件高度的比例
         */
        const val TEXT_SIZE_SCALE_H = 15.0f / 36.0f

        /**
         * 滑块相对于控件高度的比例
         */
        const val SLIDED_SQUARE_HEIGHT_SCALE_H = 28.0F / 36.0F

        /**
         * 滑块相对于所属空间的宽度比例
         */
        const val SLIDED_SQUARE_WIDTH_SCALE_SPACE = 0.9F

        const val ANIMATION_DURATION = 300L

        val DEFAULT_ARRAY = arrayOf("On", "Off")
    }

    private var w: Int = 0
    private var h: Int = 0

    /**
     * 背景圆角半径
     */
    private var cornerRadius = 0

    /**
     * 选中的位置
     */
    private var selectIndex: Int = 0

    /**
     * 一个元素占用的宽度空间
     */
    private var space = 0.0F

    /**
     * 文字大小
     */
    private var labelSize: Float = 15.0F

    /**
     * 滑块宽度
     */
    private var slideSquareW = 0.0F

    /**
     * 滑块高度
     */
    private var slideSquareH = 0.0F

    /**
     * 滑块的中心x轴坐标
     */
    private var slidedSquareCenterX = 0.0F
    private var leftText: String? = null
    private var rightText: String? = null
    private var centerText: String? = null
    private var bgColor: Int = 0
    private var slidedSquareColor: Int = 0
    private var selectTextColor: Int = 0
    private var unselectTextColor: Int = 0

    private lateinit var mRectBg: RectF
    private lateinit var mPaint: Paint

    private lateinit var mTags: Array<String>

    init {
        initAttrs(attrs = attrs)
        initData()
    }

    private fun initAttrs(attrs: AttributeSet?) {
        attrs?.let {
            val array = context.obtainStyledAttributes(it, R.styleable.Slided2SelectedView)
            selectIndex = array.getInt(R.styleable.Slided2SelectedView_selectedIndex, 0)
            leftText = array.getString(R.styleable.Slided2SelectedView_leftText)
            rightText = array.getString(R.styleable.Slided2SelectedView_rightText)
            centerText = array.getString(R.styleable.Slided2SelectedView_centerText)
            bgColor = array.getColor(R.styleable.Slided2SelectedView_bgColor, Color.parseColor("#F3F7FE"))
            slidedSquareColor = array.getColor(R.styleable.Slided2SelectedView_slidedSquareColor, Color.WHITE)
            selectTextColor = array.getColor(R.styleable.Slided2SelectedView_selectTextColor, Color.BLACK)
            unselectTextColor = array.getColor(R.styleable.Slided2SelectedView_unselectTextColor, Color.BLACK)
            array.recycle()
        }
    }

    /**
     * 初始化数据
     *
     */
    private fun initData() {
        cornerRadius = (this.h * CORNER_RADIUS_SCALE_H).toInt()
        labelSize = this.h * TEXT_SIZE_SCALE_H
        mPaint = Paint().apply {
            this.textSize = labelSize
            this.style = Paint.Style.FILL
            this.color = Color.LTGRAY
            this.isAntiAlias = true
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        this.w = w
        this.h = h
        initViewParameter()
    }

    private fun initViewParameter(tagArray: Array<String>? = null) {
        this.cornerRadius = (this.h * CORNER_RADIUS_SCALE_H).toInt()
        this.labelSize = this.h * TEXT_SIZE_SCALE_H
        this.mTags = tagArray ?: getLabelTags()
        this.space = (this.w / mTags.size).toFloat()
        this.slidedSquareCenterX = space / 2.0F + selectIndex * space * 2F
        this.slideSquareH = h * SLIDED_SQUARE_HEIGHT_SCALE_H
        this.slideSquareW = space * SLIDED_SQUARE_WIDTH_SCALE_SPACE
        this.mPaint = Paint().apply {
            this.textSize = labelSize
            this.style = Paint.Style.FILL
            this.color = Color.LTGRAY
            this.isAntiAlias = true
        }
    }

    private fun createSlidedSquareRect(centerX: Float): RectF {
        val left = centerX - slideSquareW / 2.0F
        val top = (this.h - slideSquareH) / 2.0f
        val right = left + slideSquareW
        val bottom = top + slideSquareH
        return RectF(
                left,
                top,
                right,
                bottom
        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            drawBg(it)
            drawSlidedSquare(it)
            drawText(it)
        }
    }

    private fun drawBg(canvas: Canvas) {
        mRectBg = RectF(
                0.0f,
                0.0f,
                w.toFloat(),
                h.toFloat()
        )
        mPaint.apply {
            style = Paint.Style.FILL
            color = bgColor
        }
        canvas.drawRoundRect(mRectBg, cornerRadius.toFloat(), cornerRadius.toFloat(), mPaint)
    }

    private fun drawSlidedSquare(canvas: Canvas) {
        mPaint.apply {
            color = slidedSquareColor
            style = Paint.Style.FILL
        }
        mRectBg = createSlidedSquareRect(slidedSquareCenterX)
        canvas.drawRoundRect(mRectBg, cornerRadius.toFloat(), cornerRadius.toFloat(), mPaint)
        mPaint.apply {
            color = Color.parseColor("#33222222")
            style = Paint.Style.STROKE
            strokeWidth = slideSquareH / 112.0f
        }
        canvas.drawRoundRect(mRectBg, cornerRadius.toFloat(), cornerRadius.toFloat(), mPaint)
    }


    private fun drawText(canvas: Canvas) {
        mTags.let {
            mPaint.apply {
                this.textSize = labelSize
                this.color = unselectTextColor
                this.textAlign = Paint.Align.CENTER
                this.style = Paint.Style.FILL
            }
            val yC = correctTextYCoordinates(this.h / 2.0f, mPaint)
            for (i in it.indices) {
                mPaint.color = if (i == selectIndex) selectTextColor else unselectTextColor
                canvas.drawText(it[i], (space / 2.0F + i * space), yC, mPaint)
            }
        }
    }

    fun setLabelTags(tagArray: Array<String>? = null, defaultSelectIndex: Int = 0) {
        selectIndex = defaultSelectIndex
        initViewParameter(tagArray)
        invalidate()
    }

    private fun getLabelTags(tagArray: Array<String>? = null) =
            when {
                !tagArray.isNullOrEmpty() -> {
                    mTags = tagArray
                    tagArray
                }
                leftText.isNullOrEmpty() -> {
                    DEFAULT_ARRAY
                }
                (!leftText.isNullOrEmpty() && !rightText.isNullOrEmpty() && centerText.isNullOrEmpty()) -> {
                    arrayOf(leftText!!, rightText!!)
                }
                (!leftText.isNullOrEmpty() && !rightText.isNullOrEmpty() && !centerText.isNullOrEmpty()) -> {
                    arrayOf(leftText!!, centerText!!, rightText!!)
                }
                else -> DEFAULT_ARRAY
            }

    fun slidedSquare(from: Int, to: Int) {
        val animationDuration = /*abs(from - to) **/ ANIMATION_DURATION
        val fromX = indexToX(from)
        val toX = indexToX(to)
        ObjectAnimator.ofFloat(this, "slideSquareCenterX", fromX, toX).setDuration(animationDuration).start()
    }

    private fun indexToX(index: Int) = space / 2.0f + index * space

    fun setSlideSquareCenterX(centerX: Float) {
        this.slidedSquareCenterX = centerX
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let {
            when (it.action) {
                MotionEvent.ACTION_DOWN -> {

                }
                MotionEvent.ACTION_MOVE -> {

                }
                MotionEvent.ACTION_UP -> {
                    val currentTouchIndex = getIndexByXY(it.x, it.y)
                    if (currentTouchIndex != selectIndex) {
                        slidedSquare(selectIndex, currentTouchIndex)
                        selectIndex = currentTouchIndex
                    }
                }
            }
        }
        return true
    }

    private fun getIndexByXY(x: Float, y: Float): Int {
        if (y >= (this.h - slideSquareH) / 2.0f && y <= h / 2.0F + slideSquareH / 2.0F) {
            for (i in mTags.indices) {
                val left = space * i + (space - slideSquareW) / 2.0F
                val right = left + slideSquareW
                if (x in left..right) {
                    return i
                }
            }
        }
        return selectIndex
    }

}