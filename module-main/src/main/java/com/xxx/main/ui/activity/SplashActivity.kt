package com.xxx.main.ui.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.BarUtils
import com.veepoo.main.R
import com.ywx.common.router.RouterActivityPath

/**
 * Author: YWX
 * Date: 2021/8/10 20:30
 * Description: 启动页
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BarUtils.setStatusBarLightMode(this, true)
        Handler(Looper.getMainLooper()).postDelayed({
//            WelcomeActivity.start(this)
            ARouter.getInstance().build(RouterActivityPath.Home.PAGER_HOME).navigation()
            finish()
        }, 1500)
    }

}