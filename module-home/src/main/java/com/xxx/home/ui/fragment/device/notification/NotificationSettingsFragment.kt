package com.xxx.home.ui.fragment.device.notification

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.xxx.home.R
import com.xxx.home.databinding.FragmentDeviceNotificationSettingsBinding
import com.xxx.home.ui.adapter.NotificationSettingsAdapter
import com.ywx.common.base.fragment.ScrollTitleNestFragment
import com.ywx.common.bean.CommonItemBean
import com.ywx.common.bean.CommonItemBean.ItemType.DeviceFunctionNotify
import com.ywx.common.bean.CommonItemBean.ItemType.PhoneAppNotify
import com.ywx.common.ext.toast
import com.ywx.common.ext.plusWithNewLine

/**
 * Author: YWX
 * Date: 2022/1/7 18:19
 * Description:
 */
class NotificationSettingsFragment : ScrollTitleNestFragment<FragmentDeviceNotificationSettingsBinding>() {

    private lateinit var mDeviceFunctionAdapter: NotificationSettingsAdapter
    private lateinit var mPhoneAppAdapter: NotificationSettingsAdapter

    /**
     * 设备功能通知设置
     */
    private val deviceFunctionNotifyArray: ArrayList<CommonItemBean>
        get() = arrayListOf(
                CommonItemBean(tag = DeviceFunctionNotify.ACTIVITY, iconRes = R.mipmap.icon_step_style2_ltmode, labelStrRes = R.string.ani_watchface_abovetime_content_8calorie, isShowSwitchButton = false),
                CommonItemBean(tag = DeviceFunctionNotify.ALARM, iconRes = R.mipmap.icon_device_alarm_style2_ltmode, labelStrRes = R.string.ani_device_alarm_title, isShowSwitchButton = false),
                CommonItemBean(tag = DeviceFunctionNotify.BLOOD_OXYGEN, iconRes = R.mipmap.icon_bloodoxygen_style2_ltmode, labelStrRes = R.string.ani_data_class_bloodoxygen, isShowSwitchButton = false),
                CommonItemBean(tag = DeviceFunctionNotify.BREATH, iconRes = R.mipmap.icon_device_respiratory_style2_ltmode, labelStrRes = R.string.ani_device_notifications_breath_title, isShowSwitchButton = false),
                CommonItemBean(tag = DeviceFunctionNotify.DISCONNECT_NOTIFY, iconRes = R.mipmap.icon_status_disconnected_style2_ltmode, labelStrRes = R.string.ani_device_notifications_disconnection_title, isShowSwitchButton = true, isChecked = false),
                CommonItemBean(tag = DeviceFunctionNotify.CYCLE_TRACKING, iconRes = R.mipmap.icon_cycletracking_style2_ltmode, labelStrRes = R.string.ani_cyclet_title, isShowSwitchButton = false),
                CommonItemBean(tag = DeviceFunctionNotify.HEART_HEALTH, iconRes = R.mipmap.icon_device_heart_style2_ltmode, labelStrRes = R.string.ani_device_hearthealth_title, isShowSwitchButton = false),
                CommonItemBean(tag = DeviceFunctionNotify.SLEEP, iconRes = R.mipmap.icon_device_sleep_style2_ltmode, labelStrRes = R.string.ani_data_class_sleep, isShowSwitchButton = false)
        )

    /**
     * 设备功能通知设置
     */
    private val phoneAppNotifyArray: MutableList<CommonItemBean> = mutableListOf()

