package com.xxx.main.ui.fragment

import android.view.View
import com.lxj.xpopup.core.BasePopupView
import com.veepoo.main.R
import com.xxx.main.ui.widget.DataPrivacyBottomSheet
import com.ywx.common.base.fragment.BaseFragment
import com.ywx.common.event.EventCode
import com.ywx.common.event.FragmentEvent

import kotlinx.android.synthetic.main.main_fragment_welcome.*

/**
 * Author: YWX
 * Date: 2021/8/17 14:26
 * Description: 欢迎页面
 */
class WelcomeFragment : BaseFragment(), View.OnClickListener {

    private lateinit var privacyPopup: BasePopupView

    companion object {
        fun newInstance(): WelcomeFragment {
            return WelcomeFragment()
        }
    }

    override fun initData() {
        btnNext.isEnabled = false
    }

    override fun isLightMode(): Boolean {
        return true
    }

    override fun bindListener() {
        super.bindListener()
        btnNext.setOnClickListener(this)
        tvPrivacy.setOnClickListener(this)
    }

    override fun onBindLayout() = R.layout.main_fragment_welcome

    override fun onClick(v: View?) {
        if (v == btnNext) {
            fragmentStart(R.id.action_welcome2SingUp)
        } else if (v == tvPrivacy) {
            privacyPopup = showBottomSheet(context = requireContext, bottomSheet = DataPrivacyBottomSheet(mActivity!!))
        }
    }

    override fun onEvent(event: FragmentEvent?) {
        super.onEvent(event)
        event?.let {
            when (event.code) {
                EventCode.Main.CLICK_ACCPET -> {
                    ivCheckBox.isSelected = true
                    btnNext.isEnabled = true
                }
            }
        }
    }
}