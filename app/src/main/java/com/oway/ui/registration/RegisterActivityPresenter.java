package com.oway.ui.registration;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.oway.BuildConfig;
import com.oway.R;
import com.oway.base.BasePresenter;
import com.oway.base.MvpView;
import com.oway.datasource.implementation.ApiService;
import com.oway.model.request.RegisterRequest;
import com.oway.model.response.RegisterResponse;
import com.oway.utillis.AppConstants;
import com.oway.utillis.ConstsCore;
import com.oway.utillis.NetworkUtils;

import org.json.JSONObject;

import java.io.File;

import javax.inject.Inject;

public class RegisterActivityPresenter<V extends MvpView> extends BasePresenter<RegisterActivityView> {

    @Inject
    public RegisterActivityPresenter(ApiService apiService) {
        super(apiService);
    }

    public void register(RegisterRequest registerRequest) {

        if (!NetworkUtils.isNetworkConnected(getMvpView().getActivityContext())) {
            getMvpView().showMessage(R.string.internet_check);
            return;
        }
        showLoading();
        ANRequest.MultiPartBuilder upload = AndroidNetworking.upload(BuildConfig.BASE_URL + AppConstants.REGISTER);

        if(!registerRequest.getImage().isEmpty())
        upload.addMultipartFile("image", new File(registerRequest.getImage()));

                 upload.addMultipartParameter("nama", registerRequest.getNama())
                .addMultipartParameter("email", registerRequest.getEmail())
                .addMultipartParameter("password", registerRequest.getPassword())
                .addMultipartParameter("pin", registerRequest.getPin())
                .addMultipartParameter("uplineID", registerRequest.getUplineID())
                .addMultipartParameter("phone_number", registerRequest.getPhone_number())
                .addMultipartParameter("address", registerRequest.getAddress())
                .addMultipartParameter("city", registerRequest.getCity())
                .addMultipartParameter("gender", "L")
                .addMultipartParameter("province", registerRequest.getProvince())
                .setTag("uploading detail")
                .setPriority(Priority.HIGH)
                .build()
                .setUploadProgressListener(new UploadProgressListener() {
                    @Override
                    public void onProgress(long bytesUploaded, long totalBytes) {
                        // do anything with progress
                    }
                })
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        dismissLoading();
                        try {
                            Gson gson = new Gson();
                            RegisterResponse registerResponse = gson.fromJson(response.toString(), RegisterResponse.class);
                            if (registerResponse.getCode() == ConstsCore.STATUS_CODE_SUCCESS) {
                                getMvpView().onSuccess(registerResponse);
                            } else {
                                getMvpView().onFailure(registerResponse.getRespMessage());
                            }

                        } catch (JsonSyntaxException e) {
                            if (getMvpView() != null) {

                            }
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        dismissLoading();
                        if (getMvpView() != null) {
                        }
                    }
                });
    }

        /*apiService.register(registerRequest).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                dismissLoading();
                RegisterResponse body = response.body();
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
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                dismissLoading();
                if (getMvpView() != null) {
                    String msg = t.getMessage();
                    getMvpView().showMessage(R.string.something_went_wrong);
                }
            }
        });*/
}
