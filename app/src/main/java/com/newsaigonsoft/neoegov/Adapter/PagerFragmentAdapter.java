package com.newsaigonsoft.neoegov.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by VinhCN on 4/25/2017.
 */

public class PagerFragmentAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> listFragment;
    public PagerFragmentAdapter(FragmentManager fm, ArrayList<Fragment> listFragment) {
        super(fm);
        this.listFragment = listFragment;
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }
}
