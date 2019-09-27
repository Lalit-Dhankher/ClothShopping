package com.shopping.clothshopping.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.shopping.clothshopping.GiftTab;
import com.shopping.clothshopping.HomeTab;
import com.shopping.clothshopping.KidsTab;
import com.shopping.clothshopping.OfferTab;
import com.shopping.clothshopping.WomenTab;

public class TabAdapter extends FragmentPagerAdapter {
    private Context myContext;
    private static final int TOTAL_TABS =5;

    public TabAdapter(Context context, FragmentManager fm) {
        super(fm);
        myContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return HomeTab.newInstance("HomeTab, Instance 0");
            case 1:
                return WomenTab.newInstance("womenFashion, Instance 0");
            case 2:
                return KidsTab.newInstance("KidFashion, Instance 0");
            case 3:
                return GiftTab.newInstance("Gift, Instance 0");
            case 4:
                return OfferTab.newInstance("Offer, Instance 0");
            default:
                return HomeTab.newInstance("HomeTab, Instance 0");


        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Men";
            case 1:
                return "Women";
            case 2:
                return "Kids";
            case 3:
                return "Gift";
            case 4:
                return "Coupons";
            default:
                return "Men";

        }
    }

    @Override
    public int getCount() {
        return TOTAL_TABS;
    }
}
