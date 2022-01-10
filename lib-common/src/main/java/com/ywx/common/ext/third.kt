package com.ywx.common.ext

import android.view.View
import com.chad.library.adapter.base.BaseViewHolder

/**
 * Author: YWX
 * Date: 2021/11/16 15:30
 * Description: 第三方扩展
 */
fun BaseViewHolder.setSelect(id: Int, isSelect: Boolean) {
    this.getView<View>(id).isSelected = isSelect
}