package com.xxx.home.bean

import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * Author: YWX
 * Date: 2021/11/4 17:12
 * Description: 主页的device item
 */
class DeviceInfoItem(private val itemType: Int) : MultiItemEntity {
    companion object {
        /*表头*/
        const val ITEM_TYPE_HEAD = 0x00

        /*快捷操作*/
        const val ITEM_TYPE_QUICK_ACTION = 0x01

        /*表盘*/
        const val ITEM_TYPE_DIAL_FACE = 0x02

        /*屏幕和显示*/
        const val ITEM_TYPE_DISPLAY = 0x03

        /*常用功能*/
        const val ITEM_TYPE_FUNCTION = 0x04

        /*健康*/
        const val ITEM_TYPE_HEALTH = 0x05

        /*系统设置*/
        const val ITEM_TYPE_SYSTEM_SETTINGS = 0x06
    }

    override fun getItemType() = itemType

}