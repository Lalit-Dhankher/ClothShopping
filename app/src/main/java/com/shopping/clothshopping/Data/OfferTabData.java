package com.shopping.clothshopping.Data;

import java.util.List;

public class OfferTabData {
    private String appName;
    private String icon;
    private String themeColor;

    private List<OfferDataInner> offerDataInnerList;

    public OfferTabData(String appName, String icon,String themeColor,List offerDataInnerList) {
        this.appName = appName;
        this.icon = icon;
        this.themeColor=themeColor;
        this.offerDataInnerList=offerDataInnerList;


    }

    public String getAppName() {
        return appName;
    }
    public String getIcon() {
        return icon;
    }
    public String getThemeColor() {
        return themeColor;
    }

    public List<OfferDataInner> getOfferDataInnerList() {
        return offerDataInnerList;
    }

}
