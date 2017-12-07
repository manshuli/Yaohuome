package com.wangzu.yaohuome.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

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
        mUnbinder = ButterKnife.bind(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        initView();
    }

    @Override
    protected void onDestroy() {
        mUnbinder.unbind();
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


}
