package com.ywx.common.net.state

import com.ywx.common.callback.livedata.event.EventLiveData

/**
 * Author: YWX
 * Date: 2021/10/10 17:32
 * Description:网络变化管理者
 */
class NetworkStateManager private constructor() {

    val mNetworkStateCallback = EventLiveData<NetState>()

    companion object {
        val instance: NetworkStateManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NetworkStateManager()
        }
    }

}