package com.example.ljy.model;

import java.util.List;

/**
 * Created by LJY on 2016/12/11.
 */
public class UpdateBean {
    /**
     * success : true
     * version : 90
     * log : 1、正文排版改进，支持代码高亮。
     2、周刊里推荐的活动显示活动时间和地点。
     3、修复翻页时不能调音量键的bug。
     4、个人设置页支持社交账号的取消关联。
     5、经网友指正，更改了缓存的图片数据路径到/Android/data/下(所以之前缓存的图片会丢失)。
     6、如果你的版本低于3.0.1,强烈建议升级到最新版本。

     * markets : ["baidu","meizu","xiaomi","360","oppo","smart","qq","huawei","wdj","wandoujia","letv"]
     * time : 1478048094000
     * name : 3.0.2
     * phone_apk : http://tuicoolapk.qiniudn.com/tuicool3.0.2.apk
     * pad_apk : http://tuicoolapk.qiniudn.com/tuicool_pad_3.0.2.apk
     * notification_num : 0
     */

    private boolean success;
    private int version;
    private String log;
    private long time;
    private String name;
    private String phone_apk;
    private String pad_apk;
    private int notification_num;
    private List<String> markets;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_apk() {
        return phone_apk;
    }

    public void setPhone_apk(String phone_apk) {
        this.phone_apk = phone_apk;
    }

    public String getPad_apk() {
        return pad_apk;
    }

    public void setPad_apk(String pad_apk) {
        this.pad_apk = pad_apk;
    }

    public int getNotification_num() {
        return notification_num;
    }

    public void setNotification_num(int notification_num) {
        this.notification_num = notification_num;
    }

    public List<String> getMarkets() {
        return markets;
    }

    public void setMarkets(List<String> markets) {
        this.markets = markets;
    }
}
