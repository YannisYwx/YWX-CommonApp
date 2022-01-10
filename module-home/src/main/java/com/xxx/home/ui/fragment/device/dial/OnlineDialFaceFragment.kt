package com.xxx.home.ui.fragment.device.dial

import android.os.Bundle
import android.view.View
import com.xxx.home.R
import com.xxx.home.databinding.HomeFragmentOnlineFacesBinding
import com.ywx.common.base.fragment.NestViewBindingFragment

/**
 * Author: YWX
 * Date: 2021/12/2 16:12
 * Description: 在线表盘
 */
class OnlineDialFaceFragment : NestViewBindingFragment<HomeFragmentOnlineFacesBinding>() {

    companion object {
        fun newInstance(index: Int): OnlineDialFaceFragment {
            val args = Bundle()
            args.putInt("Index_F", index)
            val fragment = OnlineDialFaceFragment()
            fragment.arguments = args
            return fragment
        }

        fun newInstance() = OnlineDialFaceFragment()
    }


    override fun getViewBinding(contentView: View): HomeFragmentOnlineFacesBinding {
        return HomeFragmentOnlineFacesBinding.bind(contentView)
    }

    override fun onBindLayout() = R.layout.home_fragment_online_faces

}