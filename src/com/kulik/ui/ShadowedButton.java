package com.kulik.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;

/**
 * User: kulik
 * Date: 12/19/12
 * Time: 11:22 PM
 */
public class ShadowedButton extends android.widget.Button {

    public static final int[] FOCUSED = { android.R.attr.state_focused};
    public static final int[] CHECKED = { android.R.attr.state_checked};
    public static final int[] NORMAL = { };

    private ColorStateList mShadowColors = null;
    private float mShadowDx;
    private float mShadowDy;
    private float mShadowRadius;


    public ShadowedButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, android.R.attr.buttonStyle);
    }

    public ShadowedButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    public ShadowedButton(Context context) {
        super(context);
        init(context, null, android.R.attr.buttonStyle);
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        //TODO doesnt work getDrawableState()
        int color = 0;
        if (isFocused()) {
            color = mShadowColors.getColorForState(FOCUSED, 0);
        } else {
            if (isPressed()) {
                color = mShadowColors.getColorForState(CHECKED, 0);
            } else {
                color = mShadowColors.getColorForState(NORMAL, 0);
            }
        }
        setShadowLayer(mShadowRadius, mShadowDx, mShadowDy, color);

        Log.d("onDraw", String.valueOf(color));
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ShadowedButton, defStyle, 0);

        final int attributeCount = a.getIndexCount();
        for (int i = 0; i < attributeCount; i++) {
            int curAttr = a.getIndex(i);

            switch (curAttr) {
                case R.styleable.ShadowedButton_shadowColor:
                    mShadowColors = a.getColorStateList(curAttr);
                    break;
                case R.styleable.ShadowedButton_shadowDx:
                    mShadowDx = a.getFloat(curAttr, 0);
                    break;
                case R.styleable.ShadowedButton_shadowDy:
                    mShadowDy = a.getFloat(curAttr, 0);
                    break;
                case R.styleable.ShadowedButton_shadowRadius:
                    mShadowRadius = a.getFloat(curAttr, 0);
                    break;
                default:
                    break;
            }
        }

        a.recycle();
    }
}
