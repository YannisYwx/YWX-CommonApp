package com.xxx.home.ui.fragment.device

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.AdaptScreenUtils
import com.blankj.utilcode.util.ColorUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.lxj.xpopup.core.BasePopupView
import com.xxx.home.R
import com.xxx.home.bean.DeviceInfoItem
import com.xxx.home.databinding.HomeFragmentDeviceBinding
import com.xxx.home.databinding.HomeViewNoDeviceBinding
import com.xxx.home.ui.adapter.WatchInfoAdapter
import com.xxx.home.ui.dialog.FindDeviceBottomSheet
import com.xxx.home.ui.dialog.SearchDeviceBottomSheet
import com.ywx.common.base.IBaseView
import com.ywx.common.base.fragment.NestViewBindingFragment
import com.ywx.common.dialog.DialogUtils
import com.ywx.common.dialog.VPCenterDialog
import com.ywx.common.ext.show

/**
 * Author: YWX
 * Date: 2021/9/13 15:30
 * Description: 【今天】页面
 */
class DeviceFragment : NestViewBindingFragment<HomeFragmentDeviceBinding>() {

    private lateinit var mNoDeviceBinding: HomeViewNoDeviceBinding

    lateinit var mAdapter: WatchInfoAdapter

    private lateinit var mDeviceInfoItems: MutableList<DeviceInfoItem>

    private lateinit var mItemClick: OnWatchInfoItemClick

    var isShowLastVersionDialog = true

    override fun initData() {
        mItemClick = OnWatchInfoItemClick()
        mNoDeviceBinding = HomeViewNoDeviceBinding.inflate(layoutInflater)
        mDeviceInfoItems = arrayListOf(DeviceInfoItem(DeviceInfoItem.ITEM_TYPE_HEAD),
                DeviceInfoItem(DeviceInfoItem.ITEM_TYPE_QUICK_ACTION),
                DeviceInfoItem(DeviceInfoItem.ITEM_TYPE_DIAL_FACE),
                DeviceInfoItem(DeviceInfoItem.ITEM_TYPE_DISPLAY),
                DeviceInfoItem(DeviceInfoItem.ITEM_TYPE_FUNCTION),
                DeviceInfoItem(DeviceInfoItem.ITEM_TYPE_HEALTH),
                DeviceInfoItem(DeviceInfoItem.ITEM_TYPE_SYSTEM_SETTINGS))
//        mDeviceInfoItems.clear()
        mAdapter = WatchInfoAdapter(mDeviceInfoItems)
        mAdapter.emptyView = mNoDeviceBinding.root
        mBinding.rvDevice.layoutManager = LinearLayoutManager(requireContext)
        mBinding.rvDevice.adapter = mAdapter
        setTitle(arrayOf("Wow's RIG"))
        mCommonTitleBar.rightCustomView
    }

    override fun onBindBarRightIcon(): Array<Int?>? {
        return arrayOf(com.ywx.res.R.mipmap.icon_search)
    }

    override fun onBindBarRightStyle() = IBaseView.CommonBarStyle.RIGHT_ICON

    override fun bindListener() {
        super.bindListener()
        mAdapter.onItemChildClickListener = mItemClick
        mNoDeviceBinding.btnAddDevice.setOnClickListener {
            showBottomSheet(context = this.requireContext, bottomSheet = SearchDeviceBottomSheet(mActivity!!))
        }
        mBinding.rvDevice.addItemDecoration(DividerItemDecoration(requireContext, DividerItemDecoration.VERTICAL))
        mBinding.rvDevice.setOnScrollChangeListener { _, _, _, _, _ ->
            val offset: Int = mBinding.rvDevice.computeVerticalScrollOffset()
            val distance = AdaptScreenUtils.pt2Px(80.64f).toFloat()
            var alpha = 1.0f
            if (offset < distance) {
                alpha = (offset.toFloat() / distance)
            }
            titleView?.setTextColor(ColorUtils.setAlphaComponent(Color.parseColor("#333A47"), alpha))
        }
    }

    override fun onBindLayout() = R.layout.home_fragment_device

    override fun getViewBinding(contentView: View) = HomeFragmentDeviceBinding.bind(contentView)

    override fun enableCommonBar(): Boolean {
        return true
    }

    override fun isLeftIconEnable() = false

    inner class OnWatchInfoItemClick : BaseQuickAdapter.OnItemChildClickListener {
        override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
            view?.let {
                when (it.id) {
                    R.id.btnEdit -> {
                        fragmentStart(R.id.action_device2EditDialFace)
                    }
                    R.id.civAlarm -> {

                    }
                    R.id.civCamera -> {

                    }
                    R.id.civNotifications -> {
                        fragmentStart(R.id.action_device2NotificationSettings)
                    }
                    R.id.civWakeupGesture -> {
                        fragmentStart(R.id.action_device2WakeupGesture)
                    }
                    R.id.civHeartHealth -> {
                        fragmentStart(R.id.action_device2heartHealthSettings)
                    }
                    R.id.civRespiratory -> {
                        fragmentStart(R.id.action_device2breathSettings)
                    }
                    R.id.civSleep -> {
                        fragmentStart(R.id.action_device2SleepSettings)
                    }
                    R.id.civUpdate -> {
                        isShowLastVersionDialog = if (isShowLastVersionDialog) {
                            DialogUtils.showFirmwareLatestVersionDialog(requireContext)
                            false
                        } else {
                            DialogUtils.showFirmwareUpdateDialog(requireContext, object : VPCenterDialog.OnVPCenterDialogListener {
                                override fun onDialogConfirmListener() {
                                    super.onDialogConfirmListener()
                                    fragmentStart(R.id.action_device2FirmwareUpdateTips)
                                }
                            })
                            true
                        }
                    }
                    R.id.civDisconnect -> {

                    }
                    R.id.civForgetDevice -> {

                    }
                    R.id.civEraseDevice -> {
                        DialogUtils.showEraseDeviceDialog(requireContext, object : VPCenterDialog.OnVPCenterDialogListener {
                            override fun onDialogConfirmListener() {
                                super.onDialogConfirmListener()
                                "擦擦擦......!!!".show()
                            }
                        })
                    }
                    R.id.civPowerOff -> {
                        DialogUtils.showDevicePowerOffDialog(requireContext, object : VPCenterDialog.OnVPCenterDialogListener {
                            override fun onDialogCancelListener() {
                                super.onDialogCancelListener()
                                "不关机".show()
                            }

                            override fun onDialogConfirmListener() {
                                super.onDialogConfirmListener()
                                "关机".show()
                            }
                        })
                    }
                    R.id.ivQA1 -> {
                        showBottomSheet(context = requireContext, bottomSheet = FindDeviceBottomSheet(requireContext))
                    }
                    R.id.ivQA2 -> {

                    }
                    R.id.ivQA3 -> {

                    }

                    else -> {
                        showToast("你点了什么")
                    }
                }
            }
        }
    }
}