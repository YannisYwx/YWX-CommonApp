package com.ywx.common.util;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

/**
 * Author: YWX
 * Date: 2021/11/11 18:46
 * Description:
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    private int spanCount; //列数
    private int spacing; //间隔
    private boolean includeEdge; //是否包含边缘

    public SpaceItemDecoration(int spanCount, int space, boolean includeEdge) {
        this.spacing = space;
        this.spanCount = spanCount;
        this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NotNull View view, RecyclerView parent, @NotNull RecyclerView.State state) {
        outRect.bottom = spacing;
        //获取当前Item的位置
        int position = parent.getChildAdapterPosition(view); // item position
        //判断奇偶位置，然后进行相应的赋值运算
        int column = position % spanCount; // item column
        if (column == 0){
            outRect.left = spacing;
            outRect.right = spacing /spanCount;
        }else if (column == 1){
            outRect.left = spacing /spanCount;
            outRect.right = spacing;
        }

    }
}

