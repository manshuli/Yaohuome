package com.wangzu.yaohuome.listener;

/**
 * Created by AnJiuZhe on 2017/12/8 0008.
 * Email 1573335865@qq.com
 */

public interface RequestListener<T> {
    void success(T data);

    void failed(String msg);

    void error();
}
