package com.oway.ui;

import android.content.Intent;
import android.os.Bundle;

import com.oway.R;
import com.oway.base.BaseActivity;
import com.oway.ui.login.LoginActivity;
import com.oway.ui.registration.Registration;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginSignUpChoice extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sign_up_choice);
        ButterKnife.bind(this);

    }

    @Override
    protected void setUp() {

    }

    @OnClick(R.id.tvxLogin)
    public void clicked() {
        Intent intent = new Intent(LoginSignUpChoice.this, LoginActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.tvxSignup)
    public void onClicked() {
        Registration.start(LoginSignUpChoice.this);
    }
}
