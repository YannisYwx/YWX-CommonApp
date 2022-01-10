package com.xxx.home.ui.dialog

import android.content.Context
import android.view.View
import com.blankj.utilcode.util.ToastUtils
import com.xxx.home.R
import com.xxx.home.databinding.HomeDialogDeviceConnectingBinding
import com.ywx.common.widget.CommonCenterDialog

/**
 * Author: YWX
 * Date: 2021/11/3 10:50
 * Description: 设备连接弹框
 */
class DeviceConnectingDialog(context: Context) : CommonCenterDialog<HomeDialogDeviceConnectingBinding>(context) {

    override fun getImplLayoutId(): Int {
        return R.layout.home_dialog_device_connecting
    }

    override fun onCreate() {
        super.onCreate()
        mBinding.ivClose.setOnClickListener{
            this.dismiss()
        }

        mBinding.btnTryAgain.setOnClickListener {
            ToastUtils.showShort("曹尼玛")
        }
    }

    override fun getViewBinding(contentView: View) = HomeDialogDeviceConnectingBinding.bind(contentView)
}