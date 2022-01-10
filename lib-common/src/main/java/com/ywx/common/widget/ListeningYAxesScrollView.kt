package com.ywx.common.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.ScrollView
import androidx.core.widget.NestedScrollView

/**
 * Author: YWX
 * Date: 2022/1/7 10:19
 * Description: 可以监听Y轴滑动的ScrollView
 */
class ListeningYAxesScrollView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : NestedScrollView(context, attrs, defStyleAttr) {

    private var onScrollListener: OnScrollYListener? = null


    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        this.onScrollListener?.onScrollY(t)
    }


    /**
     * 接口对外公开
     * @param onScrollListener
     */
    fun setOnScrollListener(onScrollListener: OnScrollYListener) {
        this.onScrollListener = onScrollListener
    }

    /**
     * 滚动的回调接口
     */
    interface OnScrollYListener {
        /**
         * 回调方法， 返回MyScrollView滑动的Y方向距离
         * @param scrollY
         *              、
         */
        fun onScrollY(scrollY: Int);
    }
}