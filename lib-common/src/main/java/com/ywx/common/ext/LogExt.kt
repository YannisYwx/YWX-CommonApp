package com.ywx.common.ext

import android.util.Log
import com.blankj.utilcode.util.ToastUtils
import com.orhanobut.logger.Logger

/**
 * Author: YWX
 * Date: 2021/9/8 14:52
 * Description: 日志打印扩展
 */
const val TAG = "YWX-Log"

/**
 *
 * 是否需要开启打印日志，默认打开，1.1.7-1.1.8版本是默认关闭的(1.0.0-1.1.6没有这个字段，框架在远程依赖下，直接不打印log)，但是默认关闭后很多人反馈都没有日志，好吧，我的我的
 * 根据true|false 控制网络请求日志和该框架产生的打印
 */
var openLog = true

/**
 * 是否使用第三方框架Logger来打印
 */
var useLogger = false

private enum class LEVEL {
    V, D, I, W, E
}

var classTag = TAG

fun String.logV(tag: String = classTag) =
        log(LEVEL.V, tag, this)

fun String.logD(tag: String = classTag) =
        log(LEVEL.D, tag, this)

fun String.logI(tag: String = classTag) =
        log(LEVEL.I, tag, this)

fun String.logW(tag: String = classTag) =
        log(LEVEL.W, tag, this)

fun String.logE(tag: String = classTag) =
        log(LEVEL.E, tag, this)

private fun log(level: LEVEL, tag: String, message: String) {
    if (!openLog) return
    when (level) {
        LEVEL.V -> if (useLogger) Logger.t(tag).v(message) else Log.v(tag, message)
        LEVEL.D -> if (useLogger) Logger.t(tag).d(message) else Log.d(tag, message)
        LEVEL.I -> if (useLogger) Logger.t(tag).i(message) else Log.i(tag, message)
        LEVEL.W -> if (useLogger) Logger.t(tag).w(message) else Log.w(tag, message)
        LEVEL.E -> if (useLogger) Logger.t(tag).e(message) else Log.e(tag, message)
    }
}
