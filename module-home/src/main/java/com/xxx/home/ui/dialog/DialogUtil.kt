package com.xxx.home.ui.dialog

import android.content.Context
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ScreenUtils
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.util.XPopupUtils
import com.xxx.home.R
import com.ywx.common.dialog.VPCenterDialog
import com.zhouyou.http.utils.Utils

/**
 * Author: YWX
 * Date: 2021/10/29 15:20
 * Description:
 */
object DialogUtil {


    fun showToSettingProfileDialog(context: Context) {
        XPopup.Builder(context)
                .moveUpToKeyboard(false)
                .isDestroyOnDismiss(true)
                .isViewMode(false)
                .popupWidth((ScreenUtils.getAppScreenWidth() * 0.82).toInt())
                .popupHeight((ScreenUtils.getAppScreenWidth() * 0.82).toInt())
                .asCustom(SettingProfilePopupView(context))
                .show()
    }

    /**
     *
     * @param context
     */
    fun showConnectDialog(context: Context) =
            XPopup.Builder(context)
                    .moveUpToKeyboard(false)
                    .isDestroyOnDismiss(true)
                    .isViewMode(false)
                    .dismissOnTouchOutside(false)
                    .popupWidth((ScreenUtils.getAppScreenWidth() * 0.82).toInt())
                    .popupHeight((ScreenUtils.getAppScreenWidth() * 0.82).toInt())
                    .asCustom(DeviceConnectingDialog(context))
                    .show()

}