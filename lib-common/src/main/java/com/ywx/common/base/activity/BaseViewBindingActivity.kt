package com.ywx.common.base.activity

import android.view.View
import androidx.viewbinding.ViewBinding

/**
 * Author: YWX
 * Date: 2021/9/15 16:53
 * Description: 支持ViewBinding的Activity
 */
abstract class BaseViewBindingActivity<VB : ViewBinding> : BaseActivity() {
    lateinit var mBinding: VB

    override fun bindingView(contentView: View) {
        mBinding = getViewBinding(contentView)
    }

    abstract fun getViewBinding(contentView: View): VB

}