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

import com.orhanobut.logger.Logger;
import com.ywx.common.R;

/**
 * Author: YWX
 * Date: 2022/1/5 10:13
 * Description: 可以展开的布局
 */
public class ExpandableLayout extends RelativeLayout {
    private Boolean isAnimationRunning = false;
    private Boolean isOpened = false;
    private Integer duration;
    private RelativeLayout contentRelativeLayout;
    private RelativeLayout headerRelativeLayout;
    private boolean isExpand = false;

    private OnExpandCollapseListener listener;

    public ExpandableLayout(Context context) {
        super(context);
    }

    public ExpandableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ExpandableLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(final Context context, AttributeSet attrs) {
        final View rootView = View.inflate(context, R.layout.view_expandable, this);
        headerRelativeLayout = rootView.findViewById(R.id.view_expandable_header_layout);
        contentRelativeLayout = rootView.findViewById(R.id.view_expandable_content_layout);
        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExpandableLayout);
        final int headerID = typedArray.getResourceId(R.styleable.ExpandableLayout_headerLayout, -1);
        final int contentID = typedArray.getResourceId(R.styleable.ExpandableLayout_contentLayout, -1);
        isExpand = typedArray.getBoolean(R.styleable.ExpandableLayout_isExpand, false);
        if (headerID == -1 || contentID == -1)
            throw new IllegalArgumentException("HeaderLayout and ContentLayout cannot be null!");
        duration = typedArray.getInt(R.styleable.ExpandableLayout_duration, getContext().getResources().getInteger(android.R.integer.config_shortAnimTime));
        typedArray.recycle();

        final View headerView = View.inflate(context, headerID, null);
        headerView.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        headerRelativeLayout.addView(headerView);
        final View contentView = View.inflate(context, contentID, null);
        contentView.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        contentRelativeLayout.addView(contentView);
        contentRelativeLayout.setVisibility(isExpand ? VISIBLE : GONE);
//        headerRelativeLayout.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                toggle();
//            }
//        });
    }

    private int targetHeight;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        contentRelativeLayout.measure(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        targetHeight = contentRelativeLayout.getMeasuredHeight();
//        contentRelativeLayout.getLayoutParams().height = targetHeight;
//        contentRelativeLayout.requestLayout();
//        contentRelativeLayout.setVisibility(GONE);
//        Logger.e("onFinishInflate---------------->targetHeight = " + targetHeight);
    }

    public void toggle() {
        toggleWithDuration(200);
    }

    private void toggleWithDuration(int duration) {
        this.duration = duration;
        if (!isAnimationRunning) {
            if (contentRelativeLayout.getVisibility() == VISIBLE) {
                collapse(contentRelativeLayout);
            } else {
                expand(contentRelativeLayout);
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

    private void expand(final View v) {
//        v.measure(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
//        final int targetHeight = v.getMeasuredHeight();
        v.getLayoutParams().height = 0;
        v.setVisibility(VISIBLE);

        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1)
                    isOpened = true;
                v.getLayoutParams().height = (interpolatedTime == 1) ? LayoutParams.WRAP_CONTENT : (int) (targetHeight * interpolatedTime);
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
//        final int initialHeight = v.getMeasuredHeight();
        final int initialHeight = targetHeight;
        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                    isOpened = false;
                } else {
                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
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
            expand(contentRelativeLayout);
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
            collapse(contentRelativeLayout);
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

    public RelativeLayout getHeaderRelativeLayout() {
        return headerRelativeLayout;
    }

    public RelativeLayout getContentRelativeLayout() {
        return contentRelativeLayout;
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

