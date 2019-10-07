package com.oway.ui.home.dashboardaccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.content.Intent;

import com.oway.App;
import com.oway.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);
    }

    public static void onStartActivity(Context context) {
        Intent intent = new Intent(App.getInstance(), EditProfileActivity.class);
        context.startActivity(intent);
    }
    @OnClick(R.id.btn_back)
    public void onClick(){
        finish();
    }

}
