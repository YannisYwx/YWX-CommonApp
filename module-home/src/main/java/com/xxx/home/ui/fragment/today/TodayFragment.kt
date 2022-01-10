package com.xxx.home.ui.fragment.today

import android.view.View
import com.xxx.home.R
import com.xxx.home.databinding.HomeFragmentTodayBinding
import com.ywx.common.base.fragment.NestViewBindingFragment

/**
 * Author: YWX
 * Date: 2021/9/13 15:30
 * Description: 【今天】页面
 */
class TodayFragment : NestViewBindingFragment<HomeFragmentTodayBinding>() {
    override fun initData() {
        mBinding.btn1.setOnClickListener {
            mBinding.ssv.slidedSquare(0, 1)
        }
        mBinding.btn2.setOnClickListener {
//            mBinding.ssv.slidedSquare(1, 0)
            mBinding.ssvHDWMY.setLabelTags(arrayOf("h", "d", "w", "m", "y"))

        }
    }

    override fun onBindLayout() = R.layout.home_fragment_today

    override fun getViewBinding(contentView: View) = HomeFragmentTodayBinding.bind(contentView)
}