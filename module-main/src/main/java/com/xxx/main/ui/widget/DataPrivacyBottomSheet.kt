package com.xxx.main.ui.widget

import android.content.Context
import android.view.View
import com.lxj.xpopup.core.BottomPopupView
import com.lxj.xpopup.util.XPopupUtils
import com.veepoo.main.R
import com.veepoo.main.databinding.MainViewDataPrivacyBinding
import com.ywx.common.event.EventCode
import com.ywx.common.event.FragmentEvent
import com.ywx.common.widget.CommonBottomSheet

import kotlinx.android.synthetic.main.main_view_data_privacy.view.*
import org.greenrobot.eventbus.EventBus

/**
 * Author: YWX
 * Date: 2021/8/14 11:21
 * Description: 隐私协议底部弹框
 */
class DataPrivacyBottomSheet(context: Context) : CommonBottomSheet<MainViewDataPrivacyBinding>(context) {

    override fun onRightBarClick() {
        super.onRightBarClick()
        EventBus.getDefault().post(FragmentEvent(EventCode.Main.CLICK_ACCPET, true))
        dismiss()
    }

    override fun getBottomContentViewId() = R.layout.main_view_data_privacy

    override fun getTitleResId(): Int {
        return R.string.ani_welcome_action_data_and_privacy
    }

    override fun getRightTextResId(): Int {
        return R.string.ani_data_and_privacy_action_accept
    }

    override fun getViewBinding(contentView: View): MainViewDataPrivacyBinding {
        return MainViewDataPrivacyBinding.bind(contentView)
    }
}