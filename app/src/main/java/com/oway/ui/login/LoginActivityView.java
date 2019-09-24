package com.oway.ui.login;


import com.oway.base.MvpView;
import com.oway.model.response.LoginResponse;

public interface LoginActivityView extends MvpView {
    void onSuccess(LoginResponse status);
    void onFailure(String response);
}
