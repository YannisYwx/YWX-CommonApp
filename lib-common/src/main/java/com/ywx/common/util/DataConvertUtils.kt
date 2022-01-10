package com.ywx.common.util

import java.math.BigDecimal
import java.math.RoundingMode

/**
 * Author: YWX
 * Date: 2021/8/31 15:33
 * Description: 数据转换工具类
 */
object DataConvertUtils {

    private const val KM_TO_MILES = 0.62138f //距离转英里系数

    const val KG_TO_LBS = 2.2046f //kg转化磅的系数

    const val CM_TO_INCHES = 0.393700787402f //cm转英寸的系数 0.0328084

    const val CM_TO_FT = 0.0328084f //cm转英寸的系数 0.0328084

    /**
     * 公里转英里
     *
     * @param km 公里数
     * @param decimal  保留的小数点数
     * @return
     */
    fun km2Miles(km: Double, decimal: Int = 1) = BigDecimalUtil.getDownDouble("" + km * KM_TO_MILES, decimal) as Double


    /**
     * 四舍五入保留一位小数 KG-->LBKG
     *
     * @param kg
     * @return
     */
    fun kg2Lbs(kg: Double, decimal: Int = 1) = getDecimalDoubleUP(kg * KG_TO_LBS, decimal).toFloat()

    /**
     * 四舍五入保留一位小数 lbs->kg
     *
     * @param lbs 磅
     * @return 公斤
     */
    fun lbs2Kg(lbs: Double, decimal: Int = 1) = getDecimalDoubleUP(lbs / KG_TO_LBS, decimal).toFloat()


    /**
     * 四舍五入取整 cm-->inches
     *
     * @param cm
     * @return
     */
    fun cm2Inches(cm: Double) = getDecimalDoubleUP(cm * CM_TO_INCHES, 0).toInt()

    /**
     * 四舍五入取整 inches-->cm
     *
     * @param inches
     * @return
     */
    fun inches2Cm(inches: Double) = getDecimalDoubleUP(inches / CM_TO_INCHES, 1)


    fun cm2FtIn(cm: Int): Array<Int> {
        val inches = cm * CM_TO_INCHES
        val inchesInt = getDecimalDoubleUP(inches.toDouble(), 0).toInt()
        val ft = inchesInt / 12
        return arrayOf(ft, inchesInt % 12)
    }

    /**
     * @param value
     * @param decimal 小数位数，4舍5入
     * @return
     */
    private fun getDecimalDoubleUP(value: Double, decimal: Int): Double {
        val bigObject = BigDecimal(value)
        return bigObject.setScale(decimal, RoundingMode.HALF_UP).toDouble()
    }

}