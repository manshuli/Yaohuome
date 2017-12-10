package com.wangzu.yaohuome.ui.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wangzu.yaohuome.R;
import com.wangzu.yaohuome.entity.Post;

import java.util.List;

/**
 * Created by AnJiuZhe on 2017/12/8 0008.
 * Email 1573335865@qq.com
 */

public class PostAdapter extends BaseQuickAdapter<Post, BaseViewHolder> {

    public PostAdapter(int layoutResId, @Nullable List<Post> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Post item) {
        helper.setText(R.id.name, item.getName());
        helper.setTextColor(R.id.name, Color.parseColor(item.getNameColor()));
        helper.setText(R.id.time, item.getTime());
        helper.setText(R.id.read, item.getRead());
        helper.setText(R.id.comment, item.getComment());
        helper.setText(R.id.title, item.getTitle());
    }
}
