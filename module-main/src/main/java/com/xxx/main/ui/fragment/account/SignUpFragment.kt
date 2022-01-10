package com.xxx.main.ui.fragment.account

import android.view.View
import com.blankj.utilcode.util.BarUtils
import com.veepoo.main.R
import com.ywx.common.base.fragment.BaseFragment
import kotlinx.android.synthetic.main.main_fragment_sign_up.*

/**
 * Author: YWX
 * Date: 2021/8/17 14:50
 * Description: 注册首页
 */
class SignUpFragment : BaseFragment() {

    companion object {
        fun newInstance(): SignUpFragment {
            return SignUpFragment()
        }
    }

    override fun initData() {
    }

    override fun onBindBarRightText(): Array<String?>? = arrayOf(resources.getString(R.string.ani_general_action_skip))

    override fun onRight1Click(v: View?) {
        super.onRight1Click(v)
//        start(PersonSettingsFragment.newInstance())
    }

    override fun bindListener() {
        super.bindListener()
        btnSignUp.setOnClickListener {
            fragmentStart(R.id.action_signUp2Email)
        }
    }

    override fun onVisible() {
        super.onVisible()
        mActivity?.let { BarUtils.setStatusBarLightMode(it, true) }
    }

    override fun onReVisible() {
        super.onReVisible()
    }

    override fun enableCommonBar() = true

    override fun onBindLayout() = R.layout.main_fragment_sign_up

    override fun onBindBarBackIcon() = 0
}