package com.ywx.common.widget

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.viewbinding.ViewBinding
import com.lxj.xpopup.core.BottomPopupView
import com.lxj.xpopup.util.XPopupUtils
import com.ywx.common.R

/**
 * Author: YWX
 * Date: 2021/11/2 15:26
 * Description: 通用的底部弹框
 */
abstract class CommonBottomSheet<VB : ViewBinding>(context: Context) : BottomPopupView(context) {

    private lateinit var ivDown: ImageView
    private lateinit var tvTitle: TextView
    private lateinit var tvRight: TextView
    private lateinit var contentContainer: FrameLayout

    lateinit var contentView: View

    lateinit var mBinding:VB

    override fun getImplLayoutId() = R.layout.common_bottom_sheet

    override fun onCreate() {
        super.onCreate()
        ivDown = bottomPopupContainer.findViewById(R.id.ivDown)
        tvTitle = bottomPopupContainer.findViewById(R.id.tvTitle)
        tvRight = bottomPopupContainer.findViewById(R.id.tvRight)
        contentContainer = bottomPopupContainer.findViewById(R.id.contentContainer)
        ivDown.setImageResource(getLeftIconResId())
        tvTitle.text = if (getTitleResId() == 0) "" else resources.getString(getTitleResId())
        tvRight.text = if (getRightTextResId() == 0) "" else resources.getString(getRightTextResId())
        initListener()
        if (getBottomContentViewId() != 0) {
            contentView = LayoutInflater.from(context).inflate(getBottomContentViewId(), contentContainer, false)
            if (enableDefaultPadding()) {
                val paddingValue = resources.getDimension(R.dimen.px2pt_24).toInt()
                contentContainer.setPadding(paddingValue, paddingValue, paddingValue, paddingValue)
            }
            contentContainer.addView(contentView)
            bindView(contentView)
        }
    }

    private fun bindView(contentView:View) {
        mBinding = getViewBinding(contentView)
    }

    abstract fun getViewBinding(contentView: View): VB

    /**
     * 是否使用默认的内边距
     */
    open fun enableDefaultPadding() = true

    private fun initListener() {
        ivDown.setOnClickListener {
            onLeftBarClick()
        }

        tvRight.setOnClickListener {
            onRightBarClick()
        }
    }

    open fun getBottomContentViewId() = 0

    open fun onLeftBarClick() {
        this.dismiss()
    }

    /**
     * 右边的文字点击
     */
    open fun onRightBarClick() {

    }

    override fun getMaxHeight() = (XPopupUtils.getScreenHeight(context) * 0.95f).toInt()

    open fun getTitleResId() = 0

    open fun getRightTextResId() = 0

    open fun getLeftIconResId() = R.mipmap.common_icon_arrow_down

}