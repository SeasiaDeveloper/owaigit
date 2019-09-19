package com.oway.ui.registration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.oway.R;
import com.oway.adapters.RegistrationViewPagerAdapter;
import com.oway.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterPayment extends BaseActivity {

    @BindView(R.id.register_viewpager)
    ViewPager registrationPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_payment);
        ButterKnife.bind(this);
        RegistrationViewPagerAdapter registerPagerAdapter = new RegistrationViewPagerAdapter(this, getSupportFragmentManager());
        registrationPager.setAdapter(registerPagerAdapter);

    }

    @Override
    protected void setUp() {

    }

    public void next_fragment(View view) {
        registrationPager.setCurrentItem(registrationPager.getCurrentItem() + 1, true);
    }
}
