package com.oway.base;


import com.oway.model.Response;

public interface MvpPresenter <V extends MvpView>{

    void onAttach(V mvpView);

    void onDetach();

    void showLoading();

    void dismissLoading();

    boolean isBodyVerified(Response response);

}
