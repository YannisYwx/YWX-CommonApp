package com.xxx.main.ui.fragment.permission

import android.view.View
import com.veepoo.main.R
import com.veepoo.main.databinding.MainFragmentPermissionBinding
import com.ywx.common.base.fragment.BaseViewBindingFragment

/**
 * Author: YWX
 * Date: 2021/9/2 16:03
 * Description: 定位权限许可
 */
class LocationPermissionFragment : BaseViewBindingFragment<MainFragmentPermissionBinding>() {

    companion object {
        fun newInstance(): LocationPermissionFragment {
            return LocationPermissionFragment()
        }
    }

    override fun getViewBinding(contentView: View): MainFragmentPermissionBinding {
        return MainFragmentPermissionBinding.bind(contentView)
    }

    override fun initData() {
    }

    override fun bindListener() {
        super.bindListener()
        mBinding.btnSetUp.setOnClickListener{
            fragmentStart(R.id.action_locationPermission2BackupSyncData)
        }
    }

    override fun onBindLayout() = R.layout.main_fragment_permission

    override fun enableCommonBar() = true

}