package com.xxx.main.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Author: YWX
 * Date: 2021/8/27 15:04
 * Description: 用户设置数据 (๑•̀ㅂ•́)و✧
 */

/**
 * TODO
 *
 * @property id id
 * @property name 姓名
 * @property gender 性别
 * @property birthday 生日
 * @property weight 体重
 * @property height 身高
 * @property skinColorLevel 肤色
 * @property unitWeight 体重单位
 * @property unitHeight 身高单位
 */
@Parcelize
data class UserBean(var id: Int, var name: String, var gender: Gender, var birthday: String,
                    var weight: Int, var height: String, var skinColorLevel: SkinColorLevel,
                    var unitWeight: WeightUnit = WeightUnit.KG, var unitHeight: HeightUnit = HeightUnit.CM) : Parcelable {


    companion object {
        fun getDefault() = UserBean(id = 0, name = "", gender = Gender.MALE, birthday = "000-00-00", weight = 55,
                height = "170", skinColorLevel = SkinColorLevel.LV_3, unitWeight = WeightUnit.KG, unitHeight = HeightUnit.CM)
    }
}


/**
 * 性别类型
 */
enum class Gender {
    MALE, /*男*/
    FEMALE, /*女*/
    OTHER, /*不男不女*/
    NONE, /*未选择*/
}

/**
 * 体重单位
 */
enum class WeightUnit {
    KG,/*千克*/
    LBS,/*磅*/
}

/**
 * 身高单位
 */
enum class HeightUnit {
    CM,/*厘米*/
    FT,/*英尺*/
}

/**
 * 肤色等级
 */
enum class SkinColorLevel {
    LV_1,
    LV_2,
    LV_3,
    LV_4,
    LV_5,
    LV_6,
}