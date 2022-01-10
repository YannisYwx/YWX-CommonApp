package com.ywx.common.ext

/**
 * Author: YWX
 * Date: 2021/11/25 17:53
 * Description:
 */
fun <T> Array<T>.toArrayList(): ArrayList<T> {
    val arrayList = arrayListOf<T>()
    this.iterator().forEach {
        arrayList.add(it)
    }
    return arrayList
}