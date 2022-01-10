package com.ywx.common.dialog

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.DrawableCompat
import com.blankj.utilcode.util.ClickUtils
import com.blankj.utilcode.util.ViewUtils
import com.lxj.xpopup.core.CenterPopupView
import com.ywx.common.R

/**
 * Author: YWX
 * Date: 2021/12/18 17:41
 * Description: 通用的中间dialog
 */
class VPCenterDialog(context: Context) : CenterPopupView(context) {

    private lateinit var tvNotice: TextView
    private lateinit var tvTips: TextView
    private lateinit var ivInfo: ImageView
    private lateinit var ivClose: ImageView
    private lateinit var btnLeft: Button
    private lateinit var btnRight: Button
    private lateinit var btnCenter: Button

    /**
     * 标题资源
     */
    var noticeRes: Int = 0

    /**
     * 提示资源
     */
    var tipsRes: Int = 0

    /**
     * 左按钮文字资源
     */
    var btnLeftRes: Int = R.string.ani_general_action_cancel

    /**
     * 右按钮文字资源
     */
    var btnRightRes: Int = R.string.ani_general_action_ok

    /**
     * 中间按钮文字资源
     */
    var btnCenterRes: Int = R.string.ani_general_action_ok

    /**
     * 弹框图片
     */
    var ivInfoRes: Int = R.mipmap.iv_vp_center_dialog_stub

    /**
     * 是否显示左上角关闭按钮
     */
    var isShowCloseButton: Boolean = false

    /**
     * 是否只显示中间按钮
     */
    var isOnlyShowCenterButton: Boolean = false

    /**
     * 中间按钮风格是否是浅蓝色
     */
    var isCenterButtonLightBlueMode = true


    var isConfirm2Dismiss = true

    var listener: OnVPCenterDialogListener? = null


    override fun getImplLayoutId(): Int {
        return R.layout.common_dialog_vp_center
    }

    override fun initPopupContent() {
        super.initPopupContent()
        tvNotice = contentView.findViewById(R.id.tvNotice)
        tvTips = contentView.findViewById(R.id.tvTips)
        ivClose = contentView.findViewById(R.id.ivClose)
        ivInfo = contentView.findViewById(R.id.ivInfo)
        btnLeft = contentView.findViewById(R.id.btnLeft)
        btnRight = contentView.findViewById(R.id.btnRight)
        btnCenter = contentView.findViewById(R.id.btnCenter)
        initListener()
        initParameters()
    }

    fun isNoTips() = tipsRes <= 0

    /**
     * TODO初始化参数
     *
     * @param imgRes
     * @param noticeRes
     * @param tipsRes
     * @param btnLeftRes
     * @param btnRightRes
     * @param btnCenterRes
     * @param isOnlyShowCenterButton
     * @param isShowCloseButton
     */
    private fun initParameters(imgRes: Int = this.ivInfoRes,
                               noticeRes: Int = this.noticeRes,
                               tipsRes: Int = this.tipsRes,
                               btnLeftRes: Int = this.btnLeftRes,
                               btnRightRes: Int = this.btnRightRes,
                               btnCenterRes: Int = this.btnCenterRes,
                               isOnlyShowCenterButton: Boolean = this.isOnlyShowCenterButton,
                               isShowCloseButton: Boolean = this.isShowCloseButton) {
        tvNotice.text = if (noticeRes <= 0) "" else resources.getString(noticeRes)
        tvTips.text = if (tipsRes <= 0) "" else resources.getString(tipsRes)
        if (tipsRes <= 0) {
            tvTips.visibility = View.GONE
        }
        ivInfo.setImageResource(imgRes)
        ivClose.visibility = if (isShowCloseButton) View.VISIBLE else View.INVISIBLE
        if (isOnlyShowCenterButton) {
            btnCenter.setText(if (btnRightRes <= 0) R.string.ani_general_action_ok else btnCenterRes)
            btnCenter.visibility = View.VISIBLE
            btnLeft.visibility = View.GONE
            btnRight.visibility = View.GONE
            initButtonStyle(btnCenter, isCenterButtonLightBlueMode)
        } else {
            btnLeft.setText(if (btnLeftRes <= 0) R.string.ani_general_action_cancel else btnLeftRes)
            btnRight.setText(if (btnRightRes <= 0) R.string.ani_general_action_ok else btnRightRes)
            btnCenter.visibility = View.GONE
            btnLeft.visibility = View.VISIBLE
            btnRight.visibility = View.VISIBLE
            initButtonStyle(btnLeft, true)
            initButtonStyle(btnRight, false)
        }
    }

    private fun initButtonStyle(button: Button, isLightBlueMode: Boolean = false) {
        if (isLightBlueMode) {
            button.setBackgroundResource(R.drawable.common_big_rounded_btn_light_blue)
            button.setTextColor(resources.getColor(R.color.text_blue, null))
        } else {
            button.setBackgroundResource(R.drawable.common_big_rounded_btn)
            button.setTextColor(resources.getColor(R.color.white, null))
        }
    }

    private fun initListener() {
        ClickUtils.applyPressedViewScale()
        val views: Array<View> = arrayOf(btnCenter, btnLeft, btnRight)
        val s = floatArrayOf(-0.06f, -0.06f, -0.06f)
        val a = floatArrayOf(0.95f, 0.95f, 0.95f)
        ClickUtils.applyPressedViewScale(views, s)
        ClickUtils.applyPressedViewAlpha(views, a)
        ivClose.setOnClickListener {
            this.dismiss()
            this.listener?.onDialogDismissListener()
        }

        btnLeft.setOnClickListener {
            this.dismiss()
            this.listener?.onDialogCancelListener()
        }

        btnRight.setOnClickListener {
            this.listener?.onDialogConfirmListener()
            if (isConfirm2Dismiss) {
                this.dismiss()
            }
        }

        btnCenter.setOnClickListener {
            this.dismiss()
            this.listener?.onDialogConfirmListener()
            if (isConfirm2Dismiss) {
                this.dismiss()
            }
        }
    }


    interface OnVPCenterDialogListener {

        fun onDialogCancelListener() {

        }

        fun onDialogConfirmListener() {

        }

        fun onDialogDismissListener() {

        }
    }
}