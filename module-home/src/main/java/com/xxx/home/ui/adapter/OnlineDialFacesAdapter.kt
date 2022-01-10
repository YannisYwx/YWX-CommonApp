package com.xxx.home.ui.adapter

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.xxx.home.R
import com.xxx.home.bean.DialFace
import com.ywx.common.event.EventCode.Device.Companion.LOCAL_DIAL_FACE_CLICK
import com.ywx.common.event.FragmentEvent
import com.ywx.common.net.model.OnlineDialFace
import org.greenrobot.eventbus.EventBus

/**
 * Author: YWX
 * Date: 2021/11/9 20:04
 * Description: 在线表盘
 */
class OnlineDialFacesAdapter(data: List<OnlineDialFace>, private var isShowLabel: Boolean = true) : BaseQuickAdapter<OnlineDialFace, BaseViewHolder>(R.layout.home_item_dial_face, data) {

    init {
//        this.onItemClickListener = OnWatchFaceSelectListener(this.data)
    }

    var isFillGrid: Boolean = false

    override fun convert(helper: BaseViewHolder, item: OnlineDialFace?) {
        item?.let {
//            if (it.id > 0) {
//                helper.setImageResource(R.id.ivDialFace, it.id)
//            }
//            helper.setVisible(R.id.ivCheck, it.isSelect)
//            helper.setSelect(R.id.ivDialFaceBg, it.isSelect)
//            helper.setSelect(R.id.tvFaceName, it.isSelect)
//            helper.setGone(R.id.tvFaceName, isShowLabel)
//            helper.itemView.layoutParams = ViewGroup.LayoutParams(if (isFillGrid) ViewGroup.LayoutParams.MATCH_PARENT else ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
    }

    inner class OnWatchFaceSelectListener(var data: List<DialFace>) : OnItemClickListener {

        override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
            for (i in data.indices) {
                data[i].isSelect = i == position
            }
            adapter?.notifyDataSetChanged()
            EventBus.getDefault().post(FragmentEvent(LOCAL_DIAL_FACE_CLICK, position))
        }

    }

}