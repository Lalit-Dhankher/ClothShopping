package Holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shopping.clothshopping.R;

public class AllAppIconViewHolder extends RecyclerView.ViewHolder {

    public ImageView ivAllAppIcon;
    public TextView tvAllAppIcon;
    public CardView cvAllApp;


    public AllAppIconViewHolder(@NonNull View itemView) {
        super(itemView);

        ivAllAppIcon = itemView.findViewById(R.id.ivAllAppIcon);
        tvAllAppIcon = itemView.findViewById(R.id.tvAllAppIcon);
        cvAllApp=itemView.findViewById(R.id.cvAllApp);
    }
}
