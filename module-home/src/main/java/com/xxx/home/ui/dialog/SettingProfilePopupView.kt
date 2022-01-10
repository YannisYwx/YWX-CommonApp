package com.xxx.home.ui.dialog

import android.content.Context
import android.view.View
import com.blankj.utilcode.util.ToastUtils
import com.lxj.xpopup.core.CenterPopupView
import com.xxx.home.R
import com.xxx.home.databinding.HomeDialogSetProfileBinding
import com.ywx.common.widget.CommonCenterDialog

/**
 * Author: YWX
 * Date: 2021/10/29 16:06
 * Description: 设置用户信息弹框
 */
class SettingProfilePopupView(context: Context) : CommonCenterDialog<HomeDialogSetProfileBinding>(context) {

    override fun getImplLayoutId(): Int {
        return R.layout.home_dialog_set_profile
    }

    override fun onCreate() {
        super.onCreate()

        mBinding.btnCancel.setOnClickListener {
            ToastUtils.showShort("取消")
        }

        mBinding.btnCancel.setOnClickListener {
            ToastUtils.showShort("取消你妹")
        }
    }

    override fun getViewBinding(contentView: View) = HomeDialogSetProfileBinding.bind(contentView)
}