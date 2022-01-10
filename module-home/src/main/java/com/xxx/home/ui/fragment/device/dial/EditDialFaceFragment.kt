package com.xxx.home.ui.fragment.device.dial

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.AdaptScreenUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.xxx.home.R
import com.xxx.home.bean.DialFaceType
import com.xxx.home.databinding.FragmentDeviceEditDialFaceBinding
import com.xxx.home.ui.adapter.DialFaceFragmentPagerAdapter
import com.xxx.home.ui.adapter.DialFaceTypeAdapter
import com.ywx.common.base.fragment.NestViewBindingFragment
import com.ywx.common.ext.logE
import com.ywx.common.util.SpaceItemDecoration

/**
 * Author: YWX
 * Date: 2021/11/11 10:23
 * Description: 表盘编辑页面
 */
class EditDialFaceFragment : NestViewBindingFragment<FragmentDeviceEditDialFaceBinding>() {

    override fun getViewBinding(contentView: View) = FragmentDeviceEditDialFaceBinding.bind(contentView)

    override fun onBindLayout() = R.layout.fragment_device_edit_dial_face

    override fun enableCommonBar() = true

    private lateinit var mFaceTypeAdapter: DialFaceTypeAdapter
    lateinit var mAdapter: DialFaceFragmentPagerAdapter

    private fun initDialFaceTabHost() {
        val dialFaceTypes = arrayListOf(DialFaceType(faceType = getString(R.string.ani_watchface_localface_title), isSelect = true),
                DialFaceType(imgResId = 12, faceType = getString(R.string.ani_watchface_photoface_title)),
                DialFaceType(faceType = getString(R.string.ani_watchface_onlineface_title)))
        mFaceTypeAdapter = DialFaceTypeAdapter(dialFaceTypes)
        mBinding.rvTabHost.setHasFixedSize(true)
        mBinding.rvTabHost.addItemDecoration(SpaceItemDecoration(1, AdaptScreenUtils.pt2Px(16F), false))
        mBinding.rvTabHost.adapter = mFaceTypeAdapter

        mBinding.rvTabHost.layoutManager = LinearLayoutManager(requireContext, RecyclerView.HORIZONTAL, false)

        mFaceTypeAdapter.onItemClickListener = object : BaseQuickAdapter.OnItemClickListener {
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                mFaceTypeAdapter.setSelectItem(position)
                mBinding.vpDialFaces.currentItem = position
            }
        }
    }

    override fun initData() {
        super.initData()
        initDialFaceTabHost()
        initPager()
    }

    private fun initPager() {
        "表盘编辑初始化 initPager()---------------------->".logE(TAG)
        mAdapter = DialFaceFragmentPagerAdapter(this.requireActivity())
        mBinding.vpDialFaces.adapter = mAdapter
        mBinding.vpDialFaces.offscreenPageLimit = 1
        mBinding.vpDialFaces.isUserInputEnabled = false // 设置用户可以滑动
    }
}
