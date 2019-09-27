package com.oway.base;

import android.util.Log;

import com.oway.datasource.implementation.ApiService;
import com.oway.model.Response;
import com.oway.utillis.ConstsCore;

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
        if (response != null && response.getCode() == ConstsCore.STATUS_CODE_SUCCESS)
            return true;
        return false;
    }

    @Override
    public boolean isBodyVerified(int response) {
        if (response == ConstsCore.STATUS_CODE_SUCCESS)
            return true;
        else if(response==ConstsCore.STATUS_CODE_SESSION_EXPIRED) {
            getMvpView().logout();
            return false;
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
