package com.xxx.home.ui.fragment.device.update

import android.view.View
import com.xxx.home.R
import com.xxx.home.databinding.FragmentFirmwareUpdateErrorNoticeBinding
import com.xxx.home.databinding.FragmentFirmwareUpdateNoticeBinding
import com.ywx.common.base.fragment.ScrollTitleNestFragment

/**
 * Author: YWX
 * Date: 2022/1/10 17:10
 * Description: 固件更新提示页面
 */
class FirmwareUpdateErrorTipsFragment :ScrollTitleNestFragment<FragmentFirmwareUpdateErrorNoticeBinding>() {
    override fun onBindScrollerLayout() = R.layout.fragment_firmware_update_error_notice

    override fun setTitleStringRes() = R.string.ani_device_updatefirm_trouble_title

    override fun getViewBinding(contentView: View) = FragmentFirmwareUpdateErrorNoticeBinding.bind(contentView)

}