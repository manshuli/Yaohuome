package com.wangzu.yaohuome.presenter;

import com.wangzu.yaohuome.entity.Post;
import com.wangzu.yaohuome.listener.RequestListener;
import com.wangzu.yaohuome.model.NewPostModel;
import com.wangzu.yaohuome.ui.view.NewPostView;

import java.util.List;

/**
 * Created by AnJiuZhe on 2017/12/8 0008.
 * Email 1573335865@qq.com
 */

public class NewPostPresenter implements RequestListener<List<Post>>{
    private NewPostModel mModel;
    private NewPostView mView;

    public NewPostPresenter(NewPostView view) {
        mModel = new NewPostModel(this);
        mView = view;
    }

    public void requestData(int page){
        mView.showProgress();
        mModel.requestData(page);
    }

    @Override
    public void success(List<Post> data) {
        mView.hideProgress();
        mView.loadData(data);
    }

    @Override
    public void failed(String msg) {
        mView.failed(msg);
    }

    @Override
    public void error() {
        mView.error();
    }
}
