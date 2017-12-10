package com.wangzu.yaohuome.model;

import android.util.Log;

import com.wangzu.yaohuome.api.Api;
import com.wangzu.yaohuome.api.WebObservable;
import com.wangzu.yaohuome.entity.PostInfo;
import com.wangzu.yaohuome.listener.RequestListener;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by AnJiuZhe on 2017/12/10.
 * Email 1573335865@qq.com
 */

public class PostInfoModel {
    private RequestListener<PostInfo> mListener;

    public PostInfoModel(RequestListener<PostInfo> listener) {
        mListener = listener;
    }

    public void requestData(String url, String postId) {

        try {
            WebObservable.getObservable(url)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Document>() {
                        @Override
                        public void accept(Document document) throws Exception {
                            PostInfo postInfo = new PostInfo();
                            //Log.e("tag", document.body().toString());
                            Elements elements = document.select("div.bbscontent").select("img");
                            for (Element element : elements) {
                                if (!element.attr("src").contains("http")) {
                                    element.attr("src", Api.HOST + element.attr("src"));
                                }
                            }
                            String content = document.select("div.bbscontent").html()
                                    .replace("<span id=\"KL_show_next_list\"></span>", "<br>");

                            String str = document.selectFirst("div.content").ownText();
                            String time = str.substring(str.lastIndexOf("]") + 2, str.length());

                            postInfo.setContent(content);
                            postInfo.setTime(time);

                            mListener.success(postInfo);
                        }
                    });
        } catch (IOException e) {
            mListener.error();
            e.printStackTrace();
        }

        try {
            WebObservable.getObservable(Api.POSTCOMMENTURL + postId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Document>() {
                        @Override
                        public void accept(Document document) throws Exception {
                            Log.e("tag", document.body().toString());
                        }
                    });
        } catch (IOException e) {
            mListener.error();
            e.printStackTrace();
        }
    }

}
