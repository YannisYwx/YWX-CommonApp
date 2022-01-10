package com.ywx.common.dialog

import android.content.Context
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.ywx.common.R

/**
 *  @author : WX.Y
 *  date : 2021/3/26 17:11
 *  description :
 */
class DialogUtils {
    companion object {
        const val DEF_DIALOG_WIDTH = 311 * 2.88
        const val DEF_DIALOG_HEIGHT = 315 * 2.88
        const val DEF_DIALOG_HEIGHT_NO_TIPS = 280 * 2.88

        @JvmStatic
        fun showTakePhotoDialog(context: Context, listener: OnTakePhotoDialogChoiceListener) {
            XPopup.Builder(context)
                    .hasBlurBg(false)
                    .hasShadowBg(true)
                    .moveUpToKeyboard(true)
                    .asCustom(TakePhotoPopup(context).setOnTakePhotoDialogChoiceListener(listener))
                    .show()
        }

        /**
         * 显示没有网络的弹框
         *
         * @param context
         */
        @JvmStatic
        fun showNoNetworkDialog(context: Context, listener: VPCenterDialog.OnVPCenterDialogListener? = null): BasePopupView = run {
            val noNetworkPop = VPCenterDialog(context).apply {
                ivInfoRes = R.mipmap.popup_icon_phone_offline
                isOnlyShowCenterButton = true
                btnCenterRes = R.string.ani_general_action_ok
                noticeRes = R.string.ani_general_alert_offline_title
                tipsRes = R.string.ani_general_alert_offline_content
                this.listener = listener
            }
            pop(context, noNetworkPop)
        }

        /**
         * 显示加载超时的弹框
         *
         * @param context
         */
        @JvmStatic
        fun showLoadingTimeoutDialog(context: Context, listener: VPCenterDialog.OnVPCenterDialogListener? = null): BasePopupView = run {
            val noNetworkPop = VPCenterDialog(context).apply {
                ivInfoRes = R.mipmap.popup_icon_phone_timeout
                isOnlyShowCenterButton = true
                btnCenterRes = R.string.ani_general_action_ok
                noticeRes = R.string.ani_general_alert_offline_title
                tipsRes = R.string.ani_general_alert_offline_content
                this.listener = listener
            }
            pop(context, noNetworkPop)
        }

        /**
         * 显示连接断开的弹框
         *
         * @param context
         */
        @JvmStatic
        fun showConnectionLostDialog(context: Context, listener: VPCenterDialog.OnVPCenterDialogListener? = null): BasePopupView = run {
            val connectionLostDialog = VPCenterDialog(context).apply {
                ivInfoRes = R.mipmap.popup_icon_lostconnection_alert
                isOnlyShowCenterButton = true
                btnCenterRes = R.string.ani_general_action_ok
                noticeRes = R.string.ani_general_alert_connectionlost_title
                tipsRes = R.string.ani_general_alert_connectionlost_content
                this.listener = listener
            }
            pop(context, connectionLostDialog)
        }

        /**
         * 传输在线表盘错误弹框
         *
         * @param context
         */
        @JvmStatic
        fun showTransferOnlineFaceErrorDialog(context: Context, listener: VPCenterDialog.OnVPCenterDialogListener? = null): BasePopupView = run {
            val transferErrorDialog = VPCenterDialog(context).apply {
                ivInfoRes = R.mipmap.popup_icon_watchface_transfer_wrong
                isOnlyShowCenterButton = true
                btnCenterRes = R.string.ani_general_action_ok
                noticeRes = R.string.ani_watchface_transfer_goeswrong_title
                tipsRes = 0
                this.listener = listener
            }
            pop(context, transferErrorDialog)
        }

        /**
         * 传输在线表盘错误弹框
         *
         * @param context
         */
        @JvmStatic
        fun showTurnOnSilentModeDialog(context: Context, listener: VPCenterDialog.OnVPCenterDialogListener? = null): BasePopupView = run {
            val turnOnSilentModeDialog = VPCenterDialog(context).apply {
                ivInfoRes = R.mipmap.popup_icon_device_silentmode
                isOnlyShowCenterButton = false
                btnLeftRes = R.string.ani_general_action_cancel
                btnRightRes = R.string.ani_general_action_turnon
                noticeRes = R.string.ani_device_silentmode_title
                tipsRes = R.string.ani_device_silentmode_content
                this.listener = listener
            }
            pop(context, turnOnSilentModeDialog)
        }

        /**
         * 搜索设备失败的弹框
         *
         * @param context
         */
        @JvmStatic
        fun showLookForDeviceFailedDialog(context: Context, listener: VPCenterDialog.OnVPCenterDialogListener? = null): BasePopupView = run {
            val lookForDeviceFailedDialog = VPCenterDialog(context).apply {
                ivInfoRes = R.mipmap.popup_icon_lookingfor_failed
                isOnlyShowCenterButton = true
                btnCenterRes = R.string.ani_general_action_tryagain
                noticeRes = R.string.ani_device_silentmode_title
                tipsRes = R.string.ani_device_silentmode_content
                this.listener = listener
            }
            pop(context, lookForDeviceFailedDialog)
        }

        /**
         * 年龄有误的弹框
         *
         * @param context
         */
        @JvmStatic
        fun showAgeWarnDialog(context: Context, listener: VPCenterDialog.OnVPCenterDialogListener? = null): BasePopupView = run {
            val lookForDeviceFailedDialog = VPCenterDialog(context).apply {
                ivInfoRes = R.mipmap.popup_icon_age_warn
                isOnlyShowCenterButton = false
                btnLeftRes = R.string.ani_general_action_leave
                btnRightRes = R.string.picture_confirm
                noticeRes = R.string._stub_notice //You seem too young
                tipsRes = R.string._stub_tips //Confirm that you have obtained the consent of your guardian
                this.listener = listener
            }
            pop(context, lookForDeviceFailedDialog)
        }

        /**
         * 显示允许通知推送的弹框
         *
         * @param context
         */
        @JvmStatic
        fun showAllowNotificationsDialog(context: Context, listener: VPCenterDialog.OnVPCenterDialogListener? = null): BasePopupView = run {
            val lookForDeviceFailedDialog = VPCenterDialog(context).apply {
                ivInfoRes = R.mipmap.popup_icon_permission_notification
                isOnlyShowCenterButton = true
                btnCenterRes = R.string.ani_general_action_dontallow // Allow
                noticeRes = R.string.ani_device_notifications_title
                tipsRes = R.string.ani_checklist_notification_content
                this.listener = listener
            }
            pop(context, lookForDeviceFailedDialog)
        }


        /**
         * 显示允最新版本的弹框
         *
         * @param context
         */
        @JvmStatic
        fun showFirmwareLatestVersionDialog(context: Context, listener: VPCenterDialog.OnVPCenterDialogListener? = null): BasePopupView = run {
            val lookForDeviceFailedDialog = VPCenterDialog(context).apply {
                ivInfoRes = R.mipmap.popup_icon_firmware_latest
                isOnlyShowCenterButton = true
                btnCenterRes = R.string.ani_general_action_dontallow
                noticeRes = R.string._stub_notice //It’s the latest version
                this.listener = listener
                isShowCloseButton = false
            }
            pop(context, lookForDeviceFailedDialog)
        }

        /**
         * 固件更新的弹框
         *
         * @param context
         */
        @JvmStatic
        fun showFirmwareUpdateDialog(context: Context, listener: VPCenterDialog.OnVPCenterDialogListener? = null): BasePopupView = run {
            val lookForDeviceFailedDialog = VPCenterDialog(context).apply {
                ivInfoRes = R.mipmap.popup_icon_firmware_update
                isOnlyShowCenterButton = false
                isShowCloseButton = false
                btnLeftRes = R.string.ani_general_action_cancel
                btnRightRes = R.string.ani_device_updatefirm_title
                noticeRes = R.string.ani_device_updatefirm_title
                tipsRes = R.string._stub_tips //A new firmware for your device
                this.listener = listener
            }
            pop(context, lookForDeviceFailedDialog)
        }

        /**
         * 删除设备的弹框
         *
         * @param context
         */
        @JvmStatic
        fun showEraseDeviceDialog(context: Context, listener: VPCenterDialog.OnVPCenterDialogListener? = null): BasePopupView = run {
            val lookForDeviceFailedDialog = VPCenterDialog(context).apply {
                ivInfoRes = R.mipmap.popup_icon_device_erase
                btnLeftRes = R.string.ani_general_action_cancel
                btnRightRes = R.string.ani_device_erasedevice_title
                noticeRes = R.string.ani_device_erasedevice_title
                tipsRes = R.string._stub_tips //A new firmware for your device
                this.listener = listener
            }
            pop(context, lookForDeviceFailedDialog)
        }

        /**
         * 设备关机的弹框
         *
         * @param context
         */
        @JvmStatic
        fun showDevicePowerOffDialog(context: Context, listener: VPCenterDialog.OnVPCenterDialogListener? = null): BasePopupView = run {
            val lookForDeviceFailedDialog = VPCenterDialog(context).apply {
                ivInfoRes = R.mipmap.popup_icon_device_poweroff
                btnLeftRes = R.string.ani_general_action_cancel
                btnRightRes = R.string.ani_device_poweroff_title
                noticeRes = R.string.ani_device_poweroff_title
                isShowCloseButton = false
                this.listener = listener
            }
            pop(context, lookForDeviceFailedDialog)
        }

        /*弹弹弹*/
        private fun pop(context: Context, popupView: VPCenterDialog, dialogWidth: Int = DEF_DIALOG_WIDTH.toInt(), dialogHeight: Int = DEF_DIALOG_HEIGHT.toInt()): BasePopupView {
            return XPopup.Builder(context)
                    .moveUpToKeyboard(false)
                    .isDestroyOnDismiss(true)
                    .isViewMode(false)
                    .dismissOnTouchOutside(false)
                    .popupWidth(dialogWidth)
                    .popupHeight(if (!popupView.isNoTips()) dialogHeight else DEF_DIALOG_HEIGHT_NO_TIPS.toInt())
                    .asCustom(popupView)
                    .show()
        }
    }

    interface OnTakePhotoDialogChoiceListener {
        /**
         * 通过相机选择图片
         */
        fun onSelectCamera()

        /**
         * 通过相册选择
         */
        fun onSelectGallery()
    }
}