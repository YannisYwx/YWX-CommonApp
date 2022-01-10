package com.xxx.main.ui.fragment.account

import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.veepoo.main.R
import com.xxx.main.ui.widget.EmailProblemBottomSheet
import com.ywx.common.base.fragment.BaseFragment
import kotlinx.android.synthetic.main.main_fragment_su_verify_email.*
import kotlinx.android.synthetic.main.main_fragment_welcome.btnNext

/**
 * Author: YWX
 * Date: 2021/8/17 14:50
 * Description: 验证邮箱
 */
class EmailVerifyFragment : BaseFragment() {
    private lateinit var emailProblemPopup: BasePopupView


    companion object {
        fun newInstance(): EmailVerifyFragment {
            return EmailVerifyFragment()
        }
    }

    override fun initData() {
        btnNext.isEnabled = true
    }

    override fun bindListener() {
        super.bindListener()
        btnNext.setOnClickListener { fragmentStart(R.id.action_emailVerify2ChangeEmail) }
        tvSendAgain.setOnClickListener {
            emailProblemPopup = XPopup.Builder(mActivity)
                    .moveUpToKeyboard(false)
                    .enableDrag(true)
                    .isDestroyOnDismiss(true)
                    .asCustom(EmailProblemBottomSheet(mActivity!!))
                    .show()
        }
        tvChangeEmail.setOnClickListener {
            fragmentStart(R.id.action_emailVerify2ChangeEmail)
        }
    }


    override fun enableCommonBar() = true

    override fun onBindLayout() = R.layout.main_fragment_su_verify_email

}