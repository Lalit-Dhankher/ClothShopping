package com.shopping.clothshopping;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.shopping.clothshopping.Adapters.AllAppAdapter;
import com.shopping.clothshopping.Adapters.OfferBannerAdapter;
import com.shopping.clothshopping.Data.AllAppData;
import com.shopping.clothshopping.Data.OfferBannerData;

import java.util.List;

public class KidsTab extends Fragment {

    RecyclerView rvOfferBanner, rvAllApp;
    private ShimmerFrameLayout mShimmerViewContainer;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        rvOfferBanner=view.findViewById(R.id.rvOfferBanner);
        rvAllApp=view.findViewById(R.id.rvAllApp);
        mShimmerViewContainer=view.findViewById(R.id.shimmer_view_container);

        AndroidNetworking.initialize(getContext());


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvOfferBanner.setLayoutManager(linearLayoutManager);

        GridLayoutManager gridLayoutManagerforallApp = new GridLayoutManager(getContext(), 3);
        rvAllApp.setLayoutManager(gridLayoutManagerforallApp);






        //        pass data torecycler view of all app

        AndroidNetworking.get("https://clothshopping-47373.firebaseio.com/kidsfashion/kids.json")
                .setPriority(Priority.HIGH)
                .build()
                .getAsObjectList(AllAppData.class, new ParsedRequestListener<List<AllAppData>>() {
                    @Override
                    public void onResponse(List<AllAppData> allAppData) {
                        AllAppAdapter allAppAdapter = new AllAppAdapter(getContext(), allAppData);
                        rvAllApp.setAdapter(allAppAdapter);
                    }
                    @Override
                    public void onError(ANError anError) {
                    }
                });





        //pass data to offer banner

        AndroidNetworking.get("https://clothshopping-47373.firebaseio.com/banner/kid.json")
                .setPriority(Priority.HIGH)
                .build()
                .getAsObjectList(OfferBannerData.class, new ParsedRequestListener<List<OfferBannerData>>() {
                    @Override
                    public void onResponse(List<OfferBannerData> offerBannerData) {
                        OfferBannerAdapter offerBannerAdapter = new OfferBannerAdapter(getContext(), offerBannerData);
                        rvOfferBanner.setAdapter(offerBannerAdapter);

                        mShimmerViewContainer.stopShimmer();
                        mShimmerViewContainer.setVisibility(View.GONE);

                    }
                    @Override
                    public void onError(ANError anError) {
                    }
                });




        return view;
    }





    public static KidsTab newInstance(String text) {
        KidsTab kidsTab = new KidsTab();
        Bundle b = new Bundle();
        b.putString("msg", text);

        kidsTab.setArguments(b);
        return kidsTab;
    }


}