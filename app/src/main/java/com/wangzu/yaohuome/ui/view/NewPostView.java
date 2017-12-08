package com.wangzu.yaohuome.ui.view;

import com.wangzu.yaohuome.entity.Post;

import java.util.List;

/**
 * Created by AnJiuZhe on 2017/12/8 0008.
 * Email 1573335865@qq.com
 */

public interface NewPostView extends BaseView {
    void loadData(List<Post> list);
}
