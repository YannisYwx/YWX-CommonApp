package com.xxx.main.ui.fragment.settings

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.veepoo.main.R
import com.veepoo.main.databinding.MainFragmentSettingsBirthdayBinding
import com.ywx.common.AppConstant
import com.ywx.common.base.fragment.NestViewBindingFragment
import com.ywx.common.util.isBirthdayValid
import com.ywx.common.util.isValidDayInMonth
import com.ywx.common.util.isValidMonth
import com.ywx.common.util.isValidYearForBirthday

/**
 * Author: YWX
 * Date: 2021/8/25 16:49
 * Description: 生日设置
 */
class BirthdaySettingsFragment : NestViewBindingFragment<MainFragmentSettingsBirthdayBinding>(), View.OnFocusChangeListener {

    companion object {
        fun newInstance(index: Int): BirthdaySettingsFragment {
            val args = Bundle()
            args.putInt("Index_F", index)
            val fragment = BirthdaySettingsFragment()
            fragment.arguments = args
            return fragment
        }

        fun newInstance() = BirthdaySettingsFragment()
    }

    private var year: String = AppConstant.EMPTY_STR
    private var month: String = AppConstant.EMPTY_STR
    private var day: String = AppConstant.EMPTY_STR

    override fun initData() {
    }

    override fun onFirstVisible() {
        super.onFirstVisible()
        PersonSettingsFragment.disableToNext()
    }

    override fun bindListener() {
        super.bindListener()
        mBinding.etYear.addTextChangedListener(InputWatcher(mBinding.etYear))
        mBinding.etMonth.addTextChangedListener(InputWatcher(mBinding.etMonth))
        mBinding.etDay.addTextChangedListener(InputWatcher(mBinding.etDay))
        mBinding.etDay.onFocusChangeListener = this
        mBinding.etMonth.onFocusChangeListener = this
        mBinding.etYear.onFocusChangeListener = this

    }

    private fun updateWarningText() {
        var isWarningsInvisible = false
        if (!year.isBlank()) {
            isWarningsInvisible = isValidYearForBirthday(year.toInt())
            mBinding.tvWarning.visibility = if (isWarningsInvisible) View.INVISIBLE else View.VISIBLE
        }

        if (!month.isBlank() && isWarningsInvisible) {
            isWarningsInvisible = isValidMonth(month.toInt())
            mBinding.tvWarning.visibility = if (isWarningsInvisible) View.INVISIBLE else View.VISIBLE
        }

        if (!day.isBlank() && isWarningsInvisible) {
            if (!year.isBlank() && !month.isBlank()) {
                mBinding.tvWarning.visibility = if (isValidDayInMonth(year.toInt(), month.toInt(), day.toInt())) View.INVISIBLE else View.VISIBLE
            } else {
                mBinding.tvWarning.visibility = if (day.toInt() in 1..31) View.INVISIBLE else View.VISIBLE
            }
        }
    }

    override fun onBindLayout() = R.layout.main_fragment_settings_birthday


    private fun enableToNext() {
        if (year.isBlank() || month.isBlank() || day.isBlank()) return
        if (isBirthdayValid(year.toInt(), month.toInt(), day.toInt())) {
            PersonSettingsFragment.enableToNext()
        } else {
            PersonSettingsFragment.disableToNext()
        }
    }

    override fun onFocusChange(view: View?, isFocus: Boolean) {
        view?.let {
            when (it) {
                mBinding.etYear -> {
                    if (day.length == 1) {
                        mBinding.etDay.setText(day.prefix())
                    }
                }
                mBinding.etMonth -> {
                    if (isFocus) {
                        if (year.length in 1..3) {
                            mBinding.etYear.setText(if (year.length == 1) year.prefix("000") else if (year.length == 2) year.prefix("00") else year.prefix())
                        }
                    }
                }
                mBinding.etDay -> {
                    if (isFocus) {
                        if (month.length == 1) {
                            mBinding.etMonth.setText(month.prefix())
                        }
                        if (year.length in 1..3) {
                            mBinding.etYear.setText(if (year.length == 1) year.prefix("000") else if (year.length == 2) year.prefix("00") else year.prefix())
                        }
                    }
                }
            }
        }

    }

    private fun String.prefix(prefix: String = "0") = prefix.plus(this)

    override fun getViewBinding(contentView: View) = MainFragmentSettingsBirthdayBinding.bind(contentView)

    //内有inner关键字表示嵌套类 相当于java中的静态内部类
    inner class InputWatcher(var view: View) : TextWatcher {
        override fun afterTextChanged(e: Editable?) {
            when (view) {
                mBinding.etYear -> {
                    if (e.isNullOrBlank()) return
                    year = e.toString()
                    updateWarningText()
                    enableToNext()
                    if (isValidYearForBirthday(year.toInt())) {
                        mBinding.etMonth.isFocusable = true
                        mBinding.etMonth.requestFocus()
                        mBinding.etMonth.setSelection(0)
                    }
                }

                mBinding.etMonth -> {
                    if (e.isNullOrBlank()) return
                    month = e.toString()
                    if (month == "00") {
                        mBinding.etMonth.setText("")
                    } else if (month.length == 2 && month.toInt() in 1..12) {
                        mBinding.etDay.setSelection(0)
                        mBinding.etDay.isFocusable = true
                        mBinding.etDay.requestFocus()
                    } else if (month.length == 1 && month.toInt() >= 2) {
                        month = month.prefix()
                        mBinding.etMonth.setText(month)
                    }
                    updateWarningText()
                    enableToNext()
                }

                mBinding.etDay -> {
                    if (e.isNullOrBlank()) return
                    day = e.toString()
                    updateWarningText()
                    enableToNext()
                }
            }
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

    }

}