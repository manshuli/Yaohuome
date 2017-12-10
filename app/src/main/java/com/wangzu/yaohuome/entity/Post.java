package com.wangzu.yaohuome.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by AnJiuZhe on 2017/12/8 0008.
 * Email 1573335865@qq.com
 */

public class Post implements Parcelable {
    private String id;
    private String title;
    private String name;
    private String nameColor = "#555555";
    private String time;
    private String read;
    private String comment;
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameColor() {
        return nameColor;
    }

    public void setNameColor(String nameColor) {
        this.nameColor = nameColor;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Post() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.name);
        dest.writeString(this.nameColor);
        dest.writeString(this.time);
        dest.writeString(this.read);
        dest.writeString(this.comment);
        dest.writeString(this.url);
    }

    protected Post(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.name = in.readString();
        this.nameColor = in.readString();
        this.time = in.readString();
        this.read = in.readString();
        this.comment = in.readString();
        this.url = in.readString();
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel source) {
            return new Post(source);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };
}
