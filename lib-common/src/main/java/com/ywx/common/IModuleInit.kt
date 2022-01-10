package com.ywx.common

/**
 * Author: YWX
 * Date: 2021/9/13 11:57
 * Description: 动态配置组件Application,有需要初始化的组件实现该接口,统一在宿主app 的Application进行初始化
 */
interface IModuleInit {
    /**
     * 需要优先初始化的
     */
    fun onInitAhead(application: App?): Boolean

    /**
     * 可以后初始化的
     */
    fun onInitLow(application: App?): Boolean
}