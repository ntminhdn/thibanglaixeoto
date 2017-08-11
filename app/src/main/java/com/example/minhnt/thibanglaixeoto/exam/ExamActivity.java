package com.example.minhnt.thibanglaixeoto.exam;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.example.minhnt.thibanglaixeoto.R;
import com.example.minhnt.thibanglaixeoto.object.Question;
import com.example.minhnt.thibanglaixeoto.practice.PracticeAdapter;
import com.example.minhnt.thibanglaixeoto.util.Constants;
import com.example.minhnt.thibanglaixeoto.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ExamActivity extends AppCompatActivity {
    private RecyclerView rvLearn;
    private PracticeAdapter examAdapter;
    private List<Question> questions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        int lesson = getIntent().getIntExtra(Constants.LESSON_NUMBER, 0);
        if (lesson != 0) {
            questions = loadDataFromAsset(this, "de" + lesson + ".json");
        }
        rvLearn = (RecyclerView) findViewById(R.id.rvLearn);
        rvLearn.setLayoutManager(new LinearLayoutManager(this));
        rvLearn.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(10, 20, 10, 20);
            }
        });
        examAdapter = new PracticeAdapter(questions);
        rvLearn.setAdapter(examAdapter);
    }

    private List<Question> loadDataFromAsset(Context context, String path) {
        String jsonOutput = Util.loadFromAssets(context, path);
        Type listType = new TypeToken<List<Question>>() {
        }.getType();
        List<Question> list = new Gson().fromJson(jsonOutput, listType);
        if (list != null) {
            return list;
        }

        return null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return true;
    }
}
