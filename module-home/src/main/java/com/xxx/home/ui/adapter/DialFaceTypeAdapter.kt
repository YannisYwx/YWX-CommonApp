package com.xxx.home.ui.adapter

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.xxx.home.R
import com.xxx.home.bean.DialFaceType

/**
 * Author: YWX
 * Date: 2021/11/11 18:11
 * Description: 表盘类型
 */
class DialFaceTypeAdapter(var types: MutableList<DialFaceType>) : BaseQuickAdapter<DialFaceType, BaseViewHolder>(R.layout.item_dial_face_type, types) {

    override fun convert(helper: BaseViewHolder, item: DialFaceType?) {
        item?.let {
            helper.setGone(R.id.ivImg, it.imgResId != -1L)
            helper.setText(R.id.tvLabel, it.faceType)
            helper.itemView.isSelected = it.isSelect
            val v = helper.getView<TextView>(R.id.tvLabel)
            v.isSelected = it.isSelect
        }
    }

    public fun setSelectItem(position: Int) {
        for (i in 0 until data.size) {
            data[i].isSelect = position == i
        }
        notifyDataSetChanged()
    }
}