package com.xxx.home.ui.fragment.device.monitor

import android.view.View
import com.xxx.home.R
import com.xxx.home.databinding.FragmentDeviceHeartHealthBinding
import com.ywx.common.base.fragment.NestViewBindingFragment
import net.cachapa.expandablelayout.ExpandableLayout

/**
 * Author: YWX
 * Date: 2021/12/22 16:41
 * Description: 心脏健康
 */
class HeartHealthSettingsFragment : NestViewBindingFragment<FragmentDeviceHeartHealthBinding>() {

    private var isOff = false

    override fun getViewBinding(contentView: View): FragmentDeviceHeartHealthBinding {
        return FragmentDeviceHeartHealthBinding.bind(contentView)
    }

    override fun onBindLayout() = R.layout.fragment_device_heart_health

    override fun enableCommonBar(): Boolean {
        return true
    }

    override fun onBackIconClick(v: View?) {
        fragmentUP()
    }

    override fun initData() {
        super.initData()
        if (isOff) {
            mBinding.elvHeartRate.isCanExpandable = false
            mBinding.elvHeartRate.setData(value = "Off", unit = "")
        }
    }
}