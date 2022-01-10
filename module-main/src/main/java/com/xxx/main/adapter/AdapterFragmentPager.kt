package com.xxx.main.adapter

import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.xxx.main.ui.fragment.settings.*

/**
 * Author: YWX
 * Date: 2021/8/25 11:08
 * Description:
 */
class AdapterFragmentPager(manager: FragmentActivity) : FragmentStateAdapter(manager) {

    private val fragmentArray: SparseArray<Fragment> = SparseArray()

    init {
        fragmentArray.put(PAGE_SETTINGS_NAME, NameSettingsFragment.newInstance())
        fragmentArray.put(PAGE_SETTINGS_GENDER, GenderSettingsFragment.newInstance())
        fragmentArray.put(PAGE_SETTINGS_BIRTHDAY, BirthdaySettingsFragment.newInstance())
        fragmentArray.put(PAGE_SETTINGS_WEIGHT, WeightSettingsFragment.newInstance())
        fragmentArray.put(PAGE_SETTINGS_TELL, HeightSettingsFragment.newInstance())
        fragmentArray.put(PAGE_SETTINGS_MORE_INFO, MoreInfoSettingsFragment.newInstance())
    }


    companion object {
        /**
         * 设置姓名
         */
        const val PAGE_SETTINGS_NAME = 0

        /**
         * 设置性别
         */
        const val PAGE_SETTINGS_GENDER = 1

        /**
         * 设置生日
         */
        const val PAGE_SETTINGS_BIRTHDAY = 2

        /**
         * 设置体重
         */
        const val PAGE_SETTINGS_WEIGHT = 3

        /**
         * 设置身高
         */
        const val PAGE_SETTINGS_TELL = 4

        /**
         * 设置其他
         */
        const val PAGE_SETTINGS_MORE_INFO = 5

    }

    override fun createFragment(position: Int): Fragment = fragmentArray[position]

    override fun getItemCount() = fragmentArray.size()

}