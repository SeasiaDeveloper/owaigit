package com.oway.ui.login;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.oway.R;
import com.oway.base.BaseActivity;
import com.oway.datasource.pref.PreferencesHelper;
import com.oway.model.response.LoginResponse;
import com.oway.ui.home.MainActivity;
import com.oway.utillis.ToastUtils;
import com.oway.utillis.ValidationUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginActivityView {

    private static final int APP_REQUEST_CODE = 101;
    @Inject
    ValidationUtils validationUtils;
    @Inject
    PreferencesHelper preferencesHelper;
    @Inject
    LoginActivityPresenter<LoginActivityView> loginActivityPresenter;

    @BindView(R.id.btn_login)
    Button btn;
    @BindView(R.id.tv_forgot_password)
    TextView etxForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        getActivityComponent().inject(this);
        setUp();
        etxForgotPassword.setPaintFlags(etxForgotPassword.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);


        loginActivityPresenter.onAttach(LoginActivity.this);
       /* MainActivity.start(this);
*/
    }
    @OnClick(R.id.btn_login)
    public void onVC(){
        Intent intent=new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void setUp() {
        Log.e("detailss","detalss");

    }

    @Override
    public void onSuccess(LoginResponse status) {

    }

    @Override
    public void onFailure(String msg) {
        ToastUtils.shortToast(msg);

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
