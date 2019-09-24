package com.oway.ui.login;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.oway.R;
import com.oway.base.BaseActivity;
import com.oway.callbacks.RegisterButtonclick;
import com.oway.datasource.pref.PreferenceHandler;
import com.oway.model.request.LoginRequest;
import com.oway.model.response.LoginResponse;
import com.oway.ui.registration.RegisterPayment;
import com.oway.utillis.AppConstants;
import com.oway.utillis.CommonUtils;
import com.oway.utillis.ToastUtils;
import com.oway.utillis.ValidationUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginActivityView, RegisterButtonclick {

    @Inject
    ValidationUtils validationUtils;

    @Inject
    LoginActivityPresenter<LoginActivityView> loginActivityPresenter;

   /* @Inject
    PreferenceHandler preferencesHandler;*/

    @BindView(R.id.btn_login)
    Button btn;
    @BindView(R.id.tv_forgot_password)
    TextView tv_forgot_password;

    @BindView(R.id.tvRegister)
    TextView tvRegister;

    private boolean isVerified;
    @BindView(R.id.etxPassword)
    EditText etxPassword;
    @BindView(R.id.etxUserName)
    EditText etxUserName;
    private RegisterButtonclick mClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUp();
    }

    @OnClick(R.id.btn_login)
    public void onLoginClick() {
        isVerified = validationUtils.isLoginDataValid(etxUserName, etxPassword);
        if (isVerified) {
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setReg_id(CommonUtils.getFirebaseId());
            loginRequest.setPassword(etxPassword.getText().toString());
            loginRequest.setUsername(etxUserName.getText().toString());
            loginActivityPresenter.login(loginRequest);
        }
    }

    @OnClick(R.id.tvRegister)
    public void onRegisterClick() {
        RegisterPayment.start(this);
    }

    public static void start(BaseActivity baseActivity) {
        Intent intent = new Intent(baseActivity, LoginActivity.class);
        baseActivity.startActivity(intent);
        baseActivity.finish();
    }

    @Override
    protected void setUp() {
        ButterKnife.bind(this);
        tv_forgot_password.setPaintFlags(tv_forgot_password.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvRegister.setPaintFlags(tvRegister.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        getActivityComponent().inject(this);
        loginActivityPresenter.onAttach(LoginActivity.this);
        mClick = this;
    }

    @Override
    public void onSuccess(LoginResponse response) {
        ToastUtils.shortToast(response.getRespMessage());
        saveAllData(response);
    }

    private void saveAllData(LoginResponse response) {
        PreferenceHandler.writeString(this, AppConstants.USER_ID, response.getUserID());
        PreferenceHandler.writeString(this, AppConstants.MBR_TOKEN, response.getMbr_token());
        PreferenceHandler.writeString(this, AppConstants.IMAGE_PATH, response.getFoto());
        PreferenceHandler.writeString(this, AppConstants.USER_NAME, response.getNama());
        finish();
        WelcomeScreenActivity.start(this);
    }

    @Override
    public void onFailure(String response) {
        if (response.contains(getResources().getString(R.string.phone_string)))
            CommonUtils.showLoginDialog(this, response, mClick);
        else
            ToastUtils.shortToast(response);
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

    @Override
    public void onRegisterButtonClick() {
        tvRegister.performClick();
    }
}
