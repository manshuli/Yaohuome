package com.wangzu.yaohuome.model;

import android.text.TextUtils;

import com.wangzu.yaohuome.api.Api;
import com.wangzu.yaohuome.api.WebObservable;
import com.wangzu.yaohuome.entity.Post;
import com.wangzu.yaohuome.listener.RequestListener;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
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

    public void requestData(int page) {
        try {
            WebObservable.getObservable(Api.NEWPOSTURL + page)
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
        } catch (IOException e) {
            mListener.error();
            e.printStackTrace();
        }
    }

    private List<Post> getListPost(Document document) {
        //Log.e("tag", document.body().toString());
        List<Post> posts = new ArrayList<>();

        Elements elements = document.select("div[class^=line]");
        for (Element element : elements) {

            Post post = new Post();

            Element titleElement = element.selectFirst("a[href^=/bbs-]");
            String url = titleElement.attr("href");
            String title = titleElement.text();
            String id = url.substring(url.indexOf("-") + 1, url.indexOf("."));

            StringBuffer sb = new StringBuffer(element.ownText());
            String read = sb.substring(sb.lastIndexOf("/") + 1, sb.length());
            String name = sb.delete(sb.lastIndexOf("/"), sb.length()).substring(sb.indexOf(" "), sb.lastIndexOf("/")).trim();

            if (TextUtils.isEmpty(name.trim())) {
                Element fontElement = element.select("font").first();
                if (fontElement != null) {
                    name = fontElement.text();
                    String nameColor = fontElement.attr("color");
                    post.setNameColor(nameColor);
                }
            }

            String time = element.selectFirst("span").text();
            String comment = element.selectFirst("a[href^=/bbs/book_re.aspx?]").text() + "回";

            post.setId(id);
            post.setTitle(title);
            post.setName(name);
            post.setComment(comment);
            post.setRead(read);
            post.setTime(time);
            post.setUrl(url);
            posts.add(post);
        }
        return posts;
    }
}
