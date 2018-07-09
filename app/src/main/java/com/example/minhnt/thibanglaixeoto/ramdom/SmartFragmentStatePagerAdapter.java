package com.example.minhnt.thibanglaixeoto.ramdom;

import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

public abstract class SmartFragmentStatePagerAdapter extends PagerAdapter {

    private SparseArray<Object> registeredFragments = new SparseArray<>();

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object fragment = super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object o) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, o);
    }

    public Object getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }
}
