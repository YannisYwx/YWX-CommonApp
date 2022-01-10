package com.ywx.common.ext.view

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.view.View
import android.widget.ImageView
import androidx.annotation.IdRes
import androidx.core.view.isVisible
import com.blankj.utilcode.util.AdaptScreenUtils
import com.blankj.utilcode.util.ClickUtils
import com.chad.library.adapter.base.BaseViewHolder

/**
 * Author: YWX
 * Date: 2021/9/8 13:48
 * Description: View的扩展函数
 */

/**
 * 设置view不占位
 */
fun View.gone() {
    visibility = View.GONE
}


/**
 * 设置view显示
 */
fun View.visible() {
    visibility = View.VISIBLE
}


/**
 * 设置view占位隐藏
 */
fun View.invisible() {
    visibility = View.INVISIBLE
}

/**
 * 根据条件设置view显示隐藏 为true 显示，为false 隐藏
 */
fun View.visibleOrGone(flag: Boolean) {
    visibility = if (flag) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

fun View.visibleOrGone() {
    visibility = if (!isVisible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

/**
 * 根据条件设置view显示隐藏 为true 显示，为false 隐藏
 */
fun View.visibleOrInvisible(flag: Boolean) {
    visibility = if (flag) {
        View.VISIBLE
    } else {
        View.INVISIBLE
    }
}

fun View.visibleOrGoneWithAnimation(){
    if (!isVisible) {
        showWithAnimation()
    } else {
        dismissWithAnimation()
    }
}

/**
 * View的消失动画
 *
 * @param duration
 */
fun View.dismissWithAnimation1(duration: Long = 300L) {
    val h = AdaptScreenUtils.pt2Px(423.36f)
    apply {
        animate().translationY(-h.toFloat()).alpha(0.0f)
                .setDuration(duration)
                .withStartAction {
                    this.visible()
                }
                .withEndAction {
                    this.visible()
                }.start()
    }
}

/**
 * View的消失动画
 *
 * @param duration
 */
fun View.dismissWithAnimation(duration: Long = 300L) {
    apply {
        animate().alpha(0.0f)
                .setDuration(duration)
                .withStartAction {
                    this.alpha = 1.0F
                    this.visible()
                }
                .withEndAction {
                    this.gone()
                }.start()
    }
}

/**
 * View的显示动画
 *
 * @param duration
 */
fun View.showWithAnimation(duration: Long = 300L) {
    apply {
        animate().alphaBy(1.0f)
                .setDuration(duration)
                .withEndAction {
                    this.visible()
                }.withStartAction {
                    this.alpha = 0.0f
                    this.visible()
                }
                .start()
    }
}

/**
 * 将view转为bitmap
 */
@Deprecated("use View.drawToBitmap()")
fun View.toBitmap(scale: Float = 1f, config: Bitmap.Config = Bitmap.Config.ARGB_8888): Bitmap? {
    if (this is ImageView) {
        if (drawable is BitmapDrawable) return (drawable as BitmapDrawable).bitmap
    }
    this.clearFocus()
    val bitmap = createBitmapSafely(
            (width * scale).toInt(),
            (height * scale).toInt(),
            config,
            1
    )
    if (bitmap != null) {
        Canvas().run {
            setBitmap(bitmap)
            save()
            drawColor(Color.WHITE)
            scale(scale, scale)
            this@toBitmap.draw(this)
            restore()
            setBitmap(null)
        }
    }
    return bitmap
}

fun createBitmapSafely(width: Int, height: Int, config: Bitmap.Config, retryCount: Int): Bitmap? {
    try {
        return Bitmap.createBitmap(width, height, config)
    } catch (e: OutOfMemoryError) {
        e.printStackTrace()
        if (retryCount > 0) {
            System.gc()
            return createBitmapSafely(width, height, config, retryCount - 1)
        }
        return null
    }
}

/**
 * 矫正text的y轴位置
 *
 * @param centerY
 * @param mPaint
 * @return
 */
fun View.correctTextYCoordinates(centerY: Float, mPaint: Paint): Float {
    return centerY - (mPaint.ascent() + mPaint.descent()) / 2.0f
}

/**
 * 防止重复点击事件 默认0.5秒内不可重复点击
 * @param interval 时间间隔 默认0.5秒
 * @param action 执行方法
 */
var lastClickTime = 0L
fun View.clickNoRepeat(interval: Long = 500, action: (view: View) -> Unit) {
    setOnClickListener {
        val currentTime = System.currentTimeMillis()
        if (lastClickTime != 0L && (currentTime - lastClickTime < interval)) {
            return@setOnClickListener
        }
        lastClickTime = currentTime
        action(it)
    }
}

fun Any?.notNull(notNullAction: (value: Any) -> Unit, nullAction1: () -> Unit) {
    if (this != null) {
        notNullAction.invoke(this)
    } else {
        nullAction1.invoke()
    }
}

/**
 * 添加点击缩放效果
 *
 * @param id
 * @param scale
 */
fun BaseViewHolder.applyPressedViewScale(@IdRes id: Int, scale: Float = -0.25f) {
    ClickUtils.applyPressedViewScale(arrayOf(this.getView(id)), floatArrayOf(scale))
}

/**
 * 添加点击缩放效果
 *
 * @param id
 * @param scale
 */
fun BaseViewHolder.applyPressedViewScale(@IdRes ids: IntArray, scales: FloatArray) {
    val views: Array<View> = ids.map {
        this.getView<View>(it)
    }.toArray()
    ClickUtils.applyPressedViewScale(views, scales)
}


inline fun <reified T> List<T>.toArray(): Array<T> {
    return Array(size) { index ->
        this[index]
    }
}
