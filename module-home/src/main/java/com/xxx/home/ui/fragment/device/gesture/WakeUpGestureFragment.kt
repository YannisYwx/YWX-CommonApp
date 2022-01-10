package com.xxx.home.ui.fragment.device.gesture

import android.os.Bundle
import android.view.View
import com.xxx.home.R
import com.xxx.home.databinding.*
import com.ywx.common.base.fragment.ScrollTitleNestFragment
import com.ywx.common.ext.view.*

/**
 * Author: YWX
 * Date: 2022/1/4 19:42
 * Description: 手势唤醒页面
 */
class WakeUpGestureFragment : ScrollTitleNestFragment<FragmentDeviceWakeupGestureSettingBinding>() {
    override fun getViewBinding(contentView: View): FragmentDeviceWakeupGestureSettingBinding {
        return FragmentDeviceWakeupGestureSettingBinding.bind(contentView)
    }

    lateinit var allDaySettingBinding: ViewAlldayTimeSettingsBinding

    override fun initView() {
        super.initView()
    }


    override fun onFirstVisible() {
        super.onFirstVisible()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postDelayed(
                Runnable {
//                    val vStub: ViewStub = mBinding.root.findViewById(R.id.viewStub)
//                    allDayTimeSetView = vStub.inflate()
//                    allDaySettingBinding  = ViewAlldayTimeSettingsBinding.bind(allDayTimeSetView)
//                    initState()
                    mBinding.elAllDay.showWithAnimation()
                }, delayMillis = 500)
    }

    private fun initState() {
        mBinding.civWakeUpGesture.switchButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                allDaySettingBinding.elAllDay.expand()
            } else {
                allDaySettingBinding.elAllDay.collapse()
            }
        }

        allDaySettingBinding.civAllDay.switchButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                allDaySettingBinding.elStartEndTime.expand()
            } else {
                allDaySettingBinding.elStartEndTime.collapse()
            }
        }

        allDaySettingBinding.tvStartTimeValue.setOnClickListener {
//            allDaySettingBinding.elStartTime.toggle()
        }

        allDaySettingBinding.tvEndTimeValue.setOnClickListener {
//            allDaySettingBinding.elEndTime.toggle()
        }
    }

    override fun initData() {
        super.initData()
        mBinding.civWakeUpGesture.switchButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                mBinding.elAllDay.expand()
            } else {
                mBinding.elAllDay.collapse()
            }
        }

        mBinding.civAllDay.switchButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                mBinding.elStartEndTime.expand()
            } else {
                mBinding.elStartEndTime.collapse()
            }
        }

        mBinding.tvStartTimeValue.setOnClickListener {
            mBinding.elStartTime.toggle()
//            mBinding.rlStartTime.dismissWithAnimation1(3000)
        }

        mBinding.tvEndTimeValue.setOnClickListener {
            mBinding.elEndTime.toggle()
//            mBinding.rlEndTime.visibleOrGoneWithAnimation()
        }

    }

    override fun enableCommonBar(): Boolean {
        return true
    }

    override fun onBindScrollerLayout() = R.layout.fragment_device_wakeup_gesture_setting

    override fun setTitleStringRes() = R.string.ani_device_wakeupgesture_title
}