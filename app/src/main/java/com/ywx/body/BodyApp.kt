package com.ywx.body

import com.ywx.common.App
import com.ywx.common.config.ModuleLifecycleConfig

/**
 * Author: YWX
 * Date: 2021/8/12 9:56
 * Description:
 */
class BodyApp : App() {
    override fun onCreate() {
        super.onCreate()
        isDebug = BuildConfig.IS_DEBUG
        // 初始化需要初始化的组件
        ModuleLifecycleConfig.getInstance().initModuleAhead(this)
    }
}