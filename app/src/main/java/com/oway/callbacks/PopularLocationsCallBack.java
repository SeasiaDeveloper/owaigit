package com.oway.callbacks;

import android.view.View;

public interface PopularLocationsCallBack {
    public void onItemClick(View v, String address,String lat,String lng);
}
