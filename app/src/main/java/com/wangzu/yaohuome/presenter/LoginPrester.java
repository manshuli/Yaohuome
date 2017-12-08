package com.wangzu.yaohuome.presenter;

import com.wangzu.yaohuome.listener.RequestListener;
import com.wangzu.yaohuome.model.LoginModel;
import com.wangzu.yaohuome.ui.view.LoginView;

/**
 * Created by AnJiuZhe on 2017/12/8 0008.
 * Email 1573335865@qq.com
 */

public class LoginPrester implements RequestListener {
    private LoginModel mModel;
    private LoginView mView;

    public LoginPrester(LoginView view) {
        mModel = new LoginModel(this);
        mView = view;
    }

    public void login() {
        mView.showProgress();
        mModel.login(mView.getUserName(), mView.getPassword());
    }

    @Override
    public void success() {
        mView.hideProgress();
        mView.success();
    }

    @Override
    public void failed(String msg) {
        mView.hideProgress();
        mView.failed(msg);
    }

    @Override
    public void error() {
        mView.hideProgress();
        mView.error();
    }
}
