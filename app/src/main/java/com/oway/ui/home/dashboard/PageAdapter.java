package com.oway.ui.home.dashboard;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PageAdapter extends FragmentStatePagerAdapter {


    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        //Log.d("OPPO-1", "getItem: "+position);
        switch (position) {
            case 0:
                return new DashBoardFragment();
            case 1:
                return new DashBoardFragment();
            case 2:
                return new DashBoardFragment();
            case 3:
                return new DashBoardFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}