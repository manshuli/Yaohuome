package com.wangzu.yaohuome.api;

import java.util.Map;

/**
 * Created by AnJiuZhe on 2017/12/8 0008.
 * Email 1573335865@qq.com
 */

public class Api {
    public final static String USER_AGENT = "User-Agent";
    public final static String USER_AGENT_VALUE = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0";
    public final static String LOGIN_RRL = "/waplogin.aspx";

    public final static String HOST = "https://yaohw.com";
    public final static String NEWPOSTURL = "/bbs/book_list.aspx?action=new&page=";
    public final static String POSTCOMMENTURL = "/bbs/book_re.aspx?id=";
    public static Map<String,String> COOKIES = null;
}
//最新帖子 https://yaohw.com/bbs/book_list.aspx?action=new