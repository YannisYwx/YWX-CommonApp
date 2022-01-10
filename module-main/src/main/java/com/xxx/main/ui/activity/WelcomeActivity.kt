package com.xxx.main.ui.activity

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.AdaptScreenUtils
import com.blankj.utilcode.util.BarUtils
import com.gyf.immersionbar.ImmersionBar
import com.veepoo.main.R
import com.ywx.common.base.activity.BaseActivity
import com.ywx.common.router.RouterActivityPath

/**
 * Author: YWX
 * Date: 2021/8/12 11:50
 * Description: 欢迎页面
 */
@Route(path = RouterActivityPath.Main.PAGER_WELCOME)
class WelcomeActivity : BaseActivity() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, WelcomeActivity::class.java))
        }
    }

    override fun getResources(): Resources? =
            AdaptScreenUtils.adaptWidth(super.getResources(), 1080)

    override fun initView() {
        super.initView()
        ImmersionBar.with(this).init()
        BarUtils.setStatusBarLightMode(this, true)
    }

    override fun initData() {
    }

    override fun isLightMode() = true

    override fun onBindLayout() = R.layout.main_container

    override fun enableCommonBar() = false
}