package com.example.locker;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int countTabs;

    public PagerAdapter(FragmentManager fm, int countTabs) {
        super(fm);
        this.countTabs = countTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Dash1 dash1 = new Dash1();
                return dash1;
            case 1:
                Dash2 dash2 = new Dash2();
                return dash2;
            case 2:
                Dash3 dash3 = new Dash3();
                return dash3;
                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return countTabs;
    }
}
