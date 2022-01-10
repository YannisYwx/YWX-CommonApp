package com.xxx.main.ui.fragment.settings

import android.graphics.Color
import android.graphics.Rect
import android.view.View
import android.view.ViewTreeObserver
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.CacheDoubleStaticUtils
import com.veepoo.main.R
import com.xxx.main.adapter.AdapterFragmentPager
import com.xxx.main.bean.UserBean
import com.veepoo.main.databinding.MainFragmentPersonSettingsBinding
import com.xxx.main.ui.widget.ScaleCircleNavigator
import com.ywx.common.AppConstant
import com.ywx.common.base.fragment.NestViewBindingFragment
import com.ywx.common.event.EventCode
import com.ywx.common.event.FragmentEvent
import kotlinx.android.synthetic.main.main_fragment_person_settings.*

/**
 * Author: YWX
 * Date: 2021/8/24 18:42
 * Description: 个人设置页面
 */
class PersonSettingsFragment : NestViewBindingFragment<MainFragmentPersonSettingsBinding>() {

    private lateinit var adapter: AdapterFragmentPager

    companion object {

        var userBean: UserBean = UserBean.getDefault()

        fun newInstance(): PersonSettingsFragment {
            return PersonSettingsFragment()
        }

        /**
         * 可以进行下一步
         */
        fun enableToNext() {
//            EventBus.getDefault().post(FragmentEvent(EventCode.Main.ENABLE_TO_NEXT))
        }

        /**
         * 禁止下一步
         */
        fun disableToNext() {
//            EventBus.getDefault().post(FragmentEvent(EventCode.Main.DISABLE_TO_NEXT))
        }
    }

    override fun initData() {
        adapter = AdapterFragmentPager(this.mActivity as FragmentActivity)
        mBinding.vp2PS.adapter = adapter
        mBinding.vp2PS.offscreenPageLimit = 1
        mBinding.vp2PS.isUserInputEnabled = true // 设置用户可以滑动
        mBinding.vp2PS.isSaveEnabled = false
        initMagicIndicator()
        CacheDoubleStaticUtils.put(AppConstant.DataCacheKey.USER, UserBean.getDefault())
        keepLoginBtnNotOver(mBinding.llRoot, mBinding.clContent)
    }

    override fun bindListener() {
        super.bindListener()
        mBinding.btnNext.setOnClickListener {
            if(vp2PS.currentItem == 5) {
                fragmentStart(R.id.action_personSettings2LocationPermission)
            } else {
                vp2PS.setCurrentItem(vp2PS.currentItem + 1, true)
            }
        }
    }

    private fun initMagicIndicator() {
        val scaleCircleNavigator = ScaleCircleNavigator(this.context)
        scaleCircleNavigator.setCircleCount(adapter.itemCount)
        scaleCircleNavigator.setNormalCircleColor(Color.LTGRAY)
        scaleCircleNavigator.setSelectedCircleColor(Color.DKGRAY)
        scaleCircleNavigator.setCircleClickListener { index ->
            mBinding.vp2PS.currentItem = index
        }
        mBinding.magicIndicator.navigator = scaleCircleNavigator
        mBinding.vp2PS.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                mBinding.magicIndicator.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                mBinding.magicIndicator.onPageSelected(position)
                mCommonTitleBar.leftCustomView.visibility = if (position == 0)  View.INVISIBLE else  View.VISIBLE
            }

            override fun onPageScrollStateChanged(state: Int) {
                mBinding.magicIndicator.onPageScrollStateChanged(state)
            }
        })
    }

    override fun onEvent(event: FragmentEvent?) {
        super.onEvent(event)
        event?.let {
            when (it.code) {
                EventCode.Main.ENABLE_TO_NEXT -> {
                    mBinding.vp2PS.isUserInputEnabled = true
                    mBinding.btnNext.isEnabled = true
                }
                EventCode.Main.DISABLE_TO_NEXT -> {
                    mBinding.vp2PS.isUserInputEnabled = false
                    mBinding.btnNext.isEnabled = false
                }
            }
        }
    }

    override fun onRight1Click(v: View?) {
        super.onRight1Click(v)
//        start(LocationPermissionFragment.newInstance())
    }

    override fun onBindLayout() = R.layout.main_fragment_person_settings

    override fun enableCommonBar() = true

    override fun onBindBarRightText(): Array<String?>? = arrayOf(resources.getString(R.string.ani_general_action_skip))

    /**
     * 保持登录按钮始终不会被覆盖
     *
     * @param root
     * @param subView
     */
    private fun keepLoginBtnNotOver(root: View, subView: View) {
        root.viewTreeObserver.addOnGlobalLayoutListener(ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            fun onGlobalLayout() {
                e("-------------------++++++++++++++++++++++")
                val rect = Rect()
                // 获取root在窗体的可视区域
                root.getWindowVisibleDisplayFrame(rect)
                // 获取root在窗体的不可视区域高度(被其他View遮挡的区域高度)
                val rootInvisibleHeight = root.rootView.height - rect.bottom
                // 若不可视区域高度大于200，则键盘显示,其实相当于键盘的高度
                if (rootInvisibleHeight > 200) {
                    // 显示键盘时
                    BarUtils.getNavBarHeight()
                    val srollHeight = rootInvisibleHeight - (root.height - subView.height) - BarUtils.getNavBarHeight()
                    if (srollHeight > 0) {
                        //当键盘高度覆盖按钮时
                        root.scrollTo(0, srollHeight);
                    }
                } else {
                    // 隐藏键盘时
                    root.scrollTo(0, 0);
                }
            }
        })
    }

    override fun getViewBinding(contentView: View) = MainFragmentPersonSettingsBinding.bind(contentView)
}