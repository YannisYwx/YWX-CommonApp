package com.xxx.home.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ClickUtils
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.xxx.home.R
import com.xxx.home.bean.DeviceInfoItem
import com.xxx.home.bean.DialFace
import com.ywx.common.ext.view.applyPressedViewScale

/**
 * Author: YWX
 * Date: 2021/11/4 15:03
 * Description: 手表信息
 */
class WatchInfoAdapter(data: List<DeviceInfoItem>) : BaseMultiItemQuickAdapter<DeviceInfoItem, BaseViewHolder>(data) {

    init {
        addItemType(DeviceInfoItem.ITEM_TYPE_HEAD, R.layout.home_view_device_header)
        addItemType(DeviceInfoItem.ITEM_TYPE_QUICK_ACTION, R.layout.home_view_device_quick_actions)
        addItemType(DeviceInfoItem.ITEM_TYPE_DIAL_FACE, R.layout.home_view_device_dial_faces)
        addItemType(DeviceInfoItem.ITEM_TYPE_DISPLAY, R.layout.home_view_device_screen_display)
        addItemType(DeviceInfoItem.ITEM_TYPE_FUNCTION, R.layout.home_view_device_common_function)
        addItemType(DeviceInfoItem.ITEM_TYPE_HEALTH, R.layout.home_view_device_health_monitor)
        addItemType(DeviceInfoItem.ITEM_TYPE_SYSTEM_SETTINGS, R.layout.home_view_device_system)
    }

    override fun convert(helper: BaseViewHolder, item: DeviceInfoItem?) {
        item?.let {
            when (it.itemType) {
                DeviceInfoItem.ITEM_TYPE_HEAD -> {

                }
                DeviceInfoItem.ITEM_TYPE_QUICK_ACTION -> {
                    val v1: View = helper.getView(R.id.ivQA1)
                    val v2: View = helper.getView(R.id.ivQA2)
                    val v3: View = helper.getView(R.id.ivQA3)
                    helper.addOnClickListener(R.id.ivQA1, R.id.ivQA2, R.id.ivQA3)
                    val views: Array<View> = arrayOf(v1, v2, v3)
                    val s = floatArrayOf(-0.25f, -0.25f, -0.2f)
                    ClickUtils.applyPressedViewScale(views, s)

                }
                DeviceInfoItem.ITEM_TYPE_DIAL_FACE -> {
                    helper.addOnClickListener(R.id.btnEdit)
                    helper.applyPressedViewScale(R.id.btnEdit)
                    initDialFaceViews(helper)
                }
                DeviceInfoItem.ITEM_TYPE_DISPLAY -> {
                    helper.addOnClickListener(R.id.btnEdit, R.id.civWakeupGesture)
                }
                DeviceInfoItem.ITEM_TYPE_HEALTH -> {
                    helper.addOnClickListener(R.id.civHeartHealth, R.id.civRespiratory, R.id.civSleep)
                }
                DeviceInfoItem.ITEM_TYPE_SYSTEM_SETTINGS -> {
                    helper.addOnClickListener(R.id.civUpdate, R.id.civDisconnect, R.id.civForgetDevice, R.id.civEraseDevice, R.id.civPowerOff)
                }
                DeviceInfoItem.ITEM_TYPE_FUNCTION -> {
                    helper.addOnClickListener(R.id.civAlarm, R.id.civCamera, R.id.civNotifications)
                }
                else -> {
                }
            }
        }
    }

    private fun initDialFaceViews(helper: BaseViewHolder) {
        val rvDialFace = helper.getView(R.id.rvDialFaces) as RecyclerView
        val faces = arrayListOf<DialFace>()
        faces.add(DialFace(R.mipmap.local_face_0, "1"))
        faces.add(DialFace(R.mipmap.local_face_1, "1"))
        faces.add(DialFace(R.mipmap.local_face_2, "1"))
        faces.add(DialFace(R.mipmap.local_face_1, "1"))
        faces.add(DialFace(R.mipmap.local_face_1, "1"))
        val adapter = DialFacesAdapter(faces)
        rvDialFace.adapter = adapter
        adapter.onItemClickListener = OnWatchFaceSelectListener(faces)
    }


    /**
     * 表盘选择的监听
     * @property data
     */
    inner class OnWatchFaceSelectListener(var data: ArrayList<DialFace>) : OnItemClickListener {

        override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
            for (i in 0 until data.size) {
                data[i].isSelect = i == position
            }
            adapter?.notifyDataSetChanged()
        }

    }
}