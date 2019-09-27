package com.shopping.clothshopping.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.shopping.clothshopping.Data.TrackOrderData;
import com.shopping.clothshopping.R;
import com.shopping.clothshopping.Web_View;

import java.util.List;

import Holder.TrackOrderHolder;

public class TrackOrderAdapter extends RecyclerView.Adapter<TrackOrderHolder>  {
    private Context mContext;
    private List<TrackOrderData> mTrackOrderList;

    public TrackOrderAdapter(Context mContext, List<TrackOrderData> mTrackOrderList) {
        this.mContext = mContext;
        this.mTrackOrderList = mTrackOrderList;
    }

    @NonNull
    @Override
    public TrackOrderHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.all_app_item, viewGroup, false);
        return new TrackOrderHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final TrackOrderHolder trackOrderHolder, int position) {
        String iconImagepath=mTrackOrderList.get(position).getIcon();

        Glide.with(mContext).load(iconImagepath).centerCrop().into(trackOrderHolder.ivTrackOrder);
        trackOrderHolder.tvTrackOrder.setText(mTrackOrderList.get(position).getAppName());
        trackOrderHolder.cvTrackOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(mContext, Web_View.class);
                mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mIntent.putExtra("url", mTrackOrderList.get(trackOrderHolder.getAdapterPosition()).getUrl());
                mIntent.putExtra("themeColor",mTrackOrderList.get(trackOrderHolder.getAdapterPosition()).getThemeColor());
                mIntent.putExtra("icon",mTrackOrderList.get(trackOrderHolder.getAdapterPosition()).getIcon());
                mContext.startActivity(mIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mTrackOrderList.size();
    }
}
