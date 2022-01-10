package com.ywx.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import androidx.annotation.Nullable;
import androidx.core.view.ViewConfigurationCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.orhanobut.logger.Logger;

/**
 * Author: YWX
 * Date: 2021/11/23 17:29
 * Description:
 */
public class BetterRecyclerView extends RecyclerView {

    private int touchSlop;
    private Context mContext;
    private int INVALID_POINTER = -1;
    private int scrollPointerId = INVALID_POINTER;

    private int initialTouchX;
    private int initialTouchY;


    private final static String TAG = "BetterRecyclerView";

    public BetterRecyclerView(Context context) {
        this(context, null);
    }

    public BetterRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BetterRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        ViewConfiguration vc = ViewConfiguration.get(context);
        touchSlop = vc.getScaledEdgeSlop();
        mContext = context;
    }

    @Override
    public void setScrollingTouchSlop(int slopConstant) {
        super.setScrollingTouchSlop(slopConstant);
        ViewConfiguration vc = ViewConfiguration.get(mContext);
        switch (slopConstant) {
            case TOUCH_SLOP_DEFAULT:
                touchSlop = vc.getScaledTouchSlop();
                break;
            case TOUCH_SLOP_PAGING:
                touchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(vc);
                break;
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        Logger.t(TAG).e("======================== Override  e = " + e.toString());
        return super.onTouchEvent(e);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        return false;
//        Logger.t(TAG).e("======================== onInterceptTouchEvent  e = " + e.toString());
//        int action = e.getAction();
//        int actionIndex = e.getActionIndex();
//
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//                scrollPointerId = e.getPointerId(0);
//                initialTouchX = Math.round(e.getX() + 0.5f);
//                initialTouchY = Math.round(e.getY() + 0.5f);
//
//                return super.onInterceptTouchEvent(e);
//
//            case MotionEvent.ACTION_POINTER_DOWN:
//
//                scrollPointerId = e.getPointerId(actionIndex);
//                initialTouchX = Math.round(e.getX(actionIndex) + 0.5f);
//                initialTouchY = Math.round(e.getY(actionIndex) + 0.5f);
//
//                return super.onInterceptTouchEvent(e);
//            case MotionEvent.ACTION_MOVE:
//                final int index = e.findPointerIndex(scrollPointerId);
//
//                if (index < 0) {
//                    return false;
//                }
//
//                int x = Math.round(e.getX(index) + 0.5f);
//                int y = Math.round(e.getX(index) + 0.5f);
//
////              if (getScrollState() != SCROLL_STATE_DRAGGING) {
//
//                int dx = x - initialTouchX;
//
//                int dy = y - initialTouchY;
//
//                Logger.t(TAG).e("dx = " + dx + " ]][[ dy = " + dy);
//                boolean startScroll = false;
//                if ((getLayoutManager().canScrollHorizontally() && Math.abs(dx) > touchSlop * 8) && (getLayoutManager().canScrollVertically() || Math.abs(dx) > Math.abs((dy)))) {
//                    startScroll = true;
//                }
//
//
//                if ((getLayoutManager().canScrollHorizontally() && Math.abs(dy) > touchSlop * 8) && (getLayoutManager().canScrollHorizontally() || Math.abs(dy) > Math.abs((dx)))) {
//                    startScroll = true;
//                }
//
//                Logger.t(TAG).e("startScroll ===> startScroll = " + startScroll + "dx = " + dx + " dy = " + dy + "  touchSlop*8 = " + touchSlop * 8);
//
//                return startScroll && super.onInterceptTouchEvent(e);
////                }
////                return super.onInterceptTouchEvent(e);
//        }
//        return super.onInterceptTouchEvent(e);
    }
}
