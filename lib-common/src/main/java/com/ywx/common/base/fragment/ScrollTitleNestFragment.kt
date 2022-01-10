package com.ywx.common.base.fragment

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import android.widget.FrameLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewbinding.ViewBinding
import com.blankj.utilcode.util.AdaptScreenUtils
import com.blankj.utilcode.util.ColorUtils
import com.ywx.common.R
import com.ywx.common.ext.view.gone
import com.ywx.common.widget.ListeningYAxesScrollView

/**
 * Author: YWX
 * Date: 2022/1/6 19:59
 * Description: 可以滑动的标题页面
 */
abstract class ScrollTitleNestFragment<VD : ViewBinding> : BaseViewBindingFragment<VD>(), ListeningYAxesScrollView.OnScrollYListener {

    private lateinit var fContentView: View
    private lateinit var contentTitleView: TextView
    private lateinit var mScrollView: ListeningYAxesScrollView

    private var titleViewHeight: Int = 0

    override fun onBindLayout() = R.layout.fragment_scroller_title

    override fun bindingView(contentView: View) {
        bindViewHasTitle(contentView)
    }

    private fun bindViewHasTitle(contentView: View) {
        //contentView:ScrollView->ConstraintLayout->[ TextView | ViewStub(onBindScrollerLayout) ]
        val mViewStubContent = contentView.findViewById<ViewStub>(R.id.vsFContent)
        mViewStubContent.layoutResource = onBindScrollerLayout()
        this.mScrollView = contentView.findViewById(R.id.scrollRoot)
        this.mScrollView.setOnScrollListener(this)
        this.fContentView = mViewStubContent.inflate()
        mBinding = getViewBinding(fContentView)
        //将fContentView设置位置
        contentTitleView = contentView.findViewById(R.id.tvSTitle)

        if (isSetNoTitle()) {
            titleViewHeight = AdaptScreenUtils.pt2Px(97.90f)
            contentTitleView.setText(setTitleStringRes())
            titleView?.setText(setTitleStringRes())
            titleView?.setTextColor(ColorUtils.setAlphaComponent(Color.parseColor("#333A47"), 0.0f))
            titleView?.visibility = View.VISIBLE
        } else {
            contentTitleView.gone()
        }

        val layoutParams: ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT)
        layoutParams.topToBottom = R.id.tvSTitle
        layoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
        fContentView.layoutParams = layoutParams

    }

    open fun isSetNoTitle() = true

    /**
     * 设置一个内容布局，该布局已嵌套在ScrollView中
     * @return
     */
    @LayoutRes
    abstract fun onBindScrollerLayout(): Int

    override fun onScrollY(scrollY: Int) {
        val distance = AdaptScreenUtils.pt2Px(80.64f).toFloat()
        var alpha = 1.0f
        if (scrollY < distance) {
            alpha = (scrollY.toFloat() / distance)
        }
        titleView?.setTextColor(ColorUtils.setAlphaComponent(Color.parseColor("#333A47"), alpha))
        contentTitleView?.setTextColor(ColorUtils.setAlphaComponent(Color.parseColor("#333A47"), 1 - alpha))
    }

    @StringRes
    abstract fun setTitleStringRes(): Int

    override fun enableLazy(): Boolean {
        return true
    }

    override fun enableCommonBar(): Boolean {
        return true
    }
}