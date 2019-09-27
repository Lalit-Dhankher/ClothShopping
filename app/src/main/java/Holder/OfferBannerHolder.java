package Holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.shopping.clothshopping.R;

public class OfferBannerHolder extends RecyclerView.ViewHolder {

    public ImageView ivOfferBanner;
    public CardView cvOfferBanner;


    public OfferBannerHolder(@NonNull View itemView) {
        super(itemView);

        ivOfferBanner = itemView.findViewById(R.id.ivOfferBanner);
        cvOfferBanner=itemView.findViewById(R.id.cvOfferBanner);

    }
}
