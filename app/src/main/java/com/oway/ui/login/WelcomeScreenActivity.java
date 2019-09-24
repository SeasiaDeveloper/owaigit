package com.oway.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.oway.R;
import com.oway.base.BaseActivity;
import com.oway.datasource.pref.PreferenceHandler;
import com.oway.ui.home.MainActivity;
import com.oway.utillis.AppConstants;
import com.oway.utillis.ValidationUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeScreenActivity extends BaseActivity {

    @Inject
    ValidationUtils validationUtils;

    @Inject
    LoginActivityPresenter<LoginActivityView> loginActivityPresenter;
    @BindView(R.id.btn_login)
    Button btn;

    @BindView(R.id.tvUserName)
    TextView tvUserName;

    @BindView(R.id.imgUser)
    ImageView imgUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        setUp();
    }

    public static void start(BaseActivity baseActivity) {
        Intent intent = new Intent(baseActivity, WelcomeScreenActivity.class);
        baseActivity.startActivity(intent);
        baseActivity.finish();
    }

    @Override
    protected void setUp() {
        ButterKnife.bind(this);
        getActivityComponent().inject(this);
        tvUserName.setText("Hello " +PreferenceHandler.readString(this, AppConstants.USER_NAME, ""));
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.avatar)
                .error(R.drawable.avatar);
        Glide.with(this).load(PreferenceHandler.readString(this, AppConstants.IMAGE_PATH, "")).apply(options).into(imgUser);
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

    @OnClick(R.id.btn_login)
    public void onNextClick() {
        MainActivity.start(this);
        finish();
    }
}
