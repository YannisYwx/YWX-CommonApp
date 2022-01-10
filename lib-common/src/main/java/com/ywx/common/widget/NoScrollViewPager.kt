package com.ywx.common.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

/**
 * Author: YWX
 * Date: 2021/11/3 20:38
 * Description:
 */
class NoScrollViewPager(context: Context, attr: AttributeSet?) : ViewPager(context, attr) {
    /**
     * true表示可以滑动，false不能滑动
     */
    var isPagingEnabled: Boolean = true

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return this.isPagingEnabled && super.onTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return this.isPagingEnabled && super.onInterceptTouchEvent(ev);
    }

}
