package com.ywx.common.widget

import android.content.Context
import android.view.View
import androidx.viewbinding.ViewBinding
import com.lxj.xpopup.core.CenterPopupView

/**
 * Author: YWX
 * Date: 2021/11/3 17:21
 * Description: 添加的ViewBinding功能
 */
abstract class CommonCenterDialog<VB:ViewBinding>(context: Context) : CenterPopupView(context) {

    lateinit var mBinding:VB

    override fun onCreate() {
        super.onCreate()
        bindView(contentView)
    }

    private fun bindView(contentView: View){
        mBinding = getViewBinding(contentView)
    }

    abstract fun getViewBinding(contentView: View): VB

}