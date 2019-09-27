package com.oway.ui.trip;

import com.oway.App;
import com.oway.R;
import com.oway.base.BasePresenter;
import com.oway.base.MvpView;
import com.oway.datasource.implementation.ApiService;
import com.oway.model.request.GetNearestDriverRequest;
import com.oway.model.request.GetRecommendedPlacesRequest;
import com.oway.model.response.GetNearestDriverResponse;
import com.oway.model.response.GetRecommendedPlacesResponse;
import com.oway.utillis.ConstsCore;
import com.oway.utillis.NetworkUtils;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TripActivityPresenter<V extends MvpView> extends BasePresenter<TripActivityView> {

    @Inject
    public TripActivityPresenter(ApiService apiService) {
        super(apiService);
    }

    public void getNearestDriver(GetNearestDriverRequest nearRequest) {

        if (!NetworkUtils.isNetworkConnected(getMvpView().getActivityContext())) {
            getMvpView().showMessage(R.string.internet_check);
            return;
        }
        showLoading();
        apiService.getNearestDriver(nearRequest).enqueue(new Callback<GetNearestDriverResponse>() {
            @Override
            public void onResponse(Call<GetNearestDriverResponse> call, Response<GetNearestDriverResponse> response) {
                dismissLoading();
                GetNearestDriverResponse body = response.body();
                if (body != null) {
                    if (isBodyVerified(response.body().getCode()) && response.body().getCode() == ConstsCore.STATUS_CODE_SUCCESS) {
                        getMvpView().onGetNearestDriverResponseSuccess(body);
                    } else if (response.body().getCode() == ConstsCore.STATUS_CODE_FAILED) {
                        getMvpView().onGetNearestDriverResponseFailure(body.getRespMessage());
                    }
                } else {
                    getMvpView().onGetNearestDriverResponseFailure(App.getInstance().getResources().getString(R.string.something_went_wrong));
                }
            }

            @Override
            public void onFailure(Call<GetNearestDriverResponse> call, Throwable t) {
                dismissLoading();
                if (getMvpView() != null) {
                    String msg = t.getMessage();
                    getMvpView().showMessage(R.string.something_went_wrong);
                }
            }
        });
    }

    public void getRecommendedPlaces(GetRecommendedPlacesRequest placeRequest) {

        if (!NetworkUtils.isNetworkConnected(getMvpView().getActivityContext())) {
            getMvpView().showMessage(R.string.internet_check);
            return;
        }
        //showLoading();
        apiService.getRecommendedPlaces(placeRequest).enqueue(new Callback<GetRecommendedPlacesResponse>() {
            @Override
            public void onResponse(Call<GetRecommendedPlacesResponse> call, Response<GetRecommendedPlacesResponse> response) {
                // dismissLoading();
                GetRecommendedPlacesResponse body = response.body();
                if (body != null) {
                    getMvpView().onGetRecommendedPlacesSuccess(response.body());
                   /* if (isBodyVerified(response.body().getCode()) && response.body().getCode() == ConstsCore.STATUS_CODE_SUCCESS) {
                        getMvpView().onGetNearestDriverResponseSuccess(body);
                    } else if (response.body().getCode() == ConstsCore.STATUS_CODE_FAILED) {
                        getMvpView().onGetNearestDriverResponseFailure(body.getRespMessage());
                    }*/
                } else {
                    getMvpView().onGetRecommendedPlacesFailure(App.getInstance().getResources().getString(R.string.something_went_wrong));
                }
            }

            @Override
            public void onFailure(Call<GetRecommendedPlacesResponse> call, Throwable t) {
                // dismissLoading();
                if (getMvpView() != null) {
                    String msg = t.getMessage();
                    getMvpView().showMessage(R.string.something_went_wrong);
                }
            }
        });
    }



}
