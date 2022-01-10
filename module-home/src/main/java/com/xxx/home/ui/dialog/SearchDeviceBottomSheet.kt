package com.xxx.home.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.blankj.utilcode.util.ClickUtils
import com.xxx.home.R
import com.xxx.home.databinding.PagerScanDeviceBinding
import com.ywx.common.widget.ViewPagerBottomSheet

/**
 * Author: YWX
 * Date: 2021/11/2 19:30
 * Description: 寻找设备
 */
class SearchDeviceBottomSheet(context: Context) : ViewPagerBottomSheet(context) {

    lateinit var connectDialog: DeviceConnectingDialog

    lateinit var viewOne: View
    lateinit var viewTwo: View
    lateinit var mScanBinding: PagerScanDeviceBinding


    override fun onCreate() {
        super.onCreate()
        ClickUtils.expandClickArea(mScanBinding.tvNotShow, 100)
        ClickUtils.applyPressedViewAlpha(mScanBinding.tvNotShow, 0.5f)
        ClickUtils.applyPressedBgDark(mScanBinding.tvNotShow)
        mScanBinding.tvNotShow.setOnClickListener {
            mBinding.cvp.currentItem = 1
        }
        mScanBinding = PagerScanDeviceBinding.bind(viewOne)

    }

    override fun getPagers(): List<View> {
        viewOne = LayoutInflater.from(context).inflate(R.layout.pager_scan_device, null)
        viewTwo = LayoutInflater.from(context).inflate(R.layout.pager_device_not_shown, null)
        return listOf(viewOne, viewTwo)
    }

}