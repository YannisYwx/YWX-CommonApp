package com.xxx.home.ui.fragment.device.dial

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.PagerSnapHelper
import com.orhanobut.logger.Logger
import com.xxx.home.R
import com.xxx.home.bean.DialColor
import com.xxx.home.bean.DialSettingCard
import com.xxx.home.databinding.HomeFragmentPhotoFacesBinding
import com.xxx.home.ui.adapter.DialFaceSettingCardAdapter
import com.ywx.common.base.fragment.NestViewBindingFragment
import com.ywx.common.ext.logE
import com.ywx.common.widget.BetterRecyclerView


/**
 * Author: YWX
 * Date: 2021/11/16 18:57
 * Description: 照片表盘编辑
 */
class PhotoFaceFragment : NestViewBindingFragment<HomeFragmentPhotoFacesBinding>(), DialFaceSettingCardAdapter.OnDialFaceSettingsChangedListener {

    companion object {
        fun newInstance(index: Int): PhotoFaceFragment {
            val args = Bundle()
            args.putInt("Index_F", index)
            val fragment = PhotoFaceFragment()
            fragment.arguments = args
            return fragment
        }

        fun newInstance() = PhotoFaceFragment()
    }

    override fun onBindLayout() = R.layout.home_fragment_photo_faces

    override fun getViewBinding(contentView: View): HomeFragmentPhotoFacesBinding {
        return HomeFragmentPhotoFacesBinding.bind(contentView)
    }

    override fun initData() {
        super.initData()
        mBinding.rvDialFaces.adapter = DialFaceSettingCardAdapter(settingCards, this)
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(mBinding.rvDialFaces)
    }

    private val settingCards: ArrayList<DialSettingCard>
        get() = arrayListOf(
                DialSettingCard(DialSettingCard.ITEM_TYPE_COLOR_DOT).apply {
                    colorPalette = dialColors
                },
                DialSettingCard(DialSettingCard.ITEM_TYPE_TIME_POSITION),
                DialSettingCard(DialSettingCard.ITEM_TYPE_ABOVE_TIME),
                DialSettingCard(DialSettingCard.ITEM_TYPE_BELOW_TIME)
        )

    private val dialColors: ArrayList<DialColor>
        get() = arrayListOf(
                DialColor(Color.parseColor("#111111"), colorName = getString(R.string.ani_watchface_contentcolor_content_black)),
                DialColor(Color.parseColor("#7B7B7B"), colorName = getString(R.string.ani_watchface_contentcolor_content_grey)),
                DialColor(Color.parseColor("#FDFDFD"), colorName = getString(R.string.ani_watchface_contentcolor_content_white), borderColor = Color.parseColor("#F0F0F0")),
                DialColor(Color.parseColor("#9ABFE2"), colorName = getString(R.string.ani_watchface_contentcolor_content_deepnavy)),
                DialColor(Color.parseColor("#0994FF"), colorName = getString(R.string.ani_watchface_contentcolor_content_royal)),
                DialColor(Color.parseColor("#09D2FF"), colorName = getString(R.string.ani_watchface_contentcolor_content_cyanblue)),
                DialColor(Color.parseColor("#5BCC74"), colorName = getString(R.string.ani_watchface_contentcolor_content_spearmint)),
                DialColor(Color.parseColor("#03A4AD"), colorName = getString(R.string.ani_watchface_contentcolor_content_pacificgreen)),
                DialColor(Color.parseColor("#FF6CFF"), colorName = getString(R.string.ani_watchface_contentcolor_content_neonpink)),
                DialColor(Color.parseColor("#F84883"), colorName = getString(R.string.ani_watchface_contentcolor_content_dragonfruit)),
                DialColor(Color.parseColor("#F1695A"), colorName = getString(R.string.ani_watchface_contentcolor_content_pinkcitrus)),
                DialColor(Color.parseColor("#FFA800"), colorName = getString(R.string.ani_watchface_contentcolor_content_lightorange)),
                DialColor(Color.parseColor("#FFF355"), colorName = getString(R.string.ani_watchface_contentcolor_content_canaryyellow)),
                DialColor(Color.parseColor("#E4FF3A"), colorName = getString(R.string.ani_watchface_contentcolor_content_flash))
        )

    override fun onColorChanged(color: DialColor) {
        Log.e(TAG,"======================== onColorChanged  =》 $color ")
        "我日尼玛---------------".logE(TAG)
        Logger.t(TAG).e("======================== onColorChanged  =》 $color ")
        showToast(color.toString() )

    }

    override fun onTimePositionChanged(data: ArrayList<String>, selectIndex: Int) {
    }

    override fun onBelowTimeChanged(data: ArrayList<String>, selectIndex: Int) {
    }

    override fun onAboveTimeChanged(data: ArrayList<String>, selectIndex: Int) {
    }
}