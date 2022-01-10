package com.xxx.home.ui.fragment.device.notification

import android.view.View
import com.xxx.home.R
import com.xxx.home.databinding.FragmentNotifyActivityBinding
import com.ywx.common.base.fragment.ScrollTitleNestFragment

/**
 * Author: YWX
 * Date: 2022/1/8 16:37
 * Description: 活动卡路里通知设置
 */
class ActivityNotifySettingsFragment : ScrollTitleNestFragment<FragmentNotifyActivityBinding>() {
    override fun onBindScrollerLayout() = R.layout.fragment_notify_activity

    override fun setTitleStringRes() = R.string.ani_data_class_activity

    override fun getViewBinding(contentView: View) = FragmentNotifyActivityBinding.bind(contentView)

    override fun initData() {
        super.initData()
    }
}