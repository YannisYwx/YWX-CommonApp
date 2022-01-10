package com.xxx.home.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.xxx.home.R
import com.xxx.home.databinding.PagerFindDeviceBinding
import com.ywx.common.widget.ViewPagerBottomSheet

/**
 * Author: YWX
 * Date: 2021/11/10 10:28
 * Description: 查找设备
 */
class FindDeviceBottomSheet(context: Context) : ViewPagerBottomSheet(context) {

    lateinit var pageOne: View

    lateinit var pageTwo: View

    lateinit var mFindingDeviceBinding: PagerFindDeviceBinding

    override fun onCreate() {
        super.onCreate()
        mFindingDeviceBinding.tvConnectTips.setOnClickListener{
            mFindingDeviceBinding.ovLoading.startOrStop()
        }

        mFindingDeviceBinding.btnGoToConnect.setOnClickListener {
            mBinding.cvp.currentItem = 1
        }
    }

    override fun getPagers(): List<View> {
        pageOne = LayoutInflater.from(context).inflate(R.layout.pager_find_device, null)
        pageTwo = LayoutInflater.from(context).inflate(R.layout.pager_find_device_failed, null)
        mFindingDeviceBinding = PagerFindDeviceBinding.bind(pageOne)
        return listOf(pageOne, pageTwo)
    }
}