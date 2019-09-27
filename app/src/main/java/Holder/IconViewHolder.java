package Holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shopping.clothshopping.R;


public class IconViewHolder extends RecyclerView.ViewHolder {
    public ImageView ivRecommendedIcon;
    public TextView tvRecommendedIcon;
    public CardView  cvRecommended;

    public IconViewHolder(@NonNull View itemView) {
        super(itemView);

         ivRecommendedIcon = itemView.findViewById(R.id.ivRecommendedIcon);
         tvRecommendedIcon = itemView.findViewById(R.id.tvRecommendedIcon);
        cvRecommended=itemView.findViewById(R.id.cvRecommended);


    }
}
