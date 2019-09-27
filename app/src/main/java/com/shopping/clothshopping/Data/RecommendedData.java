package com.shopping.clothshopping.Data;

public class RecommendedData {

    private String appName;
    private String url;
    private String icon;
    private String themeColor;


    public RecommendedData(String appName, String url, String icon, String themeColor) {
        this.appName = appName;
        this.url = url;
        this.icon = icon;
        this.themeColor=themeColor;


    }

    public String getAppName() {
        return appName;
    }

    public String getUrl() {
        return url;
    }


    public String getIcon() {
        return icon;
    }
    public String getThemeColor() {
        return themeColor;
    }
}
