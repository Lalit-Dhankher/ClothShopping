package com.shopping.clothshopping.Data;

public class OfferBannerData {
    private String themeColor;
    private String url;
    private String icon;
    private String bannerImage;



    public OfferBannerData(String themeColor, String url,String icon,String bannerImage) {
        this.url = url;
        this.themeColor = themeColor;
        this.icon=icon;
        this.bannerImage=bannerImage;

    }

    public String getThemeColor() {
        return themeColor;
    }

    public String getUrl() {
        return url;
    }
    public String getIcon() {
        return icon;
    }
    public String getBannerImage() {
        return bannerImage;
    }
}
