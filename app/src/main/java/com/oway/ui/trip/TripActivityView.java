package com.oway.ui.trip;


import com.oway.base.MvpView;
import com.oway.model.response.CancelRideResponse;
import com.oway.model.response.CustomerTransactionResponse;
import com.oway.model.response.GetEstimateBikeResponse;
import com.oway.model.response.GetNearestDriverResponse;
import com.oway.model.response.GetPriceBySeatResponse;
import com.oway.model.response.GetRecommendedPlacesResponse;
import com.oway.model.response.LocationDetailsResponse;
import com.oway.model.response.SendDriverResponse;

import retrofit2.Response;

public interface TripActivityView extends MvpView {
    void onGetNearestDriverResponseSuccess(GetNearestDriverResponse status);

    void onGetNearestDriverResponseFailure(String response);

    void onGetRecommendedPlacesSuccess(GetRecommendedPlacesResponse response);

    void onGetRecommendedPlacesFailure(String response);

    void onGetAddressSuccess(Response<LocationDetailsResponse> response);

    void onGetAddressFailure(String msg);

    void onGetBikePriceSuccess(GetEstimateBikeResponse response);

    void onGetBikePriceFailure(String response);

    void onGetCustomerTransactionSuccess(CustomerTransactionResponse response);

    void onGetCustomerTransactionFailure(String response);

    void onSendNearestDriverSuccess(SendDriverResponse response);

    void onSendNearestDriverFailure(String response);

    void onCancelRideSuccess(CancelRideResponse response);

    void onCancelRideFailure(String msg);

    void onGetPriceBySeatSuccess(GetPriceBySeatResponse response);

    void onGetPriceBySeatFailure(String response);

}
