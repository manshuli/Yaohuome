package com.wangzu.yaohuome.ui.fragment;


import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wangzu.yaohuome.R;
import com.wangzu.yaohuome.entity.Post;
import com.wangzu.yaohuome.presenter.NewPostPresenter;
import com.wangzu.yaohuome.ui.adapter.PostAdapter;
import com.wangzu.yaohuome.ui.view.BaseFragment;
import com.wangzu.yaohuome.ui.view.NewPostView;

import java.util.List;

import butterknife.BindView;

/**
 * 最新帖子Fragment
 * A simple {@link Fragment} subclass.
 */
public class NewPostFragment extends BaseFragment implements NewPostView, BaseQuickAdapter.RequestLoadMoreListener {


    @BindView(R.id.newpost_recyclerview)
    RecyclerView mNewpostRecyclerview;
    @BindView(R.id.backTopBtn)
    FloatingActionButton backTopBtn;
    NewPostPresenter mPresenter;
    int page = 1;
    private PostAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_new_post;
    }

    @Override
    public void initView() {
        mPresenter = new NewPostPresenter(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        //DividerItemDecoration itemDecoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
        mNewpostRecyclerview.setLayoutManager(layoutManager);
        //mNewpostRecyclerview.addItemDecoration(itemDecoration);

        backTopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNewpostRecyclerview.scrollToPosition(0);
                backTopBtn.setVisibility(View.INVISIBLE);
            }
        });

        mPresenter.requestData(page);
    }

    @Override
    public void loadData(List<Post> list) {
        if (mAdapter == null) {
            mAdapter = new PostAdapter(R.layout.item_post_recyclerview, list);
            mAdapter.setPreLoadNumber(5);
            mAdapter.setOnLoadMoreListener(this, mNewpostRecyclerview);
            mNewpostRecyclerview.setAdapter(mAdapter);
        } else {
            mAdapter.addData(list);
            mAdapter.loadMoreComplete();
        }

    }

    @Override
    public void failed(String msg) {

    }

    @Override
    public void error() {
        mAdapter.loadMoreFail();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.requestData(++page);
    }
}
