package com.xxx.main.ui.fragment.account

import android.animation.Animator
import android.animation.ObjectAnimator
import com.veepoo.main.R
import com.ywx.common.base.fragment.BaseFragment
import kotlinx.android.synthetic.main.main_fragment_account_establish.*


/**
 * Author: YWX
 * Date: 2021/8/17 14:50
 * Description: 账号创建成功界面
 */
class AccountEstablishFragment : BaseFragment() {

    companion object {
        fun newInstance(): AccountEstablishFragment {
            return AccountEstablishFragment()
        }
    }

    override fun initData() {
        btnNext.isEnabled = false
        btnNext.alpha = 0.0f
    }

    override fun bindListener() {
        super.bindListener()
        btnNext.setOnClickListener {
            fragmentStart(R.id.action_accountEstablish2PersonSettings)
        }
    }

    override fun onFirstVisible() {
        super.onFirstVisible()
        postDelayed(Runnable { startDismissAnim() },
                3_000)
    }

    private fun startDismissAnim() {
        val alphaAnim = ObjectAnimator.ofFloat(tvAccountTips, "alpha", 1.0f, 0.0f)
        alphaAnim.duration = 8_00
        alphaAnim.start()
        alphaAnim.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {
            }

            override fun onAnimationEnd(p0: Animator?) {
                startShowAnim()
            }

            override fun onAnimationCancel(p0: Animator?) {
            }

            override fun onAnimationStart(p0: Animator?) {
            }

        })
    }

    private fun startShowAnim() {
        tvAccountTips.setText(R.string.ani_profile_initial_title)
        val alphaAnim = ObjectAnimator.ofFloat(tvAccountTips, "alpha", 0.0f, 1.0f)
        alphaAnim.duration = 8_00
        alphaAnim.start()

        val alphaAnimBtn = ObjectAnimator.ofFloat(btnNext, "alpha", 0.0f, 1.0f)
        alphaAnimBtn.duration = 8_00
        alphaAnimBtn.start()
        btnNext.isEnabled = true
    }


    override fun enableCommonBar() = true

    override fun onBindLayout() = R.layout.main_fragment_account_establish

}