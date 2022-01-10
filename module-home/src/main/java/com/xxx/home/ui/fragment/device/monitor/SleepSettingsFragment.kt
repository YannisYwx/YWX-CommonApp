package com.xxx.home.ui.fragment.device.monitor

import android.view.View
import com.xxx.home.R
import com.xxx.home.databinding.FragmentDeviceSleepSettingBinding
import com.ywx.common.base.fragment.NestViewBindingFragment

/**
 * Author: YWX
 * Date: 2021/12/22 16:41
 * Description: 睡眠设置
 */
class SleepSettingsFragment : NestViewBindingFragment<FragmentDeviceSleepSettingBinding>() {

    override fun getViewBinding(contentView: View): FragmentDeviceSleepSettingBinding {
        return FragmentDeviceSleepSettingBinding.bind(contentView)
    }

    override fun onBindLayout() = R.layout.fragment_device_sleep_setting

    override fun enableCommonBar(): Boolean {
        return true
    }

    override fun onBackIconClick(v: View?) {
        fragmentUP()
    }

    override fun initData() {
        super.initData()
    }
}