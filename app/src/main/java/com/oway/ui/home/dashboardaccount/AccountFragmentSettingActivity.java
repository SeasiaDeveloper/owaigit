package com.oway.ui.home.dashboardaccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.oway.App;
import com.oway.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountFragmentSettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_fragment_setting);
        ButterKnife.bind(this);
    }
    public static void onStartSettings(Context context) {
        Intent intent = new Intent(App.getInstance(), AccountFragmentSettingActivity.class);
        context.startActivity(intent);
    }
    @OnClick(R.id.btn_back_settings)
    public void onClick(){
        finish();
    }
}
