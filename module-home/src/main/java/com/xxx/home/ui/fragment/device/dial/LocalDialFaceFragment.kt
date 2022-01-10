package com.xxx.home.ui.fragment.device.dial

import android.os.Bundle
import android.view.View
import com.xxx.home.R
import com.xxx.home.bean.DialFace
import com.xxx.home.databinding.HomeFragmentLocalFaceBinding
import com.xxx.home.ui.adapter.DialFaceFragmentPagerAdapter
import com.xxx.home.ui.adapter.DialFacesAdapter
import com.xxx.home.ui.dialog.DialogUtil
import com.ywx.common.base.fragment.NestViewBindingFragment
import com.ywx.common.dialog.DialogUtils
import com.ywx.common.dialog.VPCenterDialog
import com.ywx.common.event.EventCode
import com.ywx.common.event.FragmentEvent
import com.ywx.common.ext.logE
import com.ywx.common.ext.toast

/**
 * Author: YWX
 * Date: 2021/8/25 16:49
 * Description: 姓名设置
 */
class LocalDialFaceFragment : NestViewBindingFragment<HomeFragmentLocalFaceBinding>() {

    companion object {
        fun newInstance(index: Int): LocalDialFaceFragment {
            val args = Bundle()
            args.putInt("Index_F", index)
            val fragment = LocalDialFaceFragment()
            fragment.arguments = args
            return fragment
        }

        fun newInstance() = LocalDialFaceFragment()
    }

    lateinit var mAdapter: DialFaceFragmentPagerAdapter


    private val dialFacesAdapter: DialFacesAdapter by lazy {
        DialFacesAdapter(localFaceItem, isShowLabel = false).apply {
            isFillGrid = true
        }
    }

    private val localFaceItem: ArrayList<DialFace>
        get() = arrayListOf(
                DialFace(R.mipmap.local_face_0, "LocalFace 0", true),
                DialFace(R.mipmap.local_face_1, "LocalFace 1", false),
                DialFace(R.mipmap.local_face_2, "LocalFace 2", false)
        )

    override fun initData() {
        mBinding.rvDialFaces.adapter = dialFacesAdapter
    }

    override fun onFirstVisible() {
        super.onFirstVisible()
    }

    override fun bindListener() {
        super.bindListener()
        mBinding.btnSave.setOnClickListener {
            DialogUtils.showDevicePowerOffDialog(this.requireContext,listener = object :VPCenterDialog.OnVPCenterDialogListener{
                override fun onDialogConfirmListener() {
                    super.onDialogConfirmListener()
                    "沃日".toast()
                }
            })
        }
    }

    override fun onEvent(event: FragmentEvent?) {
        super.onEvent(event)
        event?.let {
            "$event ====================".logE(TAG)
            if (it.code == EventCode.Device.LOCAL_DIAL_FACE_CLICK) {
                showToast("你点击了 ${it.data as Int}")
                DialogUtils.showEraseDeviceDialog(this.requireContext, listener = object :VPCenterDialog.OnVPCenterDialogListener{
                    override fun onDialogConfirmListener() {
                        super.onDialogConfirmListener()
                        "哎哟，疼".toast()
                    }
                })
            }
        }
    }

    override fun onBindLayout() = R.layout.home_fragment_local_face

    override fun getViewBinding(contentView: View) = HomeFragmentLocalFaceBinding.bind(contentView)
}