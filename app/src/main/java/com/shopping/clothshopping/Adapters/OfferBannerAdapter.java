package com.shopping.clothshopping.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.shopping.clothshopping.Data.OfferBannerData;
import com.shopping.clothshopping.R;
import com.shopping.clothshopping.Web_View;

import java.util.List;

import Holder.OfferBannerHolder;

public class OfferBannerAdapter extends RecyclerView.Adapter<OfferBannerHolder> {

    private Context mContext;
    private List<OfferBannerData> mBannerImageList;

    public OfferBannerAdapter(Context mContext, List<OfferBannerData> mBannerImageList) {

        this.mContext = mContext;
        this.mBannerImageList = mBannerImageList;
    }


    @NonNull
    @Override
    public OfferBannerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.offer_banner_item, viewGroup, false);
        return new OfferBannerHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final OfferBannerHolder offerBannerHolder, int position) {
        String bannerImagepath=mBannerImageList.get(position).getBannerImage();

        Glide.with(mContext).load(bannerImagepath).into(offerBannerHolder.ivOfferBanner);
        offerBannerHolder.cvOfferBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(mContext, Web_View.class);
                mIntent.putExtra("url", mBannerImageList.get(offerBannerHolder.getAdapterPosition()).getUrl());
                mIntent.putExtra("themeColor",mBannerImageList.get(offerBannerHolder.getAdapterPosition()).getThemeColor());
                mIntent.putExtra("icon",mBannerImageList.get(offerBannerHolder.getAdapterPosition()).getIcon());
                mContext.startActivity(mIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mBannerImageList.size();
    }
}
