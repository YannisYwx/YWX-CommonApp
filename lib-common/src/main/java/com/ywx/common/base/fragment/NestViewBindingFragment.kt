package com.ywx.common.base.fragment

import android.os.Bundle
import androidx.viewbinding.ViewBinding

/**
 * Author: YWX
 * Date: 2021/8/26 18:23
 * Description:内部嵌套的Fragment
 *              默认没有CommonBar以及懒加载
 */
abstract class NestViewBindingFragment<VD : ViewBinding> : BaseViewBindingFragment<VD>() {

    override fun enableCommonBar() = false

    override fun enableLazy() = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onReVisible() {
        super.onReVisible()
        d("----------------onReVisible 再次可见")
    }

    override fun onFirstVisible() {
        super.onFirstVisible()
        d("----------------onFirstVisible 第一次可见")
    }

    fun getColor(resourceId: Int) =
            requireContext.resources.getColor(resourceId, null)


}