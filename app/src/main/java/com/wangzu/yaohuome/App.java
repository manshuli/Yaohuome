package com.wangzu.yaohuome;

import android.app.Application;

import com.zzhoujay.richtext.RichText;

/**
 * Created by AnJiuZhe on 2017/12/7 0007.
 * Email 1573335865@qq.com
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RichText.initCacheDir(getApplicationContext());
    }
}
