package Holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shopping.clothshopping.R;

public class OfferTabHolder extends RecyclerView.ViewHolder {

    public ImageView ivOfferTabIcon;
    public TextView tvOfferTabIcon;
    public CardView cvOfferTab,cvCoupon;


    public TextView tvVerified,tvUsed,tvOff,tvOffOn,tvOffOnAgain,tvCoupon;

    public OfferTabHolder(@NonNull View itemView) {
        super(itemView);

        ivOfferTabIcon = itemView.findViewById(R.id.ivOfferTab);
        tvOfferTabIcon = itemView.findViewById(R.id.tvOfferTab);
        cvOfferTab=itemView.findViewById(R.id.cvOfferTab);


        tvVerified=itemView.findViewById(R.id.tvVerified);
        tvUsed=itemView.findViewById(R.id.tvUsed);
        tvOff=itemView.findViewById(R.id.tvOff);
        tvOffOn=itemView.findViewById(R.id.tvOffOn);
        tvOffOnAgain=itemView.findViewById(R.id.tvOffOnAgain);
        tvVerified=itemView.findViewById(R.id.tvVerified);
        tvCoupon=itemView.findViewById(R.id.tvCouponCode);
        cvCoupon=itemView.findViewById(R.id.cvCoupon);



    }
}
