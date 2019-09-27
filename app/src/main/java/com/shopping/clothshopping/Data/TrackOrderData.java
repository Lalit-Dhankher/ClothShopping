package com.shopping.clothshopping.Data;

public class TrackOrderData {

    private String appName;
    private String icon;
    private String url;
    private String themeColor;


    public TrackOrderData(String appName, String icon, String themeColor,String url) {
        this.appName = appName;
        this.icon = icon;
        this.themeColor = themeColor;
        this.url=url;


    }

    public String getAppName() {
        return appName;
    }

    public String getIcon() {
        return icon;
    }
    public String getUrl() {
        return url;
    }
    public String getThemeColor() {
        return themeColor;
    }
}

