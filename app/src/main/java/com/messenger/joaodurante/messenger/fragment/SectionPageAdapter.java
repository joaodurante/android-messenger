package com.messenger.joaodurante.messenger.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SectionPageAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public SectionPageAdapter(FragmentManager fm){
        super(fm);
    }

    public void addFragment(Fragment fragment, String fragmentTitle){
        mFragmentList.add(fragment);
        mFragmentTitleList.add(fragmentTitle);
    }

    //return instances of the fragments created
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    //return the number of fragments
    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position){
        return mFragmentTitleList.get(position);
    }
}
