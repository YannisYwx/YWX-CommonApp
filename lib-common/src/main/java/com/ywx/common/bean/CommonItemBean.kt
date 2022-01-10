package com.ywx.common.bean

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * Author: YWX
 * Date: 2022/1/7 17:29
 * Description:
 */
data class CommonItemBean(val tag: Int = ItemType.COMMON, @DrawableRes var iconRes: Int?, @StringRes var labelStrRes: Int?,
                          var isShowSwitchButton: Boolean = true, var isShowRightArrowIcon: Boolean = true,
                          var isChecked: Boolean = true) {
    object ItemType {

        const val COMMON = 0

        interface DeviceFunctionNotify {
            companion object {
                /**
                 * 活动卡路里
                 */
                const val ACTIVITY = 0x10

                /**
                 * 闹钟
                 */
                const val ALARM = 0x11

                /**
                 * 血氧
                 */
                const val BLOOD_OXYGEN = 0x12

                /**
                 * 呼吸
                 */
                const val BREATH = 0x13

                /**
                 * 断连提醒
                 */
                const val DISCONNECT_NOTIFY = 0x14

                /**
                 * 经期跟踪
                 */
                const val CYCLE_TRACKING = 0x15

                /**
                 * 心脏健康
                 */
                const val HEART_HEALTH = 0x16

                /**
                 * 睡眠
                 */
                const val SLEEP = 0x17
            }
        }

        interface PhoneAppNotify {
            companion object {
                /**
                 * 来电
                 */
                const val PHONE_CALL = 0x20

                /**
                 * 短信
                 */
                const val SMS = 0x21

                /**
                 * Connected2
                 */
                const val CONNECTED2 = 0x22

                /**
                 * 非死不可
                 */
                const val FACEBOOK = 0x23

                /**
                 * GMail
                 */
                const val GMAIL = 0x24

                /**
                 * Instagram
                 */
                const val INSTAGRAM = 0x25

                /**
                 * Kakao talk
                 */
                const val KAKAO_TALK = 0x26

                /**
                 * Line
                 */
                const val LINE = 0x27

                /**
                 * 领英
                 */
                const val LINKED_IN = 0x28

                /**
                 * messenger
                 */
                const val MESSENGER = 0x29

                /**
                 * QQ
                 */
                const val QQ = 0x2A

                /**
                 * skype
                 */
                const val SKYPE = 0x2B

                /**
                 * snapchat
                 */
                const val SNAPCHAT = 0x2C

                /**
                 * telegram
                 */
                const val TELEGRAM = 0x2D

                /**
                 * 抖音
                 */
                const val TIK_TOK = 0x2E

                /**
                 * 推特
                 */
                const val TWITTER = 0x2F

                /**
                 * 微信
                 */
                const val WECHAT = 0x30

                /**
                 * whatsapp
                 */
                const val WHATSAPP = 0x31

                /**
                 * 其他
                 */
                const val OTHER = 0x32
            }
        }

    }
}



