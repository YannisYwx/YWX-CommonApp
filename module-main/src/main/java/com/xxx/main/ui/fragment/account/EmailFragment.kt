package com.xxx.main.ui.fragment.account

import android.view.View
import com.veepoo.main.R
import com.ywx.common.base.fragment.BaseFragment
import kotlinx.android.synthetic.main.main_fragment_su_email.*

/**
 * Author: YWX
 * Date: 2021/8/17 14:50
 * Description: 注册首页
 */
class EmailFragment : BaseFragment() {

    companion object {
        fun newInstance(): EmailFragment {
            return EmailFragment()
        }
    }

    override fun initData() {
        btnNext.isEnabled = true
    }

    override fun bindListener() {
        super.bindListener()
        btnNext.setOnClickListener {
            fragmentStart(R.id.action_email2CreatePasscode)
        }
    }

    override fun onBindBarRightText(): Array<String?>? = arrayOf(resources.getString(R.string.ani_general_action_skip))

    override fun onRight1Click(v: View?) {
        super.onRight1Click(v)
//        start(PersonSettingsFragment.newInstance())
    }


    override fun enableCommonBar() = true

    override fun onBindLayout() = R.layout.main_fragment_su_email

}