package com.oway.ui.registration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.oway.R;
import com.oway.ui.home.MainActivity;
import com.oway.ui.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Registration extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);


    }
    @OnClick(R.id.bt_reg)
    public void onVC(){
        Intent intent=new Intent(Registration.this, RegisterPayment.class);
        startActivity(intent);
    }



}
