package com.oway.ui.login;

import com.oway.App;
import com.oway.R;
import com.oway.base.BasePresenter;
import com.oway.base.MvpView;
import com.oway.datasource.implementation.ApiService;
import com.oway.model.request.LoginRequest;
import com.oway.model.response.LoginResponse;
import com.oway.utillis.ConstsCore;
import com.oway.utillis.NetworkUtils;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivityPresenter<V extends MvpView> extends BasePresenter<LoginActivityView> {

    @Inject
    public LoginActivityPresenter(ApiService apiService) {
        super(apiService);
    }

    public void login(LoginRequest loginRequest) {

        if (!NetworkUtils.isNetworkConnected(getMvpView().getActivityContext())) {
            getMvpView().showMessage(R.string.internet_check);
            return;
        }
        showLoading();
        apiService.login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                dismissLoading();
                LoginResponse body = response.body();
                if (body != null) {
                    if (isBodyVerified(response.body().getCode()) && response.body().getCode() == ConstsCore.STATUS_CODE_SUCCESS) {
                        getMvpView().onSuccess(body);
                    } else if (response.body().getCode() == ConstsCore.STATUS_CODE_FAILED) {
                        getMvpView().onFailure(body.getRespMessage());
                    }
                } else {
                    getMvpView().onFailure(App.getInstance().getResources().getString(R.string.something_went_wrong));
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                dismissLoading();
                if (getMvpView() != null) {
                    String msg = t.getMessage();
                    getMvpView().showMessage(R.string.something_went_wrong);
                }
            }
        });
    }
}
