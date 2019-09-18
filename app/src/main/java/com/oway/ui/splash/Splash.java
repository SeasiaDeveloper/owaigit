package com.oway.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.oway.R;
import com.oway.datasource.pref.PreferenceHandler;
import com.oway.datasource.pref.PreferencesHelper;
import com.oway.ui.LoginSignUpChoice;
import com.oway.utillis.AppConstants;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class Splash extends AppCompatActivity {
    private static int SPLASH_SCREEN_TIME_OUT = 3000;
    @Inject
    PreferenceHandler preferencesHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //This method is used so that your splash activity
        //can cover the entire screen.

        setContentView(R.layout.activity_splash);

        //this will bind your MainActivity.class file with activity_main.

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (preferencesHelper.readBoolean(Splash.this,AppConstants.TOTURIAL_STATUS, false)) {
                    Intent i = new Intent(Splash.this,
                            LoginSignUpChoice.class);
                    startActivity(i);
                    finish();

               } else {
                    Intent i = new Intent(Splash.this,
                            Tutorial.class);
                    startActivity(i);
                    finish();
               }

            }
        }, SPLASH_SCREEN_TIME_OUT);

    }
}
