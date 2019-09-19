package com.oway.utillis;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.oway.R;
import com.yyydjk.library.BannerLayout;

public class GlideImageLoader implements BannerLayout.ImageLoader {

    @Override
    public void displayImage(Context context, String s, ImageView imageView) {
        /*Glide.with(context)
                .load(s)
                .into(imageView);*/
        //Glide.with(context).load(s).error(context.getResources().getDrawable(R.drawable.ic_image_broken)).centerCrop().into(imageView);
       /* if(context == null || context instanceof Activity && DialogUtils.isActivityFinishedOrDestroyed(((Activity)context))){
            return;
        }*/
        Glide.with(context).load(s).error(context.getResources().getDrawable(R.drawable.account)).placeholder(R.drawable.account).centerCrop().into(imageView);


    }
}
