package com.xxx.home

import com.ywx.common.App
import com.ywx.common.IModuleInit
import com.ywx.common.ext.logD
import com.ywx.common.ext.openLog

/**
 * Author: YWX
 * Date: 2021/9/13 11:41
 * Description: Home模块初始化
 */
class HomeModuleInit : IModuleInit {
    override fun onInitAhead(application: App?): Boolean {
        openLog = BuildConfig.DEBUG
        "Home模块初始化-onInitAhead".logD(HomeModuleInit::class.java.simpleName)
        return true
    }

    override fun onInitLow(application: App?): Boolean {
        "Home模块初始化-onInitLow".logD(HomeModuleInit::class.java.simpleName)
        return true
    }
}