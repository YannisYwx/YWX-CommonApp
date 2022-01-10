package com.ywx.common.event

/**
 *  @author : WX.Y
 *  date : 2021/3/22 13:03
 *  description : EventBus事件标识
 */
class EventCode {
    interface App {
        companion object {
            /**
             * token失效
             */
            const val TOKEN_INVALID = 1_000

            /**
             * 重置密码
             */
            const val RESET_PASSWORD = 1_001
        }
    }


    interface Main {
        companion object {
            /**
             * 点击同意隐私政策
             */
            const val CLICK_ACCPET = 2_000

            /**
             * 登录成功
             */
            const val LOGIN_SUCCESS = 2_001

            /**
             * 登录失败
             */
            const val LOGIN_FAIL = 2_002

            /**
             * 可以下一步设置
             */
            const val ENABLE_TO_NEXT = 2_003

            /**
             * 不可以下一步
             */
            const val DISABLE_TO_NEXT = 2_004
        }
    }

    interface Device {
        companion object {
            /**
             * 本地表盘点击事件
             */
            const val LOCAL_DIAL_FACE_CLICK = 3_000
        }
    }

    interface User {
        companion object {

        }
    }

}