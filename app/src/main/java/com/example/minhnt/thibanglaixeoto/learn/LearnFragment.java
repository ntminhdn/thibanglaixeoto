package com.example.minhnt.thibanglaixeoto.learn;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.minhnt.thibanglaixeoto.R;
import com.example.minhnt.thibanglaixeoto.object.Question;

import java.util.List;

/**
 * Created by minh.nt on 8/9/2017.
 */

public class LearnFragment extends Fragment {
    private RecyclerView rvLearn;
    private ExamAdapter examAdapter;
    private List<Question> questions;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_learn, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvLearn = view.findViewById(R.id.rvLearn);
        rvLearn.setLayoutManager(new LinearLayoutManager(getContext()));
        rvLearn.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(10, 20, 10, 20);
            }
        });
        examAdapter = new ExamAdapter(questions);
        rvLearn.setAdapter(examAdapter);
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
