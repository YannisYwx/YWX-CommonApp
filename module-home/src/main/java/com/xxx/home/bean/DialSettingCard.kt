package com.xxx.home.bean

import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * Author: YWX
 * Date: 2021/11/18 16:17
 * Description: 表盘设置卡片
 */
class DialSettingCard(private val itemType: Int) : MultiItemEntity {

    companion object {
        /*字体颜色*/
        const val ITEM_TYPE_COLOR_DOT = 0x00

        /*时间位置*/
        const val ITEM_TYPE_TIME_POSITION = 0x01

        /*时间上方*/
        const val ITEM_TYPE_ABOVE_TIME = 0x02

        /*时间下方*/
        const val ITEM_TYPE_BELOW_TIME = 0x03

    }

    override fun getItemType() = itemType

    var colorPalette: List<DialColor>? = null

}