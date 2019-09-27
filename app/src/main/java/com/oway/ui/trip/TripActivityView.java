package com.oway.ui.trip;


import com.oway.base.MvpView;
import com.oway.model.response.GetNearestDriverResponse;
import com.oway.model.response.GetRecommendedPlacesResponse;
import com.oway.model.response.LoginResponse;

public interface TripActivityView extends MvpView {
    void onGetNearestDriverResponseSuccess(GetNearestDriverResponse status);
    void onGetNearestDriverResponseFailure(String response);
    void onGetRecommendedPlacesSuccess(GetRecommendedPlacesResponse response);
    void onGetRecommendedPlacesFailure(String response);

}
