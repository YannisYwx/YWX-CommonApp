package com.xxx.main.ui.fragment.settings

import android.view.View
import com.veepoo.main.R
import com.xxx.main.bean.Gender
import com.ywx.common.base.fragment.NestFragment
import kotlinx.android.synthetic.main.main_fragment_settings_gender.*

/**
 * Author: YWX
 * Date: 2021/8/25 18:38
 * Description: 性别设置
 */
class GenderSettingsFragment : NestFragment(), View.OnClickListener {
    companion object {
        fun newInstance(): GenderSettingsFragment {
            return GenderSettingsFragment()
        }
    }

    var selectGender = Gender.NONE

    override fun initData() {
        updateGenderCards();
    }

    override fun onFirstVisible() {
        super.onFirstVisible()
        PersonSettingsFragment.disableToNext()
    }

    private fun updateGenderCards(){
        ivMale.isSelected = selectGender == Gender.MALE
        ivFemale.isSelected = selectGender == Gender.FEMALE
        ivOther.isSelected = selectGender == Gender.OTHER
    }

    override fun bindListener() {
        super.bindListener()
        ivMale.setOnClickListener(this)
        ivFemale.setOnClickListener(this)
        ivOther.setOnClickListener(this)
    }

    override fun onBindLayout() = R.layout.main_fragment_settings_gender

    override fun onClick(v: View?) {
        v?.let {
            when (it) {
                ivMale -> selectGender = Gender.MALE
                ivFemale -> selectGender = Gender.FEMALE
                ivOther -> selectGender = Gender.OTHER
            }
            updateGenderCards()
            PersonSettingsFragment.enableToNext()
        }
    }
}