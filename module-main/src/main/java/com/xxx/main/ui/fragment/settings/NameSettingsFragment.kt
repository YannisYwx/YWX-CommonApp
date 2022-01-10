package com.xxx.main.ui.fragment.settings

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.veepoo.main.R
import com.veepoo.main.databinding.MainFragmentSettingsNameBinding
import com.ywx.common.base.fragment.NestViewBindingFragment

/**
 * Author: YWX
 * Date: 2021/8/25 16:49
 * Description: 姓名设置
 */
class NameSettingsFragment : NestViewBindingFragment<MainFragmentSettingsNameBinding>() {

    companion object {
        fun newInstance(index: Int): NameSettingsFragment {
            val args = Bundle()
            args.putInt("Index_F", index)
            val fragment = NameSettingsFragment()
            fragment.arguments = args
            return fragment
        }

        fun newInstance() = NameSettingsFragment()
    }

    override fun initData() {
    }

    override fun onFirstVisible() {
        super.onFirstVisible()
    }

    override fun bindListener() {
        super.bindListener()
        mBinding.etName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(e: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrBlank()) {
                    PersonSettingsFragment.disableToNext()
                } else {
                    PersonSettingsFragment.enableToNext()
                }
            }

        })
    }

    override fun onBindLayout() = R.layout.main_fragment_settings_name

    override fun getViewBinding(contentView: View) = MainFragmentSettingsNameBinding.bind(contentView)
}