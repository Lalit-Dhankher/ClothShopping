package com.shopping.clothshopping.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.shopping.clothshopping.Data.Constants;
import com.shopping.clothshopping.Data.OfferTabData;
import com.shopping.clothshopping.R;
import java.util.List;


import Holder.OfferTabHolder;

public class OfferTabAdapter extends RecyclerView.Adapter<OfferTabHolder>  {

    private Context mContext;
    private List<OfferTabData> mOfferTabIconList;
    private RecyclerView rvOfferTab, rvOfferTabInner;
    private LinearLayout llOfferTabInner;
    private ImageView ivOfferTabInner;




    public OfferTabAdapter(Context context, List<OfferTabData> mOfferTabIconList, RecyclerView rvOfferTab,RecyclerView rvOfferTabInner,LinearLayout llOfferTabInner,ImageView ivOfferTabInner) {
        this.mContext = context;
        this.mOfferTabIconList = mOfferTabIconList;
        this.rvOfferTab=rvOfferTab;
        this.llOfferTabInner=llOfferTabInner;
        this.rvOfferTabInner=rvOfferTabInner;
        this.ivOfferTabInner=ivOfferTabInner;
    }


    @NonNull
    @Override
    public OfferTabHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.offer_tab_item, viewGroup, false);
        return new OfferTabHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final OfferTabHolder offerTabHolder, final int position) {
        String imagePath=mOfferTabIconList.get(position).getIcon();

        Glide.with(mContext).load(imagePath).into(offerTabHolder.ivOfferTabIcon);
        offerTabHolder.tvOfferTabIcon.setText(mOfferTabIconList.get(position).getAppName());
        offerTabHolder.cvOfferTab.setCardBackgroundColor(Color.parseColor(mOfferTabIconList.get(position).getThemeColor()));
        offerTabHolder.cvOfferTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OfferTabInnerAdapter offerTabInnerAdapter = new OfferTabInnerAdapter(mContext, mOfferTabIconList.get(position),ivOfferTabInner,llOfferTabInner,rvOfferTab, rvOfferTabInner);
                rvOfferTabInner.setAdapter(offerTabInnerAdapter);

                Constants.isOfferButtonClicked=true;
                rvOfferTab.setVisibility(View.GONE);
                rvOfferTabInner.setVisibility(View.VISIBLE);
                llOfferTabInner.setVisibility(View.VISIBLE);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mOfferTabIconList.size();
    }
}
