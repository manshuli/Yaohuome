package com.wangzu.yaohuome.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AnJiuZhe on 2017/12/8 0008.
 * Email 1573335865@qq.com
 */

public class ActivityManager {

    public static List<Activity> sActivityList = new ArrayList<>();

    public static void add(Activity activity) {
        sActivityList.add(activity);
    }

    public static void remove(Activity activity) {
        sActivityList.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : sActivityList) {
            activity.finish();
        }
    }
}
