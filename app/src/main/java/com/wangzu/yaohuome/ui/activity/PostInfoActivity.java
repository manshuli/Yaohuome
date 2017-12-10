package com.wangzu.yaohuome.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.wangzu.yaohuome.R;
import com.wangzu.yaohuome.entity.Post;
import com.wangzu.yaohuome.entity.PostInfo;
import com.wangzu.yaohuome.presenter.PostInfoPresenter;
import com.wangzu.yaohuome.ui.view.PostInfoView;
import com.zzhoujay.richtext.RichText;
import com.zzhoujay.richtext.callback.OnImageClickListener;

import java.util.List;

import butterknife.BindView;

public class PostInfoActivity extends BaseActivity implements PostInfoView {

    @BindView(R.id.toorbar)
    Toolbar mToorbar;
    @BindView(R.id.nameTv)
    TextView mNameTv;
    @BindView(R.id.timeTv)
    TextView mTimeTv;
    @BindView(R.id.titleTv)
    TextView mTitleTv;
    @BindView(R.id.contentTv)
    TextView mContentTv;
    PostInfoPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportToolBar(mToorbar);
    }

    @Override
    public int layoutId() {
        return R.layout.activity_post_info;
    }

    @Override
    public void initView() {
        Post post = getIntent().getParcelableExtra("post");
        mNameTv.setText(post.getName());
        mTitleTv.setText(post.getTitle());

        mPresenter = new PostInfoPresenter(this);
        mPresenter.requestData(post.getUrl(),post.getId());

    }


    @Override
    public void loadData(PostInfo postInfo) {
        mTimeTv.setText(postInfo.getTime());
        RichText.fromHtml(postInfo.getContent())
                .imageClick(new OnImageClickListener() {
                    @Override
                    public void imageClicked(List<String> imageUrls, int position) {
                        Log.e("tag", imageUrls.get(position));
                    }
                }).autoPlay(true)
                .into(mContentTv);
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
