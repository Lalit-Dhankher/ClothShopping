package com.shopping.clothshopping;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.shopping.clothshopping.Adapters.AllAppAdapter;
import com.shopping.clothshopping.Adapters.OfferBannerAdapter;
import com.shopping.clothshopping.Adapters.RecommendedAdapter;
import com.shopping.clothshopping.Data.AllAppData;
import com.shopping.clothshopping.Data.OfferBannerData;
import com.shopping.clothshopping.Data.RecommendedData;
import java.util.List;

public class HomeTab extends Fragment {


    RecyclerView rvRecommended,rvOfferBanner, rvAllApp;
    LinearLayout llRecommanded;
    private ShimmerFrameLayout mShimmerViewContainer;
    CardView cv5star;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);



        rvRecommended=view.findViewById(R.id.rvRecommended);
        rvOfferBanner=view.findViewById(R.id.rvOfferBanner);
        rvAllApp=view.findViewById(R.id.rvAllApp);
        llRecommanded=view.findViewById(R.id.llRecommanded);
        mShimmerViewContainer=view.findViewById(R.id.shimmer_view_container);
        cv5star=view.findViewById(R.id.cv5star);

        llRecommanded.setVisibility(View.VISIBLE);

        AndroidNetworking.initialize(getContext());



        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        rvRecommended.setLayoutManager(gridLayoutManager);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvOfferBanner.setLayoutManager(linearLayoutManager);

        GridLayoutManager gridLayoutManagerforallApp = new GridLayoutManager(getContext(), 3);
        rvAllApp.setLayoutManager(gridLayoutManagerforallApp);





        //        pass data torecycler view of recommended

        AndroidNetworking.get("https://clothshopping-47373.firebaseio.com/menfashion/recommanded.json")
                .setPriority(Priority.HIGH)
                .build()
                .getAsObjectList(RecommendedData.class, new ParsedRequestListener<List<RecommendedData>>() {
                    @Override
                    public void onResponse(List<RecommendedData> recommendedData) {
                        RecommendedAdapter recommendedAdapter = new RecommendedAdapter(getContext(), recommendedData);
                        rvRecommended.setAdapter(recommendedAdapter);

                        mShimmerViewContainer.stopShimmer();
                        mShimmerViewContainer.setVisibility(View.GONE);

                    }
                    @Override
                    public void onError(ANError anError) {

                    }
                });



        //        pass data torecycler view of all app

        AndroidNetworking.get("https://clothshopping-47373.firebaseio.com/menfashion/all_app.json")
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

        AndroidNetworking.get("https://clothshopping-47373.firebaseio.com/banner/men.json")
                .setPriority(Priority.HIGH)
                .build()
                .getAsObjectList(OfferBannerData.class, new ParsedRequestListener<List<OfferBannerData>>() {
                    @Override
                    public void onResponse(List<OfferBannerData> offerBannerData) {
                        OfferBannerAdapter offerBannerAdapter = new OfferBannerAdapter(getContext(), offerBannerData);
                        rvOfferBanner.setAdapter(offerBannerAdapter);
                    }
                    @Override
                    public void onError(ANError anError) {
                    }
                });



        cv5star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("market://details?id=" + getContext().getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                // To count with Play market backstack, After pressing back button,
                // to taken back to our application, we need to add following flags to intent.
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + getContext().getPackageName())));
                }
            }
        });




        return view;




    }

    public static HomeTab newInstance(String text) {
        HomeTab homeTab = new HomeTab();
        Bundle b = new Bundle();
        b.putString("msg", text);

        homeTab.setArguments(b);
        return homeTab;
    }

}
