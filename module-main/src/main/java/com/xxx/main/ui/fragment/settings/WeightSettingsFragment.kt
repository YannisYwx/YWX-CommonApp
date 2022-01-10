package com.xxx.main.ui.fragment.settings

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.veepoo.main.R
import com.xxx.main.bean.WeightUnit
import com.veepoo.main.databinding.MainFragmentSettingsWeightBinding
import com.ywx.common.base.fragment.NestViewBindingFragment

/**
 * Author: YWX
 * Date: 2021/8/25 16:49
 * Description: 体重设置
 */
class WeightSettingsFragment : NestViewBindingFragment<MainFragmentSettingsWeightBinding>() {

    companion object {
        fun newInstance(index: Int): WeightSettingsFragment {
            val args = Bundle()
            args.putInt("Index_F", index)
            val fragment = WeightSettingsFragment()
            fragment.arguments = args
            return fragment
        }

        fun newInstance() = WeightSettingsFragment()
    }

    private var unit: WeightUnit = WeightUnit.KG
    private var weightValue = 0
    private var weightValueKg = 0
    private var weightValueLbs = 0

    override fun initData() {
    }

    override fun onFirstVisible() {
        super.onFirstVisible()
        PersonSettingsFragment.disableToNext()
    }

    override fun bindListener() {
        super.bindListener()
        mBinding.etWeight.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (unit == WeightUnit.KG) {
                    weightValueKg = if (s.isNullOrEmpty()) 0 else s.toString().toInt()
                } else {
                    weightValueLbs = if (s.isNullOrEmpty()) 0 else s.toString().toInt()
                }

                if (s.isNullOrEmpty() || !isValueValid(s.toString().toInt())) {
                    weightValue = 0
                    PersonSettingsFragment.disableToNext()
                } else {
                    weightValue = s.toString().toInt()
                    PersonSettingsFragment.enableToNext()
                }

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        })
    }

    /**
     * 体重在800kg 或 1800磅以下才算
     *
     * @param weight
     */
    private fun isValueValid(weight: Int) = (unit == WeightUnit.KG && weight in 1..800) || (unit == WeightUnit.LBS && weight in 1..1800)

    override fun onBindLayout() = R.layout.main_fragment_settings_weight

    override fun getViewBinding(contentView: View) = MainFragmentSettingsWeightBinding.bind(contentView)
}