package com.ywx.common.base.fragment

/**
 * Author: YWX
 * Date: 2021/8/26 18:23
 * Description:内部嵌套的Fragment
 *              默认没有CommonBar以及懒加载
 */
abstract class NestFragment : BaseFragment() {

    override fun enableCommonBar() = false

    override fun enableLazy() = false

    override fun onReVisible() {
        super.onReVisible()
        d("----------------onReVisible 再次可见")
    }

    override fun onFirstVisible() {
        super.onFirstVisible()
        d("----------------onFirstVisible 第一次可见")
    }

//    override fun onVisible() {
//        super.onVisible()
//        d("----------------onVisible 可见")
//    }
//
//    override fun onSupportInvisible() {
//        super.onSupportInvisible()
//        d("----------------onSupportInvisible 不可见")
//    }
}