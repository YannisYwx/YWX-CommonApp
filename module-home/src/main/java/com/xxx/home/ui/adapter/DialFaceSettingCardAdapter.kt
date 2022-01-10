package com.xxx.home.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ResourceUtils
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.xxx.home.R
import com.xxx.home.bean.DialColor
import com.xxx.home.bean.DialSettingCard
import com.xxx.home.ui.widget.CircleView
import com.ywx.common.ext.setViewPadding
import com.ywx.common.ext.toArrayList
import com.ywx.common.widget.wheelview.WheelView
import com.ywx.common.widget.wheelview.adapter.ArrayWheelAdapter

/**
 * Author: YWX
 * Date: 2021/11/4 15:03
 * Description: 表盘设置卡片
 */
class DialFaceSettingCardAdapter(data: List<DialSettingCard>, val listener: OnDialFaceSettingsChangedListener) : BaseMultiItemQuickAdapter<DialSettingCard, BaseViewHolder>(data) {

    init {
        addItemType(DialSettingCard.ITEM_TYPE_COLOR_DOT, R.layout.item_card_color_palette)
        addItemType(DialSettingCard.ITEM_TYPE_TIME_POSITION, R.layout.item_card_select_element)
        addItemType(DialSettingCard.ITEM_TYPE_ABOVE_TIME, R.layout.item_card_select_element)
        addItemType(DialSettingCard.ITEM_TYPE_BELOW_TIME, R.layout.item_card_select_element)
    }

    /**
     * 时间位置
     */
    private val timePositionArray: Array<String>
        get() = mContext.resources.getStringArray(R.array.timePosition)

    /**
     * 时间上方
     */
    private val aboveTimeArray: Array<String>
        get() = mContext.resources.getStringArray(R.array.dialFaceElement)

    /**
     * 时间下方
     */
    private val belowTimeArray: Array<String>
        get() = mContext.resources.getStringArray(R.array.dialFaceElement)

    override fun convert(helper: BaseViewHolder, item: DialSettingCard?) {
        item?.let {
            when (it.itemType) {
                DialSettingCard.ITEM_TYPE_COLOR_DOT -> {
                    initDialColorViews(helper, item)
                }
                DialSettingCard.ITEM_TYPE_TIME_POSITION -> {
                    initWheelPickerView(helper, item)
                }
                DialSettingCard.ITEM_TYPE_ABOVE_TIME -> {
                    initWheelPickerView(helper, item)
                }
                DialSettingCard.ITEM_TYPE_BELOW_TIME -> {
                    initWheelPickerView(helper, item)
                    helper.itemView.setViewPadding(start = 11.52.toInt(), end = (11.52 * 9).toInt())
                }

                else -> {
                }
            }
        }
    }

    private fun initDialColorViews(helper: BaseViewHolder, data: DialSettingCard) {
        val rvDialColor = helper.getView(R.id.rvColors) as RecyclerView
        val adapter = DialColorAdapter(data.colorPalette!!)
        rvDialColor.adapter = adapter
        adapter.onItemClickListener = OnDialColorSelectListener(helper, (data.colorPalette as ArrayList<DialColor>?)!!)
    }

    private fun initWheelPickerView(helper: BaseViewHolder, data: DialSettingCard) {
        val wvSelect = helper.getView<WheelView>(R.id.wvSelect)
        val dataArray = getDataArr(data)
        helper.setText(R.id.tvTitle, getCardTitle(data))
        wvSelect.adapter = ArrayWheelAdapter<String>(dataArray)
        wvSelect.setItemsVisibleCount(3)
        wvSelect.setAlphaGradient(false)
        wvSelect.setLineSpacingMultiplier(2.2f)
        wvSelect.setCyclic(false)
        wvSelect.setTextColorCenter(mContext.getColor(R.color.appBlue))
        wvSelect.setDividerColor(mContext.getColor(R.color.translate))
        wvSelect.setOnItemSelectedListener {
            helper.setText(R.id.tvSelect, dataArray[it])
            when (data.itemType) {
                DialSettingCard.ITEM_TYPE_ABOVE_TIME -> listener.onAboveTimeChanged(dataArray, it)
                DialSettingCard.ITEM_TYPE_BELOW_TIME -> listener.onBelowTimeChanged(dataArray, it)
                DialSettingCard.ITEM_TYPE_TIME_POSITION -> listener.onTimePositionChanged(dataArray, it)
            }
        }
    }

    /**
     *  获取填充wheelView数据
     */
    private fun getDataArr(card: DialSettingCard) =
            when (card.itemType) {
                DialSettingCard.ITEM_TYPE_TIME_POSITION -> timePositionArray.toArrayList()
                DialSettingCard.ITEM_TYPE_BELOW_TIME -> belowTimeArray.toArrayList()
                DialSettingCard.ITEM_TYPE_ABOVE_TIME -> aboveTimeArray.toArrayList()
                else -> arrayListOf()
            }

    private fun getCardTitle(card: DialSettingCard) =
            when (card.itemType) {
                DialSettingCard.ITEM_TYPE_TIME_POSITION -> mContext.getString(R.string.ani_watchface_timeposition_title)
                DialSettingCard.ITEM_TYPE_BELOW_TIME -> mContext.getString(R.string.ani_watchface_belowtime_title)
                DialSettingCard.ITEM_TYPE_ABOVE_TIME -> mContext.getString(R.string.ani_watchface_abovetime_title)
                else -> "what the fuck?"
            }

    /**
     * 表盘颜色适配器
     *
     * @constructor
     * TODO
     *
     * @param data
     */
    inner class DialColorAdapter(data: List<DialColor>) : BaseQuickAdapter<DialColor, BaseViewHolder>(R.layout.item_color, data) {
        override fun convert(helper: BaseViewHolder, item: DialColor?) {
            item?.let {
                helper.getView<CircleView>(R.id.cvColor).setCircleColor(item.color, item.borderColor)
                helper.setVisible(R.id.cv1, item.isSelect)
                helper.setVisible(R.id.cv2, item.isSelect)
            }
        }
    }

    /**
     * 表盘选择的监听
     * @property data
     */
    inner class OnDialColorSelectListener(var helper: BaseViewHolder, var data: ArrayList<DialColor>) : BaseQuickAdapter.OnItemClickListener {

        override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
            for (i in 0 until data.size) {
                data[i].isSelect = i == position
                if (data[i].isSelect) {
                    helper.setText(R.id.tvValue, data[i].colorName)
                    listener.onColorChanged(data[i])
                }
            }
            adapter?.notifyDataSetChanged()
        }

    }

    interface OnDialFaceSettingsChangedListener {

        /**
         * 颜色改变
         */
        fun onColorChanged(color: DialColor)

        fun onTimePositionChanged(data: ArrayList<String>, selectIndex: Int)

        fun onBelowTimeChanged(data: ArrayList<String>, selectIndex: Int)

        fun onAboveTimeChanged(data: ArrayList<String>, selectIndex: Int)
    }

}