package com.ywx.common.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.ywx.common.callback.livedata.BooleanLiveData

/**
 * Author: YWX
 * Date: 2021/9/16 17:39
 * Description:
 */
object KtxAppLifeObserver : LifecycleObserver {

    var isForeground = BooleanLiveData()

    //在前台
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private  fun onForeground() {
        isForeground.value = true
    }

    //在后台
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun onBackground() {
        isForeground.value = false
    }

}