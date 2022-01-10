package com.ywx.common.util

import java.math.BigDecimal
import java.math.RoundingMode

/**
 * Author: YWX
 * Date: 2021/8/31 15:51
 * Description:
 */
class BigDecimalUtil {
    companion object {
        /**
         * 直接截断-float
         *
         * @param value
         * @param position 保留几位小数
         * @return
         */
        @JvmStatic
        fun getDownFloat(value: String?, position: Int): Float {
            return if (value.isNullOrEmpty()) {
                0f
            } else try {
                val bigObject = BigDecimal(value)
                val d2 = bigObject.setScale(position, RoundingMode.DOWN)
                d2.toFloat()
            } catch (e: Exception) {
                0f
            }
        }

        /**
         * 直接截断-double
         *
         * @param value
         * @param position 保留几位小数
         * @return
         */
        @JvmStatic
        fun getDownDouble(value: String?, position: Int) =
                if (value == null || value.isEmpty()) {
                    0.0f
                } else try {
                    val bigObject = BigDecimal(value)
                    val d2 = bigObject.setScale(position, RoundingMode.DOWN)
                    d2.toDouble()
                } catch (e: Exception) {
                    0
                }


        /**
         * 四舍五入
         *
         * @param value
         * @param position
         * @return
         */
        @JvmStatic
        fun getPositionFloatHalfUp(value: String?, position: Int) = BigDecimal(value).setScale(position, RoundingMode.HALF_UP).toFloat()


        /**
         * 舍入,后面有数据的话，前一位直接加1
         * 1.1->2
         *
         * @param value
         * @param position
         * @return
         */
        @JvmStatic
        fun getPositionFloatUP(value: String?, position: Int) = BigDecimal(value).setScale(position, RoundingMode.UP).toFloat()
    }
}