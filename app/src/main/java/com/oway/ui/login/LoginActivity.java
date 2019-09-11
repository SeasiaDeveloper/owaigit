package com.oway.ui.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginActivityView {

    @Inject
    ValidationUtils validationUtils;
    @Inject
    PreferencesHelper preferencesHelper;
    @Inject
    LoginActivityPresenter<LoginActivityView> loginActivityPresenter;

    @BindView(R.id.etxName)
    EditText etxName;

    @BindView(R.id.etxPassword)
    EditText etxPassword;

    @BindView(R.id.btnLogin)
    Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getActivityComponent().inject(this);
        //setUp();
        loginActivityPresenter.onAttach(LoginActivity.this);

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
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginActivityPresenter.onDetach();
    }
}
