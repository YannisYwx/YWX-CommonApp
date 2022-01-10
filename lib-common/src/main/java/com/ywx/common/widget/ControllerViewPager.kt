package com.ywx.common.widget

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.animation.Interpolator
import android.widget.Scroller
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import androidx.viewpager.widget.ViewPager

/**
 * Author: YWX
 * Date: 2021/11/4 9:56
 * Description: 可以控制左右滑动的viewpager
 */
class ControllerViewPager(context: Context, attrs: AttributeSet?) : ViewPager(context, attrs) {

    init {
        /**
         * 通过反射来修改 ViewPager的mScroller属性，以改变滑动速度
         */
        try {
            val clazz = Class.forName("androidx.viewpager.widget.ViewPager")
            val field = clazz.getDeclaredField("mScroller")
            val fixedSpeedScroller = FixedSpeedScroller(context, LinearOutSlowInInterpolator())
            fixedSpeedScroller.mDuration = (450)
            field.isAccessible = true
            field.set(this, fixedSpeedScroller)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    var isCanGoLeft = true
    var isCanGoRight = true

    private var lastX = 0f

    companion object {

        const val LEFT_TO_RIGHT = 1 //方向=从左到右

        const val RIGHT_TO_LEFT = 2 //方向=从右到左
    }


    /**
     * 手指是否已经抬起 （如果不加入这个参数判断，在手指抬起的那一刻touch事件return了true，页面会卡在手指离开那时候的偏移位置）
     */
    private var isUp = true

    override fun onTouchEvent(event: MotionEvent): Boolean {
        var direction = 0
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                isUp = false
                //获取起始坐标值
                lastX = event.x
            }
            MotionEvent.ACTION_MOVE -> direction = if (event.x - lastX < 0) { //从右到左滑（左滑）
                RIGHT_TO_LEFT
            } else { //从左到右滑（右滑）
                LEFT_TO_RIGHT
            }
            MotionEvent.ACTION_UP -> isUp = true
        }
        return if (isUp) {
            //手指抬起后,不再接管viewpager的事件，让它自由地滑动
            tryCatchError(event)
        } else if (direction == RIGHT_TO_LEFT && isCanGoRight) {
            //允许滑动则应该调用父类的方法
            tryCatchError(event)
        } else if (direction == LEFT_TO_RIGHT && isCanGoLeft) {
            //允许滑动则应该调用父类的方法
            tryCatchError(event)
        } else {
            //禁止滑动则不做任何操作，直接返回true即可
            true
        }
    }

    /**
     * viewpager滑动偶尔莫名其妙崩溃报native,捕获异常以后，出现这种情况的时候App不会崩溃，只是卡顿那一短暂时间后继续正常操作
     * @param event
     * @return
     */
    private fun tryCatchError(event: MotionEvent): Boolean {
        try {
            return super.onTouchEvent(event)
        } catch (ex: IllegalArgumentException) {
            ex.printStackTrace()
            Log.d("kid", "捕获浏览报错4")
        }
        return false
    }

    /**
     * <pre>
     *     author : jake
     *     time   : 2018/11/20
     *     function   :  控制ViewPager滑动速度
     *     version: 1.0
     * </pre>
     *
     * 重写 startScroll方法，忽略传过来的 duration 值
     */
    class FixedSpeedScroller : Scroller {

        var mDuration = 800

        constructor (context: Context) : super(context)

        constructor (context: Context, interpolator: Interpolator) : super(context, interpolator)

        constructor (context: Context, interpolator: Interpolator, flywheel: Boolean) : super(context, interpolator, flywheel)

        override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int) {
            super.startScroll(startX, startY, dx, dy, mDuration)
        }

        override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
            super.startScroll(startX, startY, dx, dy, mDuration)
        }

    }
}