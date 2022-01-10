package com.ywx.common.widget

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.ywx.common.R
import com.ywx.common.databinding.CommonViewpagerBottomSheetBinding

/**
 * Author: YWX
 * Date: 2021/11/2 15:26
 * Description: 通用的ViewPager底部弹框
 */
abstract class ViewPagerBottomSheet(context: Context) : CommonBottomSheet<CommonViewpagerBottomSheetBinding>(context) {

    override fun getBottomContentViewId(): Int {
        return R.layout.common_viewpager_bottom_sheet
    }

    var mPagerViews = mutableListOf<View>()

    override fun onCreate() {
        super.onCreate()
        initAdapter()
    }

    /**
     * 在这里设置pager
     * @return
     */
    abstract fun getPagers(): List<View>

    private fun initAdapter() {
        mPagerViews.clear()
        mPagerViews.addAll(getPagers())
        mBinding.cvp.adapter = BottomSheetPagerAdapter(views = mPagerViews)
        mBinding.cvp.offscreenPageLimit = 1
        mBinding.cvp.isCanGoRight = (false)
        mBinding.cvp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                mBinding.cvp.isCanGoLeft = (position == 1)
            }

        })
    }

    override fun getViewBinding(contentView: View) = CommonViewpagerBottomSheetBinding.bind(contentView)

    open class BottomSheetPagerAdapter(var views: List<View>) : PagerAdapter() {


        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            container.addView(views[position])
            return views[position]
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }

        override fun isViewFromObject(view: View, `object`: Any) = view == `object`

        override fun getCount() = views.size

    }
}