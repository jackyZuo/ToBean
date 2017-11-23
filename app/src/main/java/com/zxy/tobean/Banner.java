package com.zxy.tobean;

/**
 * 文件描述：
 * 作者：Created by jackyZuo on 2017/11/22.
 */
public class Banner {
    public String id;
    public String src;
    public String order;
    public String link;

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", src='" + src + '\'' +
                ", order='" + order + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
