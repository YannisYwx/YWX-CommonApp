package com.xxx.main

import com.blankj.utilcode.util.Utils
import com.kingja.loadsir.core.LoadSir
import com.orhanobut.logger.Logger
import com.ywx.common.App
import com.ywx.common.IModuleInit
import com.ywx.common.adapter.ScreenAutoAdapter
import com.ywx.common.mvvm.view.status.EmptyStatus
import com.ywx.common.mvvm.view.status.ErrorStatus
import com.ywx.common.mvvm.view.status.InitStatus
import com.ywx.common.mvvm.view.status.LoadingStatus
import com.ywx.common.net.api.P8Api.HOST
import com.zhouyou.http.EasyHttp
import com.zhouyou.http.cache.converter.GsonDiskConverter
import com.zhouyou.http.cache.model.CacheMode

/**
 * Author: YWX
 * Date: 2021/8/10 19:56
 * Description: 主模块初始化部分
 */
class MainModuleInit : IModuleInit {
    override fun onInitAhead(application: App?): Boolean {
        ScreenAutoAdapter.setup(application!!)
        EasyHttp.init(application)
        if (application.isDebug) {
            EasyHttp.getInstance().debug("easyhttp", true)
        }
        EasyHttp.getInstance()
                .setBaseUrl(HOST)
                .setReadTimeOut(15 * 1000.toLong())
                .setWriteTimeOut(15 * 1000.toLong())
                .setConnectTimeout(15 * 1000.toLong())
                .setRetryCount(3)
                .setCacheDiskConverter(GsonDiskConverter())
                .setCacheMode(CacheMode.FIRSTREMOTE)
        LoadSir.beginBuilder()
                .addCallback(ErrorStatus())
                .addCallback(LoadingStatus())
                .addCallback(EmptyStatus())
                .addCallback(InitStatus())
                .setDefaultCallback(InitStatus::class.java)
                .commit()
        Utils.init(application)
        Logger.i("main组件初始化完成 -- onInitAhead")
        return true
    }

    override fun onInitLow(application: App?): Boolean {
        return true
    }
}