package com.xxx.home.ui.fragment.profile

import android.view.View
import com.xxx.home.R
import com.xxx.home.databinding.HomeFragmentProfileBinding
import com.ywx.common.base.fragment.NestViewBindingFragment

/**
 * Author: YWX
 * Date: 2021/9/13 15:30
 * Description: 【今天】页面
 */
class ProfileFragment : NestViewBindingFragment<HomeFragmentProfileBinding>() {
    override fun initData() {
    }

    override fun onBindLayout() = R.layout.home_fragment_profile

    override fun getViewBinding(contentView: View) = HomeFragmentProfileBinding.bind(contentView)
}