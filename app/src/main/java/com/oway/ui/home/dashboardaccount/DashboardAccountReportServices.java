package com.oway.ui.home.dashboardaccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.oway.App;
import com.oway.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DashboardAccountReportServices extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_account_report_services);
        ButterKnife.bind(this);
    }
    public static void onStartReportServices(Context context) {
        Intent intent = new Intent(App.getInstance(), DashboardAccountReportServices.class);
        context.startActivity(intent);
    }
    @OnClick(R.id.btn_back_rep)
    public void onClick(){
        finish();
    }
}
