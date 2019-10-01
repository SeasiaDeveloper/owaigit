package com.oway.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.oway.App;
import com.oway.R;
import com.oway.base.BaseActivity;
import com.oway.datasource.pref.PreferenceHandler;
import com.oway.otto.BusProvider;
import com.oway.otto.OnApplyPushNotificationEvent;
import com.oway.ui.home.MainActivity;
import com.oway.utillis.AppConstants;
import com.oway.utillis.CommonUtils;
import com.oway.utillis.ConstsCore;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class Splash extends BaseActivity {
    private static int SPLASH_SCREEN_TIME_OUT = 3000;
    @Inject
    PreferenceHandler preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        CommonUtils.getFirebaseId();

        BusProvider.getInstance().register(this);
        BusProvider.getInstance().post(new OnApplyPushNotificationEvent("df", "jj"));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (PreferenceHandler.readString(Splash.this, AppConstants.USER_ID, "").isEmpty()) {
                    // MainActivity.start(Splash.this);
                    Intent i = new Intent(Splash.this,
                            Tutorial.class);
                    startActivity(i);
                    finish();

                } else {
                    MainActivity.start(Splash.this);
                    finish();
                }
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }

    @Override
    protected void setUp() {

    }
}
