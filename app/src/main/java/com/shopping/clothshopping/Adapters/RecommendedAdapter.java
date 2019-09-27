package com.shopping.clothshopping.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.shopping.clothshopping.Data.RecommendedData;
import com.shopping.clothshopping.R;
import com.shopping.clothshopping.Web_View;
import java.util.List;
import Holder.IconViewHolder;

public class RecommendedAdapter extends RecyclerView.Adapter<IconViewHolder> {

    private Context context;
    private List<RecommendedData> mIconList;

    public RecommendedAdapter(Context mContext, List<RecommendedData> mIconList) {
        this.context = mContext;
        this.mIconList = mIconList;

    }


    @NonNull
    @Override
    public IconViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recommended_item, viewGroup, false);
        return new IconViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final IconViewHolder iconViewHolder, int position) {
        String iconImagepath=mIconList.get(position).getIcon();

        Glide.with(context).load(iconImagepath).centerCrop().into(iconViewHolder.ivRecommendedIcon);
        iconViewHolder.tvRecommendedIcon.setText(mIconList.get(position).getAppName());
        iconViewHolder.cvRecommended.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(context, Web_View.class);
                mIntent.putExtra("url", mIconList.get(iconViewHolder.getAdapterPosition()).getUrl());
                mIntent.putExtra("themeColor",mIconList.get(iconViewHolder.getAdapterPosition()).getThemeColor());
                mIntent.putExtra("icon",mIconList.get(iconViewHolder.getAdapterPosition()).getIcon());
                context.startActivity(mIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mIconList.size();
    }
}

