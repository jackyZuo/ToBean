package com.zxy.tobean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 文件描述：比较复杂的bean
 * 作者：Created by jackyZuo on 2017/11/22.
 */
public class HomeInfoResp extends BaseResp implements Serializable {

    public ResultObject resultObject;

    public static class ResultObject implements Serializable {
        public String appId;
        public String appName;
        public String version;
        public String versionName;
        public String status;
        public String forceUpdate;
        public String changeLog;
        public ArrayList<Banner> banners;
        public ArrayList<SubApp> subApps;

        @Override
        public String toString() {
            return "{" +
                    "appId='" + appId + '\'' +
                    ", appName='" + appName + '\'' +
                    ", version='" + version + '\'' +
                    ", versionName='" + versionName + '\'' +
                    ", status='" + status + '\'' +
                    ", forceUpdate='" + forceUpdate + '\'' +
                    ", changeLog='" + changeLog + '\'' +
                    ", banners=" + banners +
                    ", subApps=" + subApps +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "{" +
                "resultObject=" + resultObject +
                '}';
    }
}
