package com.xxx.home.ui.activity

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.alibaba.android.arouter.facade.annotation.Route
import com.xxx.home.R
import com.xxx.home.databinding.HomeActivityBinding
import com.ywx.common.base.activity.BaseViewBindingActivity
import com.ywx.common.ext.logE
import com.ywx.common.router.RouterActivityPath

/**
 * Author: YWX
 * Date: 2021/9/13 11:44
 * Description: Home页面：今日，设备，个人信息
 */
@Route(path = RouterActivityPath.Home.PAGER_HOME)
class HomeActivity : BaseViewBindingActivity<HomeActivityBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getViewBinding(contentView: View): HomeActivityBinding {
        return HomeActivityBinding.bind(contentView)
    }

    override fun initData() {
        setUpNavigation()
    }

    override fun onBindLayout(): Int {
        return R.layout.home_activity
    }

    private fun setUpNavigation() {
        val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        setupWithNavController(mBinding.bnvHome,
                navHostFragment!!.navController)
    }

    override fun enableCommonBar(): Boolean {
        return false
    }

}