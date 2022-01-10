package com.xxx.home.ui.adapter

import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.xxx.home.ui.fragment.device.dial.LocalDialFaceFragment
import com.xxx.home.ui.fragment.device.dial.OnlineDialFaceFragment
import com.xxx.home.ui.fragment.device.dial.PhotoFaceFragment

/**
 * Author: YWX
 * Date: 2021/8/25 11:08
 * Description:
 */
class DialFaceFragmentPagerAdapter(manager: FragmentActivity) : FragmentStateAdapter(manager) {

    private val fragmentArray: SparseArray<Fragment> = SparseArray()

    init {
        fragmentArray.put(PAGE_LOCAL_FACE, LocalDialFaceFragment.newInstance())
        fragmentArray.put(PAGE_PHOTO_FACE, PhotoFaceFragment.newInstance())
        fragmentArray.put(PAGE_ONLINE_FACE, OnlineDialFaceFragment.newInstance())
    }


    companion object {
        /**
         * 本地表盘
         */
        const val PAGE_LOCAL_FACE = 0

        /**
         * 照片表盘
         */
        const val PAGE_PHOTO_FACE = 1

        /**
         * 在线表盘
         */
        const val PAGE_ONLINE_FACE = 2

    }

    override fun createFragment(position: Int): Fragment = fragmentArray[position]

    override fun getItemCount() = fragmentArray.size()

}