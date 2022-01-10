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
import com.ywx.common.bean.CommonItemBean
import com.ywx.common.ext.view.applyPressedViewScale
import com.ywx.common.widget.CommonItemView

/**
 * Author: YWX
 * Date: 2021/11/4 15:03
 * Description: 手表信息
 */
class NotificationSettingsAdapter(data: List<CommonItemBean>) : BaseQuickAdapter<CommonItemBean, BaseViewHolder>(R.layout.item_common_item, data) {


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

    override fun convert(helper: BaseViewHolder, item: CommonItemBean?) {
        item?.let {
            val commonItem = helper.getView<CommonItemView>(R.id.civItem).apply {
                setData(it.iconRes!!, mContext.getString(it.labelStrRes!!), it.isShowSwitchButton, it.isShowRightArrowIcon, it.isChecked)
            }
        }
    }
}