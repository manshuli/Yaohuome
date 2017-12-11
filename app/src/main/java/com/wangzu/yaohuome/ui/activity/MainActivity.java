package com.wangzu.yaohuome.ui.activity;

import android.os.Bundle;

import com.jude.swipbackhelper.SwipeBackHelper;
import com.wangzu.yaohuome.R;
import com.zzhoujay.richtext.RichText;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SwipeBackHelper.getCurrentPage(this).setSwipeBackEnable(false);
    }

    @Override
    public int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

    }

    @Override
    protected void onDestroy() {
        RichText.recycle();
        super.onDestroy();
    }
}
