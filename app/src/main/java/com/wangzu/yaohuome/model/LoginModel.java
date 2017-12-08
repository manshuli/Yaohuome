package com.wangzu.yaohuome.model;

import android.util.Log;

import com.wangzu.yaohuome.api.Api;
import com.wangzu.yaohuome.listener.RequestListener;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.wangzu.yaohuome.api.Api.HOST;
import static com.wangzu.yaohuome.api.Api.LOGIN_RRL;
import static com.wangzu.yaohuome.api.Api.USER_AGENT;
import static com.wangzu.yaohuome.api.Api.USER_AGENT_VALUE;

/**
 * Created by AnJiuZhe on 2017/12/8 0008.
 * Email 1573335865@qq.com
 */

public class LoginModel {

    private RequestListener mListener;

    public LoginModel(RequestListener listener) {
        mListener = listener;
    }

    public void login(final String username, final String password) {
        Observable.create(new ObservableOnSubscribe<Document>() {
            @Override
            public void subscribe(ObservableEmitter<Document> emitter) throws Exception {
                Document document = getDocument(username, password);
                emitter.onNext(document);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Document>() {
                    @Override
                    public void accept(Document document) throws Exception {
                        String status = document.select(".tip").text();
                        Log.e("tag", status);
                        if (status.contains("成功")) {
                            mListener.success(status);
                        } else {
                            mListener.failed(status);
                        }
                    }
                });
    }

    private Document getDocument(String username, String password) throws IOException {
        /*
         * 第一次请求
         * 获取登陆提交的表单信息，及修改其提交data数据（login，password）
         */
        Connection con = Jsoup.connect(HOST + LOGIN_RRL);  // 获取connection
        con.header(USER_AGENT, USER_AGENT_VALUE);   // 配置模拟浏览器
        Connection.Response rs = con.execute();     // 获取响应
        Document d1 = Jsoup.parse(rs.body());       // 转换为Dom树
        List<Element> eleList = d1.select("form");  // 获取提交form表单，可以通过查看页面源码代码得知
        // 获取cooking和表单属性
        Map<String, String> datas = new HashMap<>();
        for (Element e : eleList.get(0).getAllElements()) {
            // 设置用户名
            if (e.attr("name").equals("logname")) {
                e.attr("value", username);
            }
            // 设置用户密码
            if (e.attr("name").equals("logpass")) {
                e.attr("value", password);
            }
            // 排除空值表单属性
            if (e.attr("name").length() > 0) {
                datas.put(e.attr("name"), e.attr("value"));
            }
        }
        /*
         * 第二次请求，以post方式提交表单数据以及cookie信息
         */
        Connection con2 = Jsoup.connect(HOST + LOGIN_RRL);
        con2.header(USER_AGENT, USER_AGENT_VALUE);
        // 设置cookie和post上面的map数据
        Connection.Response login = con2.ignoreContentType(true).followRedirects(true).method(Connection.Method.POST)
                .data(datas).cookies(rs.cookies()).execute();
        // 登陆成功后的cookie信息，可以保存到本地，以后登陆时，只需一次登陆即可
        Map<String, String> map = login.cookies();//error cookies -3-0-0-0-0
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
        Api.COOKIES = map;
        return Jsoup.parse(login.body());
    }
}
