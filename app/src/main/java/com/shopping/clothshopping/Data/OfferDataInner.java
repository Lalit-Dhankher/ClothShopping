package com.shopping.clothshopping.Data;

public class OfferDataInner  {
    private String url;
    private String verified;
    private String used;
    private String off;
    private String offOn;
    private String offOnAgain;
    private String coupon;

    public OfferDataInner(String url,String used,String off,String offOn,String offOnAgain, String coupon,String verified){
        this.url=url;
        this.verified = verified;
        this.used = used;
        this.off=off;
        this.offOn=offOn;
        this.offOnAgain = offOnAgain;
        this.coupon=coupon;


    }

    public String getUrl() {
        return url;
    }
    public String getVerified() {
        return verified;
    }
    public String getUsed() {
        return used;
    }
    public String getOff() {
        return off;
    }
    public String getOffOn() {
        return offOn;
    }
    public String getOffOnAgain() {
        return offOnAgain;
    }
    public String getCoupon() {
        return coupon;
    }



}
