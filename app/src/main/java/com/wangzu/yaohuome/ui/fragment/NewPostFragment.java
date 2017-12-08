package com.wangzu.yaohuome.ui.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wangzu.yaohuome.R;
import com.wangzu.yaohuome.entity.Post;
import com.wangzu.yaohuome.presenter.NewPostPresenter;
import com.wangzu.yaohuome.ui.view.BaseFragment;
import com.wangzu.yaohuome.ui.view.NewPostView;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewPostFragment extends BaseFragment implements NewPostView {


    @BindView(R.id.newpost_recyclerview)
    RecyclerView mNewpostRecyclerview;
    NewPostPresenter mPresenter;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_new_post;
    }

    @Override
    public void initView() {
        mPresenter = new NewPostPresenter(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
        mNewpostRecyclerview.setLayoutManager(layoutManager);
        mNewpostRecyclerview.addItemDecoration(itemDecoration);

        mPresenter.requestData();
    }

    @Override
    public void loadData(List<Post> list) {

    }

    @Override
    public void failed(String msg) {

    }

    @Override
    public void error() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

}
