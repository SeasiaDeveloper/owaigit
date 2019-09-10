package com.oway.base;

import android.util.Log;

import com.oway.R;
import com.oway.datasource.implementation.ApiService;
import com.oway.model.Response;
import com.oway.utillis.ConstsCore;
import com.oway.utillis.ToastUtils;

import javax.inject.Inject;


public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private V mMvpView;
    protected ApiService apiService;

    @Inject
    public BasePresenter(ApiService apiService) {
        this.apiService = apiService;
        Log.e("responsee", "responsee");
    }

    @Override
    public void onAttach(V mvpView) {
        this.mMvpView = mvpView;
    }

    @Override
    public void onDetach() {
        mMvpView = null;
    }

    @Override
    public void showLoading() {
        if (getMvpView() != null)
            getMvpView().showLoading();
    }

    @Override
    public void dismissLoading() {
        if (getMvpView() != null)
            getMvpView().hideLoading();
    }

    @Override
    public boolean isBodyVerified(Response response) {
       /* if (AppConstants.isDashBoard) {
            HMSPreference.writeLong(App.getInstance(),
                    HMSPreference.PREF_TIME_OUT, System.currentTimeMillis());
        }*/
        if (response != null && response.getCode() == ConstsCore.STATUS_CODE_SUCCESS)
            return true;
        else if (response != null && response.getCode() == ConstsCore.STATUS_CODE_SESSION_EXPIRED) {
            //ToastUtils.shortToast(response.getMessage());
            if (getMvpView() != null) {
                getMvpView().logout();
            }
            return false;
        } else if (response != null && response.getCode() != ConstsCore.STATUS_CODE_SUCCESS) {
            String msg = getMsgfromCode(response.getCode(), response.getMessage());
            if (msg != null && !msg.isEmpty() && (!msg.equalsIgnoreCase("No fields have been changed") ||
                    !msg.equalsIgnoreCase("error")))
                ToastUtils.shortToast(response.getMessage());
            //getMvpView().onError(getMsgfromCode(response.getCode(), response.getMessage()));
        } else {
            if (getMvpView() != null)
                ToastUtils.shortToast(R.string.something_went_wrong);
            //getMvpView().onError(R.string.something_went_wrong);
        }
        return false;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public V getMvpView() {
        return mMvpView;
    }

    public String getMsgfromCode(long code, String message) {
        return message;
    }
}
