package com.ywx.common.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavDirections
import androidx.viewbinding.ViewBinding
import com.kingja.loadsir.callback.Callback
import com.lxj.xpopup.XPopup
import com.ywx.common.mvvm.view.status.EmptyStatus
import com.ywx.common.mvvm.view.status.ErrorStatus
import com.ywx.common.mvvm.view.status.InitStatus
import com.ywx.common.mvvm.view.status.LoadingStatus
import com.ywx.common.widget.CommonBottomSheet

/**
 *  @author : WX.Y
 *  date : 2021/3/17 10:13
 *  description : 基View
 */
interface IBaseView {

    companion object {
        var SHOW_SPACE = 200L
    }

    fun initView() {}

    /**
     * 最先的初始化，如传值之类
     */
    fun initDataBefore() {

    }

    /**
     * 初始化通用视图
     */
    fun initCommonView()

    /**
     * 初始化数据
     */
    fun initData() {

    }

    /**
     * 绑定视图布局
     *
     * @return layout res
     */
    @LayoutRes
    fun onBindLayout(): Int

    /**
     * 绑定监听事件
     */
    fun bindListener() {}

    fun getInitStatus(): Callback {
        return InitStatus()
    }

    /**
     * 加载中视图
     *
     * @return view
     */
    fun getLoadingStatus(): Callback {
        return LoadingStatus()
    }

    /**
     * 加载出错视图
     *
     * @return view
     */
    fun getErrorStatus(): Callback {
        return ErrorStatus()
    }

    /**
     * 控数据视图
     *
     * @return view
     */
    fun getEmptyStatus(): Callback {
        return EmptyStatus()
    }

    /**
     * 弹出一个吐司
     *
     * @param msg 消息内容
     */
    fun showToast(msg: String)

    fun beginTransaction(): FragmentTransaction? {
        var fm: FragmentManager? = null
        if (this is Fragment) {
            fm = (this as Fragment).childFragmentManager
        } else if (this is Activity) {
            fm = (this as FragmentActivity).supportFragmentManager
        }
        return fm?.beginTransaction()
    }

    /**
     * 提供额外状态布局
     *
     * @return
     */
    fun getExtraStatus(): List<Callback?>? {
        return null
    }

    fun isLightMode(): Boolean {
        return true
    }

    enum class CommonBarStyle {
        /**
         * 返回图标(默认)
         */
        LEFT_BACK,

        /**
         * 返回图标+文字
         */
        LEFT_BACK_TEXT,

        /**
         * 附加图标
         */
        LEFT_ICON,

        /**
         * 标题(默认)
         */
        CENTER_TITLE,

        /**
         * 自定义布局
         */
        CENTER_CUSTOM,

        /**
         * 文字
         */
        RIGHT_TEXT,

        /**
         * 图标(默认)
         */
        RIGHT_ICON,

        /**
         * 自定义布局
         */
        RIGHT_CUSTOM
    }


    /**
     * fragment基本跳转
     * A->B
     *
     * @param id
     */
    fun fragmentStart(@IdRes id: Int) {

    }

    /**
     * fragment 携带数据跳转-Bundle
     * A->B
     *
     * @param id
     * @param bundle
     */
    fun fragmentStart(@IdRes id: Int, bundle: Bundle?) {

    }

    /**
     * fragment 携带数据新写法，Navigation 标准写法
     * 需要在 nav_host中添加相应的argument，自动生成 以下类
     * 传递端 ClassName+Directions ，接收端ClassName+Args
     *
     *
     * Demo: (传递端)RegisterDelegateDirections.actionRegisterDelegateToCreateUserDelegate(phone)
     * (接收端)CreateUserDelegateArgs.fromBundle(getArguments()).getPhone()
     *
     * @param directions
     */
    fun fragmentStart(directions: NavDirections) {

    }

    /**
     * 退栈方法
     */
    fun fragmentUP() {

    }

    /**
     * 跳转方法，多级跳转
     * A->B->C,C->A,避免Navigation 跳转后重新执行生命周期方法
     *
     * @param id
     * @return 如果退栈一次就返回true
     */
    fun fragmentStartToA(@IdRes id: Int): Boolean {
        return true
    }

    /**
     * 显示底部弹框
     *
     * @param duration
     * @param enableDrag
     * @param context
     * @param bottomSheet
     */
    fun showBottomSheet(duration: Int = 700, enableDrag: Boolean = true, context: Context, bottomSheet: CommonBottomSheet<out ViewBinding>) =
            XPopup.Builder(context)
                    .moveUpToKeyboard(false)
                    .enableDrag(enableDrag)
                    .animationDuration(duration)
                    .isDestroyOnDismiss(true)
                    .asCustom(bottomSheet)
                    .show()
}