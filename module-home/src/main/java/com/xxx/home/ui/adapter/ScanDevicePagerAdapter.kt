package com.xxx.home.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

/**
 * Author: YWX
 * Date: 2021/8/25 11:08
 * Description:
 */
class ScanDevicePagerAdapter(var views: List<View>) : PagerAdapter() {


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