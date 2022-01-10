package com.xxx.home.bean

import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * Author: YWX
 * Date: 2021/11/4 17:12
 * Description: 主页的device item
 */
class NotificationSettingsItem(private val itemType: Int) : MultiItemEntity {
    companion object {
        /*设备功能*/
        const val ITEM_TYPE_DEVICE_FUNCTION = 0x00

        /**/
        const val ITEM_TYPE_PHONE_APP_NOTIFICATION = 0x01
    }

    override fun getItemType() = itemType




}