package com.oway.ui.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.oway.R;
import com.oway.adapters.TutorialViewPagerAdapter;

public class Tutorial extends AppCompatActivity {
    private ViewPager tutorialViewPager;
    private TabLayout tutorialDots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        tutorialViewPager = findViewById(R.id.photos_viewpager);
        TutorialViewPagerAdapter pagerAdapter = new TutorialViewPagerAdapter(this, getSupportFragmentManager());
        tutorialViewPager.setAdapter(pagerAdapter);
        tutorialDots = findViewById(R.id.tab_layout);
        tutorialDots.setupWithViewPager(tutorialViewPager);


    }
   /* public void next_fragment(View view) {
        tutorialViewPager.setCurrentItem(tutorialViewPager.getCurrentItem()+1);
    }*/
}
