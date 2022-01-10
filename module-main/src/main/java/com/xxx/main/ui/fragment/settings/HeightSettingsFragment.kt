package com.xxx.main.ui.fragment.settings

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.veepoo.main.R
import com.xxx.main.bean.HeightUnit
import com.veepoo.main.databinding.MainFragmentSettingsHeightBinding
import com.ywx.common.base.fragment.NestViewBindingFragment

/**
 * Author: YWX
 * Date: 2021/8/25 16:49
 * Description: 身高设置
 */
class HeightSettingsFragment : NestViewBindingFragment<MainFragmentSettingsHeightBinding>() {

    companion object {
        fun newInstance(index: Int): HeightSettingsFragment {
            val args = Bundle()
            args.putInt("Index_F", index)
            val fragment = HeightSettingsFragment()
            fragment.arguments = args
            return fragment
        }

        fun newInstance() = HeightSettingsFragment()
    }

    private var cm: Double = 0.0
    private var ft: Int = 0
    private var inch: Int = 0
    private var unit = HeightUnit.CM

    override fun initData() {
    }

    override fun onFirstVisible() {
        super.onFirstVisible()
        PersonSettingsFragment.disableToNext()
    }

    override fun bindListener() {
        super.bindListener()
        mBinding.etCm.addTextChangedListener(InputWatcher(mBinding.etCm))
        mBinding.etFt.addTextChangedListener(InputWatcher(mBinding.etFt))
        mBinding.etIn.addTextChangedListener(InputWatcher(mBinding.etIn))
    }

    override fun onBindLayout() = R.layout.main_fragment_settings_height

    override fun getViewBinding(contentView: View) = MainFragmentSettingsHeightBinding.bind(contentView)

    //内有inner关键字表示嵌套类 相当于java中的静态内部类
    inner class InputWatcher(var view: View) : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            when (view) {
                mBinding.etCm -> {
                    if (s.isNullOrEmpty() || s.toString().toDouble() == 0.0) {
                        cm = 0.0
                        PersonSettingsFragment.disableToNext()
                    } else {
                        cm = s.toString().toDouble()
                        PersonSettingsFragment.enableToNext()
                    }
                }

                mBinding.etFt -> {
                    if (s.isNullOrEmpty() || s.toString().toInt() == 0) {
                        ft = 0
                        PersonSettingsFragment.disableToNext()
                    } else {
                        ft = s.toString().toInt()
                        PersonSettingsFragment.enableToNext()
                    }
                }

                mBinding.etIn -> {
                    inch = if (s.isNullOrEmpty()) 0 else s.toString().toInt()
                }
            }
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }


    }
}