    private fun initPhoneAppNotifyArray() {
        CommonItemBean.ItemType
        phoneAppNotifyArray.clear()
        phoneAppNotifyArray.add(CommonItemBean(tag = PhoneAppNotify.PHONE_CALL, iconRes = R.mipmap.icon_notification_phone_call, labelStrRes = R.string.ani_device_notifications_app_incomingcall, isShowSwitchButton = true))
        phoneAppNotifyArray.add(CommonItemBean(tag = PhoneAppNotify.PHONE_CALL, iconRes = R.mipmap.icon_notification_sms, labelStrRes = R.string.ani_device_notifications_app_sms, isShowSwitchButton = true))
        phoneAppNotifyArray.add(CommonItemBean(tag = PhoneAppNotify.PHONE_CALL, iconRes = R.mipmap.icon_notification_connected2me, labelStrRes = R.string.ani_device_notifications_app_connected2, isShowSwitchButton = true))
        phoneAppNotifyArray.add(CommonItemBean(tag = PhoneAppNotify.PHONE_CALL, iconRes = R.mipmap.icon_notification_facebook, labelStrRes = R.string.ani_device_notifications_app_fb, isShowSwitchButton = true))
        phoneAppNotifyArray.add(CommonItemBean(tag = PhoneAppNotify.PHONE_CALL, iconRes = R.mipmap.icon_notification_gmail, labelStrRes = R.string.ani_device_notifications_app_gmail, isShowSwitchButton = true))
        phoneAppNotifyArray.add(CommonItemBean(tag = PhoneAppNotify.PHONE_CALL, iconRes = R.mipmap.icon_notification_instagram, labelStrRes = R.string.ani_device_notifications_app_ig, isShowSwitchButton = true))
        phoneAppNotifyArray.add(CommonItemBean(tag = PhoneAppNotify.PHONE_CALL, iconRes = R.mipmap.icon_notification_kakao_talk, labelStrRes = R.string.ani_device_notifications_app_kakao, isShowSwitchButton = true))
        phoneAppNotifyArray.add(CommonItemBean(tag = PhoneAppNotify.PHONE_CALL, iconRes = R.mipmap.icon_notification_line, labelStrRes = R.string.ani_device_notifications_app_line, isShowSwitchButton = true))
        phoneAppNotifyArray.add(CommonItemBean(tag = PhoneAppNotify.PHONE_CALL, iconRes = R.mipmap.icon_notification_linked_in, labelStrRes = R.string.ani_device_notifications_app_linkedin, isShowSwitchButton = true))
        phoneAppNotifyArray.add(CommonItemBean(tag = PhoneAppNotify.PHONE_CALL, iconRes = R.mipmap.icon_notification_messenger, labelStrRes = R.string.ani_device_notifications_app_messenger, isShowSwitchButton = true))
        phoneAppNotifyArray.add(CommonItemBean(tag = PhoneAppNotify.PHONE_CALL, iconRes = R.mipmap.icon_notification_qq, labelStrRes = R.string.ani_device_notifications_app_qq, isShowSwitchButton = true))
        phoneAppNotifyArray.add(CommonItemBean(tag = PhoneAppNotify.PHONE_CALL, iconRes = R.mipmap.icon_notification_skype, labelStrRes = R.string.ani_device_notifications_app_skype, isShowSwitchButton = true))
        phoneAppNotifyArray.add(CommonItemBean(tag = PhoneAppNotify.PHONE_CALL, iconRes = R.mipmap.icon_notification_talk, labelStrRes = R.string.ani_device_notifications_app_snapchat, isShowSwitchButton = true))
        phoneAppNotifyArray.add(CommonItemBean(tag = PhoneAppNotify.PHONE_CALL, iconRes = R.mipmap.icon_notification_telegram, labelStrRes = R.string.ani_device_notifications_app_telegram, isShowSwitchButton = true))
        phoneAppNotifyArray.add(CommonItemBean(tag = PhoneAppNotify.PHONE_CALL, iconRes = R.mipmap.icon_notification_tik_tok, labelStrRes = R.string.ani_device_notifications_app_tiktok, isShowSwitchButton = true))
        phoneAppNotifyArray.add(CommonItemBean(tag = PhoneAppNotify.PHONE_CALL, iconRes = R.mipmap.icon_notification_twitter, labelStrRes = R.string.ani_device_notifications_app_twitter, isShowSwitchButton = true))
        phoneAppNotifyArray.add(CommonItemBean(tag = PhoneAppNotify.PHONE_CALL, iconRes = R.mipmap.icon_notification_wechart, labelStrRes = R.string.ani_device_notifications_app_wechat, isShowSwitchButton = true))
        phoneAppNotifyArray.add(CommonItemBean(tag = PhoneAppNotify.PHONE_CALL, iconRes = R.mipmap.icon_notification_whats_app, labelStrRes = R.string.ani_device_notifications_app_whatsapp, isShowSwitchButton = true))
        phoneAppNotifyArray.add(CommonItemBean(tag = PhoneAppNotify.PHONE_CALL, iconRes = R.mipmap.icon_notification_others, labelStrRes = R.string.ani_device_notifications_app_others, isShowSwitchButton = true))
    }

    private fun initRecyclerViews() {
        initPhoneAppNotifyArray()
        mDeviceFunctionAdapter = NotificationSettingsAdapter(deviceFunctionNotifyArray)
        mPhoneAppAdapter = NotificationSettingsAdapter(phoneAppNotifyArray)
        mBinding.rvDeviceFunctionNotify.layoutManager = LinearLayoutManager(requireContext)
        mBinding.rvPhoneAppNotify.layoutManager = LinearLayoutManager(requireContext)
        mBinding.rvDeviceFunctionNotify.adapter = mDeviceFunctionAdapter
        mBinding.rvPhoneAppNotify.adapter = mPhoneAppAdapter
        mDeviceFunctionAdapter.notifyDataSetChanged()
        mPhoneAppAdapter.notifyDataSetChanged()
        mBinding.rvPhoneAppNotify.setHasFixedSize(true);
        mBinding.rvPhoneAppNotify.setNestedScrollingEnabled(false);
    }

    override fun bindListener() {
        super.bindListener()
        mDeviceFunctionAdapter.setOnItemClickListener { adapter, view, position ->
            val civ = deviceFunctionNotifyArray[position]
            when (civ.tag) {
                DeviceFunctionNotify.ACTIVITY -> {
                    fragmentStart(R.id.action_notify2Activity)
                }
                DeviceFunctionNotify.ALARM -> {

                }
                DeviceFunctionNotify.BLOOD_OXYGEN -> {

                }
                DeviceFunctionNotify.BREATH -> {

                }
                DeviceFunctionNotify.CYCLE_TRACKING -> {

                }
                DeviceFunctionNotify.HEART_HEALTH -> {

                }
                DeviceFunctionNotify.SLEEP -> {

                }
            }
        }
        mPhoneAppAdapter.setOnItemClickListener { adapter, view, position ->
            "$position ---->".toast()
        }

    }

    override fun initData() {
        super.initData()
        val deviceFunctionTips = getString(R.string.ani_device_notifications_content_1wear).plusWithNewLine(getString(R.string.ani_device_notifications_content_2view))
        mBinding.tvDeviceFunctionNotifyTips.text = deviceFunctionTips
        initRecyclerViews()
    }

    override fun onBindScrollerLayout() = R.layout.fragment_device_notification_settings

    override fun setTitleStringRes() = R.string.ani_device_notifications_title

    override fun getViewBinding(contentView: View) = FragmentDeviceNotificationSettingsBinding.bind(contentView)
}