package com.xxx.home.ui.fragment.device.update

import android.view.View
import com.xxx.home.R
import com.xxx.home.databinding.FragmentFirmwareUpdateNoticeBinding
import com.xxx.home.databinding.FragmentFirmwareUpdatingBinding
import com.ywx.common.base.fragment.ScrollTitleNestFragment
import com.ywx.common.ext.view.invisible

/**
 * Author: YWX
 * Date: 2022/1/10 17:10
 * Description: 固件更新提示页面
 */
class FirmwareUpdatingFragment :ScrollTitleNestFragment<FragmentFirmwareUpdatingBinding>() {
    override fun onBindScrollerLayout() = R.layout.fragment_firmware_updating

    override fun setTitleStringRes() = R.string.ani_device_updatefirm_title

    override fun isSetNoTitle(): Boolean {
        return false
    }

    override fun getViewBinding(contentView: View) = FragmentFirmwareUpdatingBinding.bind(contentView)

    override fun initData() {
        super.initData()
        mCommonTitleBar.leftCustomView.invisible()
        mBinding.ivUpdating.setOnClickListener {
            fragmentStart(R.id.action_updating2Error)
        }
    }
}