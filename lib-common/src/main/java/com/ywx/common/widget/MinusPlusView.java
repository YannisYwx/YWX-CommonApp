package com.ywx.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.ywx.common.R;

/**
 * @author : WX.Y
 * date : 2020/10/12 11:10
 * description :
 */
public class MinusPlusView extends ConstraintLayout {

    private ImageView ivMinus, ivPlus;

    private OnMinusAndPlusClickListener listener;

    public void setOnMinusAndPlusClickListener(OnMinusAndPlusClickListener listener) {
        this.listener = listener;
    }

    public MinusPlusView(@NonNull Context context) {
        this(context, null);
    }

    public MinusPlusView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MinusPlusView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        View.inflate(context, R.layout.view_add_sub, this);
    }

    @Override
    protected void onFinishInflate() {
        ivMinus = findViewById(R.id.ivMinus);
        ivPlus = findViewById(R.id.ivPlus);
        ivMinus.setOnClickListener(view -> {
            if (listener != null) {
                listener.onMinusClick();
            }
        });
        ivPlus.setOnClickListener(view -> {
            if (listener != null) {
                listener.onPlusClick();
            }
        });
        super.onFinishInflate();
    }


    public interface OnMinusAndPlusClickListener {
        void onMinusClick();

        void onPlusClick();
    }
}

