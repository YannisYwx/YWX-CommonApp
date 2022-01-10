package com.ywx.common.base.fragment

import android.view.View
import androidx.viewbinding.ViewBinding

/**
 * Author: YWX
 * Date: 2021/8/31 10:00
 * Description: 支持view binding的fragment
 */
abstract class BaseViewBindingFragment<VB : ViewBinding> : BaseFragment() {
    lateinit var mBinding: VB

    override fun bindingView(contentView: View) {
        mBinding = getViewBinding(contentView)
    }

    abstract fun getViewBinding(contentView: View): VB

    override fun onBindLayout() = mBinding.root.id

}