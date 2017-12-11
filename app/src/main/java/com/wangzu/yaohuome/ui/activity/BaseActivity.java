package com.wangzu.yaohuome.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.jude.swipbackhelper.SwipeBackHelper;
import com.wangzu.yaohuome.utils.ActivityManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by AnJiuZhe on 2017/12/7 0007.
 * Email 1573335865@qq.com
 */

public abstract class BaseActivity extends AppCompatActivity {

    public abstract int layoutId();

    public abstract void initView();

    Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        ActivityManager.add(this);
        mUnbinder = ButterKnife.bind(this);
        SwipeBackHelper.onCreate(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        initView();
    }

    @Override
    protected void onDestroy() {
        Log.e("tag", "destroy");
        mUnbinder.unbind();
        ActivityManager.remove(this);
        SwipeBackHelper.onDestroy(this);
        super.onDestroy();
    }

    protected void setSupportToolBar(android.support.v7.widget.Toolbar toolBar) {
        setSupportActionBar(toolBar);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        SwipeBackHelper.onPostCreate(this);
    }

}
