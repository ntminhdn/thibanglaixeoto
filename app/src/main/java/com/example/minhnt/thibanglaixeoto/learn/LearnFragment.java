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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.minhnt.thibanglaixeoto.R;
import com.example.minhnt.thibanglaixeoto.object.Question;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minh.nt on 8/9/2017.
 */

public class LearnFragment extends Fragment {
    private RecyclerView rvLearn;
    private ExamAdapter examAdapter;
    private List<Question> questions;
    private int countCorrect = 0;
    private LinearLayout llResult;
    private TextView tvChucMung, tvScore;
    private ImageView ivChucMung;
    private CheckBox cbShowWrong;
    private Button btnFinish;
    private List<Question> wrongList = new ArrayList<>();
    private InterstitialAd mInterstitialAd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_learn, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mInterstitialAd = new InterstitialAd(getContext());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });
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

        addControl(view);

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnFinish.getText().equals("Nộp bài")) {
                    if (mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    }
                    cbShowWrong.setChecked(false);

                    //xác định đúng sai
                    List<Question> questions = examAdapter.getQuestions();
                    for (int i = 0; i < questions.size(); i++) {
                        questions.get(i).isShowCorrectAnswer = true;

                        if (isCorrect(questions.get(i))) {
                            countCorrect++;
                        } else {
                            wrongList.add(questions.get(i));
                        }
                    }

                    //notify tô màu câu sai
                    examAdapter.setQuestions(LearnFragment.this.questions);

                    //hiển thị màn hình chúc mừng
                    result(countCorrect);
                    btnFinish.setText("Làm lại");
                } else if (btnFinish.getText().equals("Làm lại")) {
                    countCorrect = 0;
                    llResult.setVisibility(View.GONE);
                    btnFinish.setText("Nộp bài");
                    examAdapter.setQuestions(questions);
                    examAdapter.clear();
                    wrongList.clear();
                }
            }
        });

        cbShowWrong.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    examAdapter.setQuestions(wrongList);
                } else {
                    examAdapter.setQuestions(questions);
                }
            }
        });
    }

    private void result(int count) {
        llResult.setVisibility(View.VISIBLE);
        tvScore.setText("Điểm của bạn: " + count + " / " + questions.size());
        if (count >= 26) {
            tvChucMung.setText("Xin chúc mừng bạn đã vượt qua kỳ sát hạch");
            ivChucMung.setImageResource(R.drawable.smile);
        } else {
            tvChucMung.setText("Bạn chưa vượt qua kỳ sát hạch");
            ivChucMung.setImageResource(R.drawable.sad);
        }
    }

    private boolean isCorrect(Question question) {
        if (question.correctAnswer1 == question.myAnswer1 && question.correctAnswer2 == question.myAnswer2 && question.correctAnswer3 == question.myAnswer3 && question.correctAnswer4 == question.myAnswer4) {
            return true;
        }

        return false;
    }

    private void addControl(View view) {
        btnFinish = view.findViewById(R.id.btnFinish);
        llResult = view.findViewById(R.id.llResult);
        tvChucMung = view.findViewById(R.id.tvChucMung);
        tvScore = view.findViewById(R.id.tvScore);
        ivChucMung = view.findViewById(R.id.ivChucMung);
        cbShowWrong = view.findViewById(R.id.cbShowWrong);
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
