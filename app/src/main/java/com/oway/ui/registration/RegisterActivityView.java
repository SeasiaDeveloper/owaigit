package com.oway.ui.registration;


import com.oway.base.MvpView;
import com.oway.model.response.RegisterResponse;

public interface RegisterActivityView extends MvpView {
    void onSuccess(RegisterResponse status);

    void onFailure(String response);
}
