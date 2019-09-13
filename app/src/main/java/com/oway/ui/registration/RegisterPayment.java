package com.oway.ui.registration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.oway.R;
import com.oway.adapters.RegistrationViewPagerAdapter;

public class RegisterPayment extends AppCompatActivity {
    private ViewPager registrationPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_payment);
        registrationPager = findViewById(R.id.register_viewpager);
        RegistrationViewPagerAdapter registerPagerAdapter = new RegistrationViewPagerAdapter(this, getSupportFragmentManager());
        registrationPager.setAdapter(registerPagerAdapter);

    }
}
