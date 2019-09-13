package com.oway.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import android.content.Context;

import com.oway.ui.splash.TutorialFragmentFour;
import com.oway.ui.splash.TutorialFragmentThree;
import com.oway.ui.splash.TutorialFragmentTwo;
import com.oway.ui.splash.TutorialFregmentOne;

public class TutorialViewPagerAdapter extends FragmentPagerAdapter {
    Context context;
    TutorialFregmentOne screenOne = new TutorialFregmentOne();
    TutorialFragmentTwo screenTwo = new TutorialFragmentTwo();
    TutorialFragmentThree screenThree = new TutorialFragmentThree();
    TutorialFragmentFour screenFour = new TutorialFragmentFour();


    public TutorialViewPagerAdapter(Context contextOne, FragmentManager fm) {
        super(fm);
        context = contextOne;

    }


    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new TutorialFregmentOne();
        } else if (position == 1) {
            return  new TutorialFragmentTwo();
        } else if (position == 2) {
            return  new TutorialFragmentThree();
        } else if (position == 3) {
            return new TutorialFragmentFour();
        } else


            return null;
    }

    @Override
    public int getCount() {
        return 4;
    }


}
