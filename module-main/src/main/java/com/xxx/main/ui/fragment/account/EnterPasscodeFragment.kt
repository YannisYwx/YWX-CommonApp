package com.xxx.main.ui.fragment.account

import com.veepoo.main.R
import com.ywx.common.base.fragment.BaseFragment
import kotlinx.android.synthetic.main.main_fragment_su_enter_passcode.*

/**
 * Author: YWX
 * Date: 2021/8/17 14:50
 * Description: 输入密码
 */
class EnterPasscodeFragment : BaseFragment() {

    companion object {
        fun newInstance(): EnterPasscodeFragment {
            return EnterPasscodeFragment()
        }
    }

    override fun initData() {
        btnNext.isEnabled = true
    }

    override fun bindListener() {
        super.bindListener()
        btnNext.setOnClickListener {
            fragmentStart(R.id.action_enterPasscode2EmailVerify)
        }
    }

    override fun enableCommonBar() = true

    override fun onBindLayout() = R.layout.main_fragment_su_enter_passcode

}