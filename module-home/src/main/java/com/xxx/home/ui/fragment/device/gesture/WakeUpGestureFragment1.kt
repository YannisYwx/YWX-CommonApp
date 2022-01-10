package com.xxx.home.ui.fragment.device.gesture

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import android.view.ViewTreeObserver
import android.view.animation.Animation
import com.xxx.home.R
import com.xxx.home.databinding.*
import com.ywx.common.base.fragment.NestViewBindingFragment
import com.ywx.common.ext.logE
import com.ywx.common.ext.toast
import com.ywx.common.ext.view.*
import com.ywx.common.widget.ExpandableLayout
import java.util.*

/**
 * Author: YWX
 * Date: 2022/1/4 19:42
 * Description: 手势唤醒页面
 */
class WakeUpGestureFragment1 : NestViewBindingFragment<FragmentDeviceWakeupGesture1Binding>() {
    override fun getViewBinding(contentView: View): FragmentDeviceWakeupGesture1Binding {
        return FragmentDeviceWakeupGesture1Binding.bind(contentView)
    }

    lateinit var alldayContentBinding: ViewContentAlldayBinding
    lateinit var alldayHeaderBinding: ViewHeaderAlldayBinding
    lateinit var elStartTimeSet: ExpandableLayout
    lateinit var elEndTimeSet: ExpandableLayout
    lateinit var allDayTimeSetView:View
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
//        postDelayed(
//                Runnable {
//                    val vStub: ViewStub = mBinding.root.findViewById(R.id.viewStub)
//                    allDayTimeSetView = vStub.inflate()
//                    allDaySettingBinding  = ViewAlldayTimeSettingsBinding.bind(allDayTimeSetView)
//                    initState()
//                },delayMillis = 400)
    }

    private fun initState(){
        mBinding.civWakeUpGesture.switchButton.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                allDaySettingBinding.elAllDay.expand()
            } else {
                allDaySettingBinding.elAllDay.collapse()
            }
        }

        allDaySettingBinding.civAllDay.switchButton.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                allDaySettingBinding.elStartEndTime.expand()
            } else {
                allDaySettingBinding.elStartEndTime.collapse()
            }
        }

//        allDaySettingBinding.tvStartTimeValue.setOnClickListener {
//            allDaySettingBinding.rlStartTime.visibleOrGone()
//        }
//
//        allDaySettingBinding.tvEndTimeValue.setOnClickListener {
//            allDaySettingBinding.rlEndTime.visibleOrGone()
//        }
    }

    override fun initData() {
        super.initData()
        mBinding.civWakeUpGesture.switchButton.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                mBinding.elAllDay.expand()
            } else {
                mBinding.elAllDay.collapse()
            }
        }

        mBinding.civAllDay.switchButton.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                mBinding.elStartEndTime.expand()
            } else {
                mBinding.elStartEndTime.collapse()
            }
        }

        mBinding.tvStartTimeValue.setOnClickListener {
            mBinding.elStartTime.toggle()
//            mBinding.elStartTime.toggle()
        }

        mBinding.tvEndTimeValue.setOnClickListener {
            mBinding.elEndTime.toggle()
//            mBinding.elEndTime.toggle()
        }

    }

    override fun onBindLayout() = R.layout.fragment_device_wakeup_gesture1

    override fun enableCommonBar(): Boolean {
        return true
    }
}