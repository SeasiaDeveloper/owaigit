package com.oway.ui.splash;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.oway.R;
import com.oway.adapters.TutorialViewPagerAdapter;
import com.oway.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Optional;

public class Tutorial extends BaseActivity {

    @BindView(R.id.photos_viewpager)
    ViewPager tutorialViewPager;
    @BindView(R.id.tab_layout)
    TabLayout tutorialDots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        ButterKnife.bind(this);
        TutorialViewPagerAdapter pagerAdapter = new TutorialViewPagerAdapter(this, getSupportFragmentManager());
        tutorialViewPager.setAdapter(pagerAdapter);
        tutorialDots.setupWithViewPager(tutorialViewPager);
    }

    @Override
    protected void setUp() {

    }
   /* public void next_fragment(View view) {
        tutorialViewPager.setCurrentItem(tutorialViewPager.getCurrentItem()+1);
    }*/
}
