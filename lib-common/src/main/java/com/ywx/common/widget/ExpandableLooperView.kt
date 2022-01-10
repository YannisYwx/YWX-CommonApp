package com.ywx.common.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.ywx.common.R
import com.ywx.common.ext.logE
import com.ywx.common.widget.wheelview.WheelView
import com.ywx.common.widget.wheelview.adapter.ArrayWheelAdapter
import net.cachapa.expandablelayout.ExpandableLayout

/**
 * Author: YWX
 * Date: 2021/12/23 19:56
 * Description: 可以扩展收缩的Looper View
 */
class ExpandableLooperView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_expandable_looper, this, true)
        initAttrs(attrs = attrs)
        initView(rootView)
        initData()
    }

    private lateinit var label: String
    private lateinit var value: String
    private lateinit var unit: String
    private var maxValue = 100
    private var minValue = 0
    private lateinit var dataArray: MutableList<String>

    var isCanExpandable = true
    var isExpand = false

    private lateinit var tvLabel: TextView
    private lateinit var tvValue: TextView
    private lateinit var elWheelView: ExpandableLayout
    private lateinit var wvSelect: WheelView

    private fun initAttrs(attrs: AttributeSet?) {
        attrs?.let {
            val array = context.obtainStyledAttributes(it, R.styleable.ExpandableLooperView)
            label = array.getString(R.styleable.ExpandableLooperView_label) ?: ""
            unit = array.getString(R.styleable.ExpandableLooperView_unit) ?: ""
            maxValue = array.getInteger(R.styleable.ExpandableLooperView_maxValue, 100)
            minValue = array.getInteger(R.styleable.ExpandableLooperView_minValue, 0)
            isExpand = array.getBoolean(R.styleable.ExpandableLooperView_isExpand, false)
            array.recycle()
            "=====>  $label  --  $unit -- $maxValue -- $minValue   $isExpand".logE("^^^^^^^^^^^^")
        }
    }

    private fun initView(view: View) {
        tvLabel = view.findViewById(R.id.tvLabel)
        tvValue = view.findViewById(R.id.tvValue)
        elWheelView = view.findViewById(R.id.elWheelView)
        wvSelect = view.findViewById(R.id.wvSelect)
    }

    private fun initData() {
        "=====>  $label  --  $unit -- $maxValue -- $minValue  $isExpand".logE("^^^^^^^^^^^^")
        tvLabel.text = label
        wvSelect.adapter = ArrayWheelAdapter<String>(createWheelViewData())
        wvSelect.setItemsVisibleCount(3)
        wvSelect.setAlphaGradient(false)
        wvSelect.setLineSpacingMultiplier(2.2f)
        wvSelect.setCyclic(false)
        wvSelect.setTextColorCenter(context.getColor(R.color.appBlue))
        wvSelect.setDividerColor(context.getColor(R.color.translate))
        wvSelect.setOnItemSelectedListener {
            value = dataArray[it]
            tvValue.text = value.plus(this.unit)
        }

        tvValue.setOnClickListener {
            if (!isCanExpandable) return@setOnClickListener
            if (elWheelView.isExpanded) {
                elWheelView.collapse()
            } else {
                elWheelView.expand()
            }
        }

        elWheelView.setOnExpansionUpdateListener { _, state ->
            tvValue.setTextColor(context.getColor(if (state == 0) R.color.textColor else R.color.appBlue))
        }
        elWheelView.setExpanded(isExpand, false)
    }

    private fun createWheelViewData(): MutableList<String>? {
        dataArray = mutableListOf()
        dataArray.clear()
        for (i in minValue..maxValue) {
            dataArray.add(i.toString())
        }
        return dataArray
    }

    fun setData(value: String, label: String? = null, unit: String? = null, isNeedAppendUnit: Boolean = true) {
        label?.let { this.label = it }
        unit?.let { this.unit = it }
        this.value = value
        setData2UI(isNeedAppendUnit)
    }

    private fun setData2UI(isNeedAppendUnit: Boolean = true) {
        tvValue.text = value.plus(if (isNeedAppendUnit) unit else "")
        tvLabel.text = label
    }

}