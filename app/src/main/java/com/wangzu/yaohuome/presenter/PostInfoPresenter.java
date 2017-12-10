package com.wangzu.yaohuome.presenter;

import com.wangzu.yaohuome.entity.PostInfo;
import com.wangzu.yaohuome.listener.RequestListener;
import com.wangzu.yaohuome.model.PostInfoModel;
import com.wangzu.yaohuome.ui.view.PostInfoView;

/**
 * Created by AnJiuZhe on 2017/12/10.
 * Email 1573335865@qq.com
 */

public class PostInfoPresenter implements RequestListener<PostInfo>{
    private PostInfoModel mModel;
    private PostInfoView mView;

    public PostInfoPresenter(PostInfoView view) {
        mModel = new PostInfoModel(this);
        mView = view;
    }

    public void requestData(String url,String postId){
        mView.showProgress();
        mModel.requestData(url,postId);
    }


    @Override
    public void success(PostInfo data) {
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
