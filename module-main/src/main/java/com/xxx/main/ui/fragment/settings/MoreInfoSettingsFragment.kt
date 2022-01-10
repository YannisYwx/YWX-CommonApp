package com.xxx.main.ui.fragment.settings

import android.view.View
import com.veepoo.main.R
import com.xxx.main.bean.Gender
import com.veepoo.main.databinding.MainFragmentSettingsMoreInfoBinding
import com.ywx.common.base.fragment.NestViewBindingFragment

/**
 * Author: YWX
 * Date: 2021/8/25 16:49
 * Description: 姓名设置
 */
class MoreInfoSettingsFragment : NestViewBindingFragment<MainFragmentSettingsMoreInfoBinding>() {
    companion object {
        fun newInstance() = MoreInfoSettingsFragment()
    }


    private var selectIndex = 0

    override fun initData() {
        initUserSkins(Gender.MALE)
    }

    override fun onFirstVisible() {
        super.onFirstVisible()
        PersonSettingsFragment.disableToNext()
    }

    override fun onBindLayout() = R.layout.main_fragment_settings_more_info

    override fun enableCommonBar(): Boolean {
        return false
    }

    override fun enableLazy(): Boolean {
        return false
    }

    override fun bindListener() {
        super.bindListener()
        mBinding.ivSkin1.setOnClickListener(this)
        mBinding.ivSkin2.setOnClickListener(this)
        mBinding.ivSkin3.setOnClickListener(this)
        mBinding.ivSkin4.setOnClickListener(this)
        mBinding.ivSkin5.setOnClickListener(this)
        mBinding.ivSkin6.setOnClickListener(this)
    }

    private fun updateSelectStatus(){
        mBinding.ivSkin1.isSelected = selectIndex == 1
        mBinding.ivSkin2.isSelected = selectIndex == 2
        mBinding.ivSkin3.isSelected = selectIndex == 3
        mBinding.ivSkin4.isSelected = selectIndex == 4
        mBinding.ivSkin5.isSelected = selectIndex == 5
        mBinding.ivSkin6.isSelected = selectIndex == 6
    }

    private fun initUserSkins(gender: Gender) {
        when (gender) {
            Gender.MALE, Gender.NONE -> {
                mBinding.ivSkin1.setBackgroundResource(R.drawable.main_gender_male_skin_1)
                mBinding.ivSkin2.setBackgroundResource(R.drawable.main_gender_male_skin_2)
                mBinding.ivSkin3.setBackgroundResource(R.drawable.main_gender_male_skin_3)
                mBinding.ivSkin4.setBackgroundResource(R.drawable.main_gender_male_skin_4)
                mBinding.ivSkin5.setBackgroundResource(R.drawable.main_gender_male_skin_5)
                mBinding.ivSkin6.setBackgroundResource(R.drawable.main_gender_male_skin_6)
            }
            Gender.FEMALE -> {
                mBinding.ivSkin1.setBackgroundResource(R.drawable.main_gender_female_skin_1)
                mBinding.ivSkin2.setBackgroundResource(R.drawable.main_gender_female_skin_2)
                mBinding.ivSkin3.setBackgroundResource(R.drawable.main_gender_female_skin_3)
                mBinding.ivSkin4.setBackgroundResource(R.drawable.main_gender_female_skin_4)
                mBinding.ivSkin5.setBackgroundResource(R.drawable.main_gender_female_skin_5)
                mBinding.ivSkin6.setBackgroundResource(R.drawable.main_gender_female_skin_6)
            }
            Gender.OTHER -> {
                mBinding.ivSkin1.setBackgroundResource(R.drawable.main_gender_other_skin_1)
                mBinding.ivSkin2.setBackgroundResource(R.drawable.main_gender_other_skin_2)
                mBinding.ivSkin3.setBackgroundResource(R.drawable.main_gender_other_skin_3)
                mBinding.ivSkin4.setBackgroundResource(R.drawable.main_gender_other_skin_4)
                mBinding.ivSkin5.setBackgroundResource(R.drawable.main_gender_other_skin_5)
                mBinding.ivSkin6.setBackgroundResource(R.drawable.main_gender_other_skin_6)
            }
        }
    }

    override fun getViewBinding(contentView: View): MainFragmentSettingsMoreInfoBinding {
        return MainFragmentSettingsMoreInfoBinding.bind(contentView)
    }

    override fun onClick(view: View?) {
        super.onClick(view)
        when (view) {
            mBinding.ivSkin1 -> selectIndex = 1
            mBinding.ivSkin2 -> selectIndex = 2
            mBinding.ivSkin3 -> selectIndex = 3
            mBinding.ivSkin4 -> selectIndex = 4
            mBinding.ivSkin5 -> selectIndex = 5
            mBinding.ivSkin6 -> selectIndex = 6
        }
        updateSelectStatus()
    }
}