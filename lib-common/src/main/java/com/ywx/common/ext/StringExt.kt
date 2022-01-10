package com.ywx.common.ext

import com.blankj.utilcode.util.ToastUtils

/**
 * Author: YWX
 * Date: 2021/9/1 17:27
 * Description: String类扩展方法
 */
fun String.toIntWithDecimal(): Int {
    if (this.endsWith('.')) {
        this.plus('0')
    }
    return this.toDouble().toInt()
}

//加一个前缀
fun String.prefix(prefix: String = "0") = prefix.plus(this)

//换行加一句
fun String.plusWithNewLine(str: String) = this.plus("\n").plus(str)

//弹出消息
fun String.toast() {
    ToastUtils.showShort(this)
}

fun String.show(){
    toast()
}
