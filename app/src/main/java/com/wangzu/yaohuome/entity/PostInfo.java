package com.wangzu.yaohuome.entity;

/**
 * Created by AnJiuZhe on 2017/12/10.
 * Email 1573335865@qq.com
 */

public class PostInfo {
    private String content;
    private String time;
    private Comment mComment;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Comment getComment() {
        return mComment;
    }

    public void setComment(Comment comment) {
        mComment = comment;
    }
}
