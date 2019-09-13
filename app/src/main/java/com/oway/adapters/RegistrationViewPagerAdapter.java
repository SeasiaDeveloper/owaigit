package com.oway.adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.oway.ui.registration.RegistrationViewOne;
import com.oway.ui.registration.RegistrationViewThree;
import com.oway.ui.registration.RegistrationViewTwo;



public class RegistrationViewPagerAdapter extends FragmentPagerAdapter {
    Context context;
    RegistrationViewOne screenOne = new RegistrationViewOne();
    RegistrationViewTwo screenTwo = new RegistrationViewTwo();
    RegistrationViewThree screenThree = new RegistrationViewThree();


    public RegistrationViewPagerAdapter(Context contextOne, FragmentManager fm) {
        super(fm);
        context = contextOne;

    }


    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new RegistrationViewOne();
        } else if (position == 1) {
            return  new RegistrationViewTwo();
        } else if (position == 2) {
            return  new RegistrationViewThree();
        }
         else


            return null;
    }

    @Override
    public int getCount() {
        return 3;
    }


}
