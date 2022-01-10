package com.xxx.main.ui.fragment.permission

import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.veepoo.main.R
import com.veepoo.main.databinding.MainFragmentAllDoneBinding
import com.ywx.common.base.fragment.BaseViewBindingFragment
import com.ywx.common.router.RouterActivityPath

/**
 * Author: YWX
 * Date: 2021/9/2 16:03
 * Description: all done
 */
class AllDoneFragment : BaseViewBindingFragment<MainFragmentAllDoneBinding>() {

    companion object {
        fun newInstance(): AllDoneFragment {
            return AllDoneFragment()
        }
    }

    override fun getViewBinding(contentView: View): MainFragmentAllDoneBinding {
        return MainFragmentAllDoneBinding.bind(contentView)
    }

    override fun initData() {
    }

    override fun bindListener() {
        super.bindListener()
        mBinding.btnDone.setOnClickListener {
            ARouter.getInstance().build(RouterActivityPath.Home.PAGER_HOME).navigation()
            mActivity?.finish()
        }
    }

    override fun onBindLayout() = R.layout.main_fragment_all_done

    override fun enableCommonBar() = true
}