package com.ywx.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.RelativeLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.blankj.utilcode.util.AdaptScreenUtils;
import com.orhanobut.logger.Logger;
import com.ywx.common.R;

/**
 * Author: YWX
 * Date: 2022/1/5 10:13
 * Description: 可以展开的布局
 */
public class ExpandableCLayout extends ConstraintLayout {
    private Boolean isAnimationRunning = false;
    private Boolean isOpened = false;
    private Integer duration;
    private ConstraintLayout contentLayout;
    private ConstraintLayout headerLayout;
    private boolean isExpand = false;

    private OnExpandCollapseListener listener;

    public ExpandableCLayout(Context context) {
        super(context);
    }

    public ExpandableCLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ExpandableCLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(final Context context, AttributeSet attrs) {
        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExpandableCLayout);
        final int headerID = typedArray.getResourceId(R.styleable.ExpandableCLayout_ecl_headerLayout, -1);
        final int contentID = typedArray.getResourceId(R.styleable.ExpandableCLayout_ecl_contentLayout, -1);
        isExpand = typedArray.getBoolean(R.styleable.ExpandableCLayout_ecl_isExpand, false);
        if (headerID == -1 || contentID == -1)
            throw new IllegalArgumentException("HeaderLayout and ContentLayout cannot be null!");
        duration = typedArray.getInt(R.styleable.ExpandableCLayout_ecl_duration, getContext().getResources().getInteger(android.R.integer.config_shortAnimTime));
        typedArray.recycle();

        headerLayout = (ConstraintLayout) View.inflate(context, headerID, null);
        contentLayout = (ConstraintLayout) View.inflate(context, contentID, null);

        ConstraintLayout.LayoutParams topLayoutParams = new ConstraintLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        addView(headerLayout);
        topLayoutParams.topToTop = LayoutParams.PARENT_ID;
        topLayoutParams.startToStart = LayoutParams.PARENT_ID;
        headerLayout.setLayoutParams(topLayoutParams);

        ConstraintLayout.LayoutParams contentLayoutParams = new ConstraintLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        contentLayoutParams.topToBottom = headerLayout.getId();
        contentLayoutParams.startToStart = LayoutParams.PARENT_ID;
//        contentLayoutParams.height = AdaptScreenUtils.pt2Px(423.36F);
        contentLayout.setLayoutParams(contentLayoutParams);
        addView(contentLayout);
        contentLayout.setVisibility(isExpand ? VISIBLE : GONE);
        headerLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });
    }

    private int targetHeight;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public void toggle() {
        toggleWithDuration(200);
    }

    private void toggleWithDuration(int duration) {
        this.duration = duration;
        if (!isAnimationRunning) {
            if (contentLayout.getVisibility() == VISIBLE) {
                collapse(contentLayout);
            } else {
                expand(contentLayout);
            }
            isAnimationRunning = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    isAnimationRunning = false;
                    if (listener != null) {
                        if (isOpened) {
                            listener.onExpand();
                        } else {
                            listener.onCollapse();
                        }
                    }
                }
            }, duration);
        }
    }

    private void expand(final ConstraintLayout v) {
        v.measure(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();
//        final int targetHeight = AdaptScreenUtils.pt2Px(423.36F);
        v.getLayoutParams().height = 0;
//        ConstraintLayout.LayoutParams contentLayoutParams = (LayoutParams) v.getLayoutParams();
//        contentLayoutParams.height = 0;
//        v.setLayoutParams(contentLayoutParams);
        v.setVisibility(VISIBLE);

        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                Logger.t("ExpandableCLayout").e("------------expand interpolatedTime = " +interpolatedTime);
                if (interpolatedTime == 1)
                    isOpened = true;
                v.getLayoutParams().height = (interpolatedTime == 1) ? LayoutParams.WRAP_CONTENT : (int) (targetHeight * interpolatedTime);
//                v.setVisibility(VISIBLE);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation.setDuration(duration);
        v.startAnimation(animation);
    }

    private void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();
//        final int initialHeight = targetHeight;
//        final int initialHeight = AdaptScreenUtils.pt2Px(423.36F);
        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                    isOpened = false;
                } else {
                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                    v.setVisibility(VISIBLE);
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        animation.setDuration(duration);
        v.startAnimation(animation);
    }

    public Boolean isOpened() {
        return isOpened;
    }

    public void show() {
        if (!isAnimationRunning) {
            expand(contentLayout);
            isAnimationRunning = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    isAnimationRunning = false;
                    if (listener != null) {
                        listener.onExpand();
                    }
                }
            }, duration);
        }
    }

    public void hide() {
        if (!isAnimationRunning) {
            collapse(contentLayout);
            isAnimationRunning = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    isAnimationRunning = false;
                    if (listener != null) {
                        listener.onCollapse();
                    }
                }
            }, duration);
        }
    }

    public ConstraintLayout getHeaderLayout() {
        return headerLayout;
    }

    public ConstraintLayout getContentLayout() {
        return contentLayout;
    }

    public interface OnExpandCollapseListener {
        /**
         * 展开
         */
        void onExpand();

        /**
         * 折叠
         */
        void onCollapse();
    }

    public void setOnExpandCollapseListener(OnExpandCollapseListener listener) {
        this.listener = listener;
    }
}

