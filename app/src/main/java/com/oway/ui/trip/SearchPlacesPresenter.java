package com.oway.ui.trip;

import com.oway.App;
import com.oway.R;
import com.oway.base.BasePresenter;
import com.oway.base.MvpView;
import com.oway.datasource.implementation.ApiService;
import com.oway.model.request.GetSearchPlacesRequest;
import com.oway.model.response.GetRecommendedPlacesResponse;
import com.oway.utillis.NetworkUtils;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPlacesPresenter<V extends MvpView> extends BasePresenter<SearchPlacesView> {

    @Inject
    public SearchPlacesPresenter(ApiService apiService) {
        super(apiService);
    }

    public void getSearchPlaces(GetSearchPlacesRequest placeRequest) {

        if (!NetworkUtils.isNetworkConnected(getMvpView().getActivityContext())) {
            getMvpView().showMessage(R.string.internet_check);
            return;
        }
        //showLoading();
        apiService.getSearchPlaces(placeRequest).enqueue(new Callback<GetRecommendedPlacesResponse>() {
            @Override
            public void onResponse(Call<GetRecommendedPlacesResponse> call, Response<GetRecommendedPlacesResponse> response) {
                // dismissLoading();
                GetRecommendedPlacesResponse body = response.body();
                if (isBodyVerified(body.getCode())&&body != null) {
                    getMvpView().onGetSearchPlacesResponseSuccess(response.body());
                } else {
                    getMvpView().onGetsearchPlacesResponseFailure(App.getInstance().getResources().getString(R.string.something_went_wrong));
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
