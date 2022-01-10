package com.xxx.home.ui.fragment.device.monitor

import android.view.View
import com.xxx.home.R
import com.xxx.home.databinding.FragmentDeviceBreathSettingBinding
import com.xxx.home.databinding.FragmentDeviceHeartHealthBinding
import com.ywx.common.base.fragment.NestViewBindingFragment
import net.cachapa.expandablelayout.ExpandableLayout

/**
 * Author: YWX
 * Date: 2021/12/22 16:41
 * Description: 呼吸监控设置
 */
class BreathSettingsFragment : NestViewBindingFragment<FragmentDeviceBreathSettingBinding>() {

    private var isOff = false

    override fun getViewBinding(contentView: View): FragmentDeviceBreathSettingBinding {
        return FragmentDeviceBreathSettingBinding.bind(contentView)
    }

    override fun onBindLayout() = R.layout.fragment_device_breath_setting

    override fun enableCommonBar(): Boolean {
        return true
    }

    override fun onBackIconClick(v: View?) {
        fragmentUP()
    }

    override fun initData() {
        super.initData()
        mBinding.elvHeartRate.setData(value = "Off", isNeedAppendUnit = false)
        if (isOff) {
//            mBinding.elvHeartRate.isCanExpandable = false
//            mBinding.elvHeartRate.setData(value = "Off", unit = "")
        }
    }
}