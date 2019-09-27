package com.shopping.clothshopping.Adapters;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.shopping.clothshopping.Data.Constants;
import com.shopping.clothshopping.Data.OfferTabData;
import com.shopping.clothshopping.R;
import com.shopping.clothshopping.Web_View;

import Holder.OfferTabHolder;

public class OfferTabInnerAdapter extends RecyclerView.Adapter<OfferTabHolder> {
    private Context mContext;
    private ImageView ivOfferTabInner;
    private LinearLayout llOfferTabInner;
    private OfferTabData clickedOfferTabData;
    private RecyclerView rvOfferTab,rvOfferTabInner;


    public OfferTabInnerAdapter(Context context, OfferTabData clickedOfferTabData, ImageView ivOfferTabInner, LinearLayout llOfferTabInner, RecyclerView rvOfferTab, RecyclerView rvOfferTabInner) {
        this.mContext = context;
        this.clickedOfferTabData = clickedOfferTabData;
        this.ivOfferTabInner = ivOfferTabInner;
        this.llOfferTabInner = llOfferTabInner;
        this.rvOfferTab = rvOfferTab;
        this.rvOfferTabInner=rvOfferTabInner;
    }

    @NonNull
    @Override
    public OfferTabHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.offer_tab_inner_item, viewGroup, false);
        return new OfferTabHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final OfferTabHolder offerTabHolder, final int position) {
        String imagePath = clickedOfferTabData.getIcon();

        Glide.with(mContext).load(imagePath).into(ivOfferTabInner);
        llOfferTabInner.setBackgroundColor(Color.parseColor(clickedOfferTabData.getThemeColor()));
        offerTabHolder.tvVerified.setText(clickedOfferTabData.getOfferDataInnerList().get(position).getVerified());
        offerTabHolder.tvUsed.setText(clickedOfferTabData.getOfferDataInnerList().get(position).getUsed());
        offerTabHolder.tvOff.setText(clickedOfferTabData.getOfferDataInnerList().get(position).getOff());
        offerTabHolder.tvOffOn.setText(clickedOfferTabData.getOfferDataInnerList().get(position).getOffOn());
        offerTabHolder.tvOffOnAgain.setText(clickedOfferTabData.getOfferDataInnerList().get(position).getOffOnAgain());
        offerTabHolder.tvCoupon.setText(clickedOfferTabData.getOfferDataInnerList().get(position).getCoupon());
        offerTabHolder.cvCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("", offerTabHolder.tvCoupon.getText());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(mContext, "Coupon Copied to Clipboard", Toast.LENGTH_LONG).show();
                Constants.isOfferButtonClicked=false;

                Intent mIntent = new Intent(mContext, Web_View.class);
                mIntent.putExtra("url", clickedOfferTabData.getOfferDataInnerList().get(position).getUrl());
                mIntent.putExtra("themeColor", clickedOfferTabData.getThemeColor());
                mIntent.putExtra("icon", clickedOfferTabData.getIcon());
                mContext.startActivity(mIntent);
                rvOfferTab.setVisibility(View.VISIBLE);
                llOfferTabInner.setVisibility(View.GONE);
                rvOfferTabInner.setVisibility(View.GONE);

            }
        });

    }

    @Override
    public int getItemCount() {
        return clickedOfferTabData.getOfferDataInnerList().size();
    }
}
