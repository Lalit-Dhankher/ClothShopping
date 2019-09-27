package com.shopping.clothshopping.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.shopping.clothshopping.Data.AllAppData;
import com.shopping.clothshopping.R;
import com.shopping.clothshopping.Web_View;

import java.util.List;

import Holder.AllAppIconViewHolder;

public class AllAppAdapter extends RecyclerView.Adapter<AllAppIconViewHolder> {

    private Context mContext;
    private List<AllAppData> mAllAppIconList;

    public AllAppAdapter(Context mContext, List<AllAppData> mAllAppIconList) {
        this.mContext = mContext;
        this.mAllAppIconList = mAllAppIconList;
    }

    @NonNull
    @Override
    public AllAppIconViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.all_app_item, viewGroup, false);
        return new AllAppIconViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final AllAppIconViewHolder allAppIconViewHolder, int position) {
        String iconImagepath=mAllAppIconList.get(position).getIcon();

        Glide.with(mContext).load(iconImagepath).centerCrop().into(allAppIconViewHolder.ivAllAppIcon);
        allAppIconViewHolder.tvAllAppIcon.setText(mAllAppIconList.get(position).getAppName());
        allAppIconViewHolder.cvAllApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(mContext, Web_View.class);
                mIntent.putExtra("url", mAllAppIconList.get(allAppIconViewHolder.getAdapterPosition()).getUrl());
                mIntent.putExtra("themeColor",mAllAppIconList.get(allAppIconViewHolder.getAdapterPosition()).getThemeColor());
                mIntent.putExtra("icon",mAllAppIconList.get(allAppIconViewHolder.getAdapterPosition()).getIcon());
                mContext.startActivity(mIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mAllAppIconList.size();
    }
}

