package com.xxx.home._module

import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.ywx.common.App


/**
 * Author: YWX
 * Date: 2021/10/28 16:34
 * Description: 模块化的App
 */
class ModuleApp : App() {
    override fun onCreate() {
        isDebug = true
        super.onCreate()
        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)
                .methodCount(0)
                .methodOffset(7)
                .tag("Home")
                .build()
        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy));
    }

//    private fun initEasyHttp() {
//        EasyHttp.init(this)
//        //设置全局的请求头
//        val headers: HttpHeaders = HttpHeaders()
//        headers.put("packagename")
//    }
}