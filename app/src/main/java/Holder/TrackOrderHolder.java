package Holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shopping.clothshopping.R;

public class TrackOrderHolder extends RecyclerView.ViewHolder {
    public ImageView ivTrackOrder;
    public TextView tvTrackOrder;
    public CardView cvTrackOrder;

    public TrackOrderHolder(@NonNull View itemView) {
        super(itemView);

        ivTrackOrder = itemView.findViewById(R.id.ivAllAppIcon);
        tvTrackOrder = itemView.findViewById(R.id.tvAllAppIcon);
        cvTrackOrder=itemView.findViewById(R.id.cvAllApp);



    }
}
