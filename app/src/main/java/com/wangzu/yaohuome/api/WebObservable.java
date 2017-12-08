package com.wangzu.yaohuome.api;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by AnJiuZhe on 2017/12/8 0008.
 * Email 1573335865@qq.com
 */

public class WebObservable {
    public static Observable<Document> getObservable(final String url) {
        return Observable.create(new ObservableOnSubscribe<Document>() {
            @Override
            public void subscribe(ObservableEmitter<Document> emitter) throws Exception {
                Document document = null;
                if (Api.COOKIES != null) {
                    document = Jsoup.connect(Api.HOST + url).cookies(Api.COOKIES).timeout(10000).get();
                }
                //Log.e("tag", document.body().toString());
                emitter.onNext(document);
            }
        });
    }
}
