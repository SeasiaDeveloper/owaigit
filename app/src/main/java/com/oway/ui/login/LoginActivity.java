package com.oway.ui.login;

import android.os.Bundle;
import android.view.View;

import com.oway.R;
import com.oway.base.BaseActivity;
import com.oway.customviews.CustomEditText;
import com.oway.datasource.pref.PreferencesHelper;
import com.oway.model.request.LoginRequest;
import com.oway.model.response.LoginResponse;
import com.oway.utillis.ToastUtils;
import com.oway.utillis.ValidationUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginActivityView {

    @Inject
    ValidationUtils validationUtils;
    @Inject
    PreferencesHelper preferencesHelper;
    @Inject
    LoginActivityPresenter<LoginActivityView> loginActivityPresenter;
    @BindView(R.id.etxName)
    CustomEditText etxName;
    @BindView(R.id.etxPassword)
    CustomEditText etxPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void setUp() {

    }

    @Override
    public void onSuccess(LoginResponse status) {

    }

    @Override
    public void onFailure(String msg) {
        ToastUtils.shortToast(msg);

    }

    @OnClick(R.id.btnLogin)
    public void onLoginClick(View view) {
        hitLoginApi();
    }

    private void hitLoginApi() {
        boolean isValid = validationUtils.isLoginDataValid(etxName, etxPassword);
        if (isValid) {
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setCardnum(etxName.getText().toString());
            loginRequest.setPassword(etxPassword.getText().toString());
            loginRequest.setDevicetype("Android");
            loginRequest.setVersion("sdsd");
            loginActivityPresenter.login(loginRequest);
        }
    }
}
