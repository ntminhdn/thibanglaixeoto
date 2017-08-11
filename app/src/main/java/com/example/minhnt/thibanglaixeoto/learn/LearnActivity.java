package com.example.minhnt.thibanglaixeoto.learn;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.minhnt.thibanglaixeoto.R;
import com.example.minhnt.thibanglaixeoto.object.Question;
import com.example.minhnt.thibanglaixeoto.util.Constants;
import com.example.minhnt.thibanglaixeoto.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by minh.nt on 8/9/2017.
 */

public class LearnActivity extends AppCompatActivity {
    private TabLayout tabLearn;
    private ViewPager vpLearn;
    private List<List<Question>> lists = new ArrayList<>();
    private LearnViewPagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        lists.add(loadDataFromAsset(this, Constants.ASSET_PATH_DE1));
        lists.add(loadDataFromAsset(this, Constants.ASSET_PATH_DE2));
        lists.add(loadDataFromAsset(this, Constants.ASSET_PATH_DE3));
        lists.add(loadDataFromAsset(this, Constants.ASSET_PATH_DE4));
        lists.add(loadDataFromAsset(this, Constants.ASSET_PATH_DE5));
        lists.add(loadDataFromAsset(this, Constants.ASSET_PATH_DE1));
        lists.add(loadDataFromAsset(this, Constants.ASSET_PATH_DE2));
        lists.add(loadDataFromAsset(this, Constants.ASSET_PATH_DE3));
        lists.add(loadDataFromAsset(this, Constants.ASSET_PATH_DE4));
        lists.add(loadDataFromAsset(this, Constants.ASSET_PATH_DE5));
        lists.add(loadDataFromAsset(this, Constants.ASSET_PATH_DE1));
        lists.add(loadDataFromAsset(this, Constants.ASSET_PATH_DE2));
        lists.add(loadDataFromAsset(this, Constants.ASSET_PATH_DE3));
        lists.add(loadDataFromAsset(this, Constants.ASSET_PATH_DE4));
        lists.add(loadDataFromAsset(this, Constants.ASSET_PATH_DE5));

        vpLearn = (ViewPager) findViewById(R.id.vpLearn);
        tabLearn = (TabLayout) findViewById(R.id.tabLearn);
        adapter = new LearnViewPagerAdapter(getSupportFragmentManager(), lists);
        vpLearn.setAdapter(adapter);
        tabLearn.setupWithViewPager(vpLearn);
    }

    private List<Question> loadDataFromAsset(Context context, String path) {
        String jsonOutput = Util.loadFromAssets(context, path);
        Type listType = new TypeToken<List<Question>>() {
        }.getType();
        List<Question> list = new Gson().fromJson(jsonOutput, listType);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Log.d("hihi", "Câu " + (i + 1) + "");
                Log.d("hihi", list.get(i).correctAnswer1 + "");
                Log.d("hihi", list.get(i).correctAnswer2 + "");
                Log.d("hihi", list.get(i).correctAnswer3 + "");
                Log.d("hihi", list.get(i).correctAnswer4 + "");
            }
            return list;
        }

        return null;
    }
}
