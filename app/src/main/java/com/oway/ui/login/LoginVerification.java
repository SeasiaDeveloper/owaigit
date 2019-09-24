package com.oway.ui.login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;
import com.facebook.accountkit.ui.SkinManager;
import com.facebook.accountkit.ui.UIManager;
import com.oway.R;
import com.oway.datasource.pref.PreferenceHandler;
import com.oway.ui.registration.Registration;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginVerification extends AppCompatActivity {
    private static final int APP_REQUEST_CODE = 101;
    @Inject
    PreferenceHandler preferenceHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_verification);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_fb)
    public void fbLogin() {
        login();
    }

    public void login() {
        final Intent intent = new Intent(this, AccountKitActivity.class);
        UIManager uiManager;
        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(
                        LoginType.PHONE,
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
                    AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
                        @Override
                        public void onSuccess(Account account) {

                            Toast.makeText(LoginVerification.this, account.getPhoneNumber().toString(), Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(LoginVerification.this, Registration.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onError(AccountKitError accountKitError) {

                        }
                    });

                    //getCurrentAccount();
                    toastMessage = "logged In";
                }
                if (!toastMessage.isEmpty())
                    Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Something went wrong. Please try again", Toast.LENGTH_LONG).show();
            }

        } else {
        }
    }


}
