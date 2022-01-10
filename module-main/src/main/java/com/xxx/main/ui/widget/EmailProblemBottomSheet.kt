package com.xxx.main.ui.widget

import android.content.Context
import android.view.View
import com.veepoo.main.R
import com.veepoo.main.databinding.MainViewEmailProblemBinding
import com.ywx.common.widget.CommonBottomSheet

/**
 * Author: YWX
 * Date: 2021/8/14 11:21
 * Description: 隐私协议底部弹框
 */
class EmailProblemBottomSheet(context: Context) : CommonBottomSheet<MainViewEmailProblemBinding>(context) {

    override fun getBottomContentViewId() = R.layout.main_view_email_problem

    override fun onCreate() {
        super.onCreate()
    }

    override fun getViewBinding(contentView: View) = MainViewEmailProblemBinding.bind(contentView)

}