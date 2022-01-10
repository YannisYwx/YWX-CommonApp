package com.ywx.common.base

import androidx.fragment.app.Fragment

/**
 * Author: YWX
 * Date: 2021/12/28 11:52
 * Description: 拦截返回键事件
 */
interface BackHandledInterface {

    fun setHandedBackPressFragment(fragment: Fragment)
}