package com.oway.ui.login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.SkinManager;
import com.facebook.accountkit.ui.UIManager;
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

    private static final int APP_REQUEST_CODE =101 ;
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
        login();
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

    @Override
    public void onActivityResult(final int requestCode, final int resultCode,
                                 final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == APP_REQUEST_CODE) { // confirm that this response matches your request
            if (data != null) {
                AccountKitLoginResult loginResult = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
                String toastMessage = "";
                if (loginResult.getError() != null) {
                    toastMessage = loginResult.getError().getErrorType().getMessage();
                   // errorDialog(loginResult.getError().toString());
                    //Log.d(TAG, "Error " + loginResult.getError().toString());
                } else if (loginResult.wasCancelled()) {
                    toastMessage = "Login Cancelled";
                } else {
                    // Success! Start your next activity...
                    //getCurrentAccount();
                    toastMessage = "login";
                }
                if (!toastMessage.isEmpty())
                    Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Something went wrong. Please try again", Toast.LENGTH_LONG).show();
            }

        } else {
           // login.setVisibility(View.VISIBLE);
        }
    }
    public void login()
    {
        final Intent intent = new Intent(this, AccountKitActivity.class);
        UIManager uiManager;
        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(
                        com.facebook.accountkit.ui.LoginType.PHONE,
                        AccountKitActivity.ResponseType.TOKEN); // or .ResponseType.TOKEN

        //di param UI Manager di hilangkan parameter com.facebook.accountkit.ui.LoginType.PHONE
        uiManager = new SkinManager(SkinManager.Skin.TRANSLUCENT,
                (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? getResources().getColor(R.color.colorPrimaryDark, null) : getResources().getColor(R.color.colorPrimaryDark)),
                R.drawable.ic_background,
                SkinManager.Tint.WHITE,
                0.55
        );
        configurationBuilder.setDefaultCountryCode("ID");
        configurationBuilder.setUIManager(uiManager);
        intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION, configurationBuilder.build());
        startActivityForResult(intent, APP_REQUEST_CODE);
    }
}
