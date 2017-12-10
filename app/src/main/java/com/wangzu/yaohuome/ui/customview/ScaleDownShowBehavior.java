package com.wangzu.yaohuome.ui.customview;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by AnJiuZhe on 2017/12/9.
 * Email 1573335865@qq.com
 */

public class ScaleDownShowBehavior extends FloatingActionButton.Behavior {

    public ScaleDownShowBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        if ((dyConsumed > 0 || dyUnconsumed > 0) && child.isShown()) {//往下滑
            child.setVisibility(View.INVISIBLE);
            child.animate().scaleX(0.0f).scaleY(0.0f).alpha(0.0f).setDuration(300);

        } else if ((dyConsumed < 0 || dyUnconsumed < 0) && !child.isShown()) {//往上滑
            child.setVisibility(View.VISIBLE);
            child.animate().scaleX(1.0f).scaleY(1.0f).alpha(1.0f).setDuration(300);
        }
    }
}
