package com.oway.ui.home.dashboardaccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.oway.App;
import com.oway.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountFragmentEmergencyCall extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_fragment_emergency_call);
        ButterKnife.bind(this);
    }
    public static void onStartCall(Context context) {
        Intent intent = new Intent(App.getInstance(), AccountFragmentEmergencyCall.class);
        context.startActivity(intent);
    }
    @OnClick(R.id.btn_back_call)
    public void onClick(){
        finish();
    }
}
