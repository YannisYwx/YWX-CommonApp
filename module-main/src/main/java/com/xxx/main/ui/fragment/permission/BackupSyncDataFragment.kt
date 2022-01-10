package com.xxx.main.ui.fragment.permission

import android.view.View
import com.veepoo.main.R
import com.veepoo.main.databinding.MainFragmentBackupSynDataBinding
import com.ywx.common.base.fragment.BaseViewBindingFragment

/**
 * Author: YWX
 * Date: 2021/9/2 16:03
 * Description: 备份和同步用户数据
 */
class BackupSyncDataFragment : BaseViewBindingFragment<MainFragmentBackupSynDataBinding>() {

    companion object {
        fun newInstance(): BackupSyncDataFragment {
            return BackupSyncDataFragment()
        }
    }

    override fun getViewBinding(contentView: View): MainFragmentBackupSynDataBinding {
        return MainFragmentBackupSynDataBinding.bind(contentView)
    }

    override fun initData() {
    }

    override fun bindListener() {
        super.bindListener()
        mBinding.btnSetUp.setOnClickListener {
            fragmentStart(R.id.action_backupSyncData2AllDone)
        }
    }

    override fun onBindLayout() = R.layout.main_fragment_backup_syn_data

    override fun enableCommonBar() = true
}