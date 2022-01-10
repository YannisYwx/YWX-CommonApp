package com.ywx.common.util

import com.blankj.utilcode.util.TimeUtils
import java.util.*

/**
 * Author: YWX
 * Date: 2021/8/28 16:28
 * Description: 判断生日function
 */

/**
 * 判断是否是有效的生日
 *
 * @param year
 * @param month
 * @param day
 * @return true 是有效的生日， false 是无效的生日
 */
fun isBirthdayValid(year: Int, month: Int, day: Int, minYear: Int = 1900): Boolean {
    if (year <= 0 || month <= 0 || day <= 0) return false
    if (month > 12 || day > 31) return false
    val thisYear: Int = Calendar.getInstance().get(Calendar.YEAR)
    if (year <= thisYear - 120 || year > thisYear) return false
    if (day > getDaysInMoth(year, month)) return false
    return true
}

fun isValidYearForBirthday(year: Int): Boolean {
    if (year <= 0) return false
    val thisYear: Int = Calendar.getInstance().get(Calendar.YEAR)
    if (year <= thisYear - 120 || year > thisYear) return false
    return true
}

/**
 * 是否是有效的月份
 *
 * @param month
 */
fun isValidMonth(month: Int) = month in 1..12

/**
 * 是否是有效的天数在该年该月
 *
 * @param year
 * @param month
 * @param day
 */
fun isValidDayInMonth(year: Int, month: Int, day: Int) = day in 1..getDaysInMoth(year, month)

/**
 * 该年该月的天数
 *
 * @param year
 * @param month
 */
fun getDaysInMoth(year: Int, month: Int) =
        when (month) {
            1, 3, 5, 7, 8, 10, 12 -> 31
            2 -> if (TimeUtils.isLeapYear(year)) {
                29
            } else {
                28
            }
            4, 6, 9, 11 -> 30
            else -> 30
        }