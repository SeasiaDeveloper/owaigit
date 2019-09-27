package com.oway.ui.home.dashboard;

import com.oway.App;
import com.oway.R;
import com.oway.base.BasePresenter;
import com.oway.base.MvpView;
import com.oway.datasource.implementation.ApiService;
import com.oway.model.request.GetSaldoRequest;
import com.oway.model.response.GetSaldoResponse;
import com.oway.utillis.ConstsCore;
import com.oway.utillis.NetworkUtils;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashBoardFragmentPresenter<V extends MvpView> extends BasePresenter<DashBoardFragmentInterface> {

    @Inject
    public DashBoardFragmentPresenter(ApiService apiService) {
        super(apiService);
    }


    public void getSaldo(GetSaldoRequest saldoRequest) {

        if (!NetworkUtils.isNetworkConnected(getMvpView().getActivityContext())) {
            getMvpView().showMessage(R.string.internet_check);
            return;
        }
        showLoading();
        apiService.getSaldo(saldoRequest).enqueue(new Callback<GetSaldoResponse>() {
            @Override
            public void onResponse(Call<GetSaldoResponse> call, Response<GetSaldoResponse> response) {
                dismissLoading();
                GetSaldoResponse body = response.body();
                if (body != null) {
                    if (isBodyVerified(response.body().getCode()) && response.body().getCode() == ConstsCore.STATUS_CODE_SUCCESS) {
                        getMvpView().onGetSaldoResponseSuccess(body);
                    } else if (response.body().getCode() == ConstsCore.STATUS_CODE_FAILED) {
                        getMvpView().onGetsaldoResponseFailure(body.getRespMessage());
                    }
                } else {
                    getMvpView().onGetsaldoResponseFailure(App.getInstance().getResources().getString(R.string.something_went_wrong));
                }
            }


            @Override
            public void onFailure(Call<GetSaldoResponse> call, Throwable t) {
                dismissLoading();
                if (getMvpView() != null) {
                    String msg = t.getMessage();
                    getMvpView().showMessage(R.string.something_went_wrong);
                }
            }
        });
    }



}
