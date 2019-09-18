package com.oway.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;

import com.oway.R;
import com.oway.customviews.CustomTextView;
import com.oway.ui.login.LoginActivity;
import com.oway.ui.login.LoginVerification;
import com.oway.ui.registration.Registration;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginSignUpChoice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sign_up_choice);
        ButterKnife.bind(this);

    }
    @OnClick(R.id.tvxLogin)
    public void clicked(){
        Intent intent=new Intent(LoginSignUpChoice.this, LoginActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.tvxSignup)
    public void onClicked(){
        Intent intent=new Intent(LoginSignUpChoice.this, LoginVerification.class);
        startActivity(intent);
    }
}
