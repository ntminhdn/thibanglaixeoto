package com.example.minhnt.thibanglaixeoto.learn;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.minhnt.thibanglaixeoto.object.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minh.nt on 8/9/2017.
 */

public class LearnViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<LearnFragment> fragments = new ArrayList<>();
    private List<List<Question>> lists;

    public LearnViewPagerAdapter(FragmentManager fm, List<List<Question>> lists) {
        super(fm);
        this.lists = lists;
        LearnFragment de1 = new LearnFragment();
        LearnFragment de2 = new LearnFragment();
        LearnFragment de3 = new LearnFragment();
        LearnFragment de4 = new LearnFragment();
        LearnFragment de5 = new LearnFragment();
        LearnFragment de6 = new LearnFragment();
        LearnFragment de7 = new LearnFragment();
        LearnFragment de8 = new LearnFragment();
        LearnFragment de9 = new LearnFragment();
        LearnFragment de10 = new LearnFragment();
        LearnFragment de11 = new LearnFragment();
        LearnFragment de12 = new LearnFragment();
        LearnFragment de13 = new LearnFragment();
        LearnFragment de14 = new LearnFragment();
        LearnFragment de15 = new LearnFragment();
        fragments.add(de1);
        fragments.add(de2);
        fragments.add(de3);
        fragments.add(de4);
        fragments.add(de5);
        fragments.add(de6);
        fragments.add(de7);
        fragments.add(de8);
        fragments.add(de9);
        fragments.add(de10);
        fragments.add(de11);
        fragments.add(de12);
        fragments.add(de13);
        fragments.add(de14);
        fragments.add(de15);
        de1.setQuestions(lists.get(0));
        de2.setQuestions(lists.get(1));
        de3.setQuestions(lists.get(2));
        de4.setQuestions(lists.get(3));
        de5.setQuestions(lists.get(4));
        de6.setQuestions(lists.get(5));
        de7.setQuestions(lists.get(6));
        de8.setQuestions(lists.get(7));
        de9.setQuestions(lists.get(8));
        de10.setQuestions(lists.get(9));
        de11.setQuestions(lists.get(10));
        de12.setQuestions(lists.get(11));
        de13.setQuestions(lists.get(12));
        de14.setQuestions(lists.get(13));
        de15.setQuestions(lists.get(14));
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return 15;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Đề " + (position + 1);
    }
}
