package com.wangzu.yaohuome.model;

import com.wangzu.yaohuome.api.Api;
import com.wangzu.yaohuome.api.WebObservable;
import com.wangzu.yaohuome.entity.Post;
import com.wangzu.yaohuome.listener.RequestListener;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by AnJiuZhe on 2017/12/8 0008.
 * Email 1573335865@qq.com
 */

public class NewPostModel {

    private RequestListener<List<Post>> mListener;
    private List<Post> posts;

    public NewPostModel(RequestListener<List<Post>> listener) {
        mListener = listener;
    }

    public void requestData() {

        WebObservable.getObservable(Api.NEWPOSTURL)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .doOnNext(new Consumer<Document>() {
                    @Override
                    public void accept(Document document) throws Exception {
                        posts = getListPost(document);
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Document>() {
                    @Override
                    public void accept(Document document) throws Exception {
                        mListener.success(posts);
                    }
                });
    }

    private List<Post> getListPost(Document document) {
        //Log.e("tag", document.body().toString());
        List<Post> posts = new ArrayList<>();

        Elements elements = document.select("div[class^=line]");
        for (Element element : elements) {

            StringBuffer stringBuffer = new StringBuffer(element.text());

            String time = stringBuffer.substring(stringBuffer.length() - 6, stringBuffer.length());
            stringBuffer.delete(stringBuffer.length() - 6, stringBuffer.length());

            String read = stringBuffer.substring(stringBuffer.lastIndexOf("/"), stringBuffer.length());
            stringBuffer.delete(stringBuffer.lastIndexOf("/"), stringBuffer.length());

            String comment = stringBuffer.substring(stringBuffer.lastIndexOf("/"), stringBuffer.length());
            stringBuffer.delete(stringBuffer.lastIndexOf("/"), stringBuffer.length());

            String name = stringBuffer.substring(stringBuffer.lastIndexOf(" "), stringBuffer.length());
            stringBuffer.delete(stringBuffer.lastIndexOf(" "), stringBuffer.length());

            String title = stringBuffer.substring(stringBuffer.indexOf(".") + 1, stringBuffer.length());

            //Log.e("tag",title+"\t"+name+"\t"+comment+"\t"+read+"\t"+time);

            Post post = new Post();
            post.setTitle(title);
            post.setName(name);
            post.setComment(comment);
            post.setRead(read);
            post.setTime(time);
            posts.add(post);
        }
        return posts;
    }
}
