package com.oway.ui.trip;

import com.oway.base.MvpView;
import com.oway.model.response.GetRecommendedPlacesResponse;

public interface SearchPlacesView extends MvpView {
    void onGetSearchPlacesResponseSuccess(GetRecommendedPlacesResponse status);
    void onGetsearchPlacesResponseFailure(String response);
}
