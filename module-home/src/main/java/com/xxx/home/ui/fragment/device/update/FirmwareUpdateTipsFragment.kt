package com.xxx.home.ui.fragment.device.update

import android.view.View
import com.xxx.home.R
import com.xxx.home.databinding.FragmentFirmwareUpdateNoticeBinding
import com.ywx.common.base.fragment.ScrollTitleNestFragment

/**
 * Author: YWX
 * Date: 2022/1/10 17:10
 * Description: 固件更新提示页面
 */
class FirmwareUpdateTipsFragment :ScrollTitleNestFragment<FragmentFirmwareUpdateNoticeBinding>() {
    override fun onBindScrollerLayout() = R.layout.fragment_firmware_update_notice

    override fun setTitleStringRes() = R.string.ani_device_updatefirm_title

    override fun getViewBinding(contentView: View) = FragmentFirmwareUpdateNoticeBinding.bind(contentView)

    override fun initData() {
        super.initData()
        mBinding.btnUpdate.setOnClickListener {
            fragmentStart(R.id.action_tips2Updating)
        }
    }
}