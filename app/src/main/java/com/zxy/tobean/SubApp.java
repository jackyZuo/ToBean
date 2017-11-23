package com.zxy.tobean;


/**
 * 文件描述：
 * 作者：Created by jackyZuo on 2017/11/22.
 */
public class SubApp{
    public String icon;
    public String link;
    public String name;
    public String order;
    public String status;
    public String type;
    public String version;

    @Override
    public String toString() {
        return "{" +
                "icon='" + icon + '\'' +
                ", link='" + link + '\'' +
                ", name='" + name + '\'' +
                ", order='" + order + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
