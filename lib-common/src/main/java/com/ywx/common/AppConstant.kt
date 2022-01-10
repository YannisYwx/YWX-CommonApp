package com.ywx.common

/**
 *  @author : WX.Y
 *  date : 2021/3/15 11:52
 *  description : app通用的常量类
 */
object AppConstant {

    const val EMPTY_STR = ""

    interface Entry {
        companion object {
            //是否是第一次进入
            const val IS_FIRST_IN = "_is_first_in"

            //是否需要登录
            const val IS_NEED_LOGIN = "_is_need_login"
        }
    }

    /**
     * 数据缓存Key
     */
    interface DataCacheKey {
        companion object {
            const val USER = "_User"
        }
    }
}