package com.example.myapplication.src.Adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class AdapterViewPagerManactivity extends FragmentStatePagerAdapter {
    private ArrayList<Fragment>arrayListFragment = new ArrayList<>();
    private ArrayList<String>arrayTitle = new ArrayList<>();
    public AdapterViewPagerManactivity(FragmentManager fm) {
        super(fm);
    }

    // lay fragment theo thu tu ra
    @Override
    public Fragment getItem(int position) {
        return arrayListFragment.get(position);
    }

    @Override
    // tra ve so luong fragment co trong mang
    public int getCount() {
        return arrayListFragment.size();
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return arrayTitle.get(position);
    }

    public void addFragment(Fragment fragment, String title){
        arrayListFragment.add(fragment);
        arrayTitle.add(title);
    }
}
