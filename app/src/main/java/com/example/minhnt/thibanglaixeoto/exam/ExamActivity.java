package com.example.minhnt.thibanglaixeoto.exam;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.minhnt.thibanglaixeoto.R;
import com.example.minhnt.thibanglaixeoto.learn.LearnFragment;
import com.example.minhnt.thibanglaixeoto.object.Question;
import com.example.minhnt.thibanglaixeoto.practice.PracticeAdapter;
import com.example.minhnt.thibanglaixeoto.util.Constants;
import com.example.minhnt.thibanglaixeoto.util.Util;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ExamActivity extends AppCompatActivity {
    private RecyclerView rvLearn;
    private TestAdapter examAdapter;
    private List<Question> questions = new ArrayList<>();
    private int countCorrect = 0;
    private LinearLayout llResult;
    private TextView tvChucMung, tvScore;
    private ImageView ivChucMung;
    private CheckBox cbShowWrong;
    private Button btnFinish;
    private List<Question> wrongList = new ArrayList<>();
    private TextView tvTimer;
    private CountDownTimer timer;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-5290873807821544/9314738117");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

        });
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
        examAdapter = new TestAdapter(questions);
        rvLearn.setAdapter(examAdapter);

        addControl();

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (btnFinish.getText().equals("Nộp bài")) {
                    if (mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    }

                    cbShowWrong.setChecked(false);
                    timer.cancel();

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

                    if (countCorrect == questions.size()) {
                        goToCongrat();
                        finish();
                    }

                    //notify tô màu câu sai
                    examAdapter.setQuestions(ExamActivity.this.questions);

                    //hiển thị màn hình chúc mừng
                    result(countCorrect);
                    btnFinish.setText("Làm lại");
                } else if (btnFinish.getText().equals("Làm lại")) {
                    timer.start();
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

        timer = new CountDownTimer(20 * 60 * 1000, 1000) {
            @Override
            public void onTick(long l) {
                tvTimer.setText(convertTime(l));
            }

            @Override
            public void onFinish() {
                Util.showMessage(getApplicationContext(), "Đã hết giờ làm bài", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        btnFinish.performClick();
                    }
                });
            }
        }.start();
    }

    private String convertTime(long l) {
        long second = l / 1000;
        return (second / 60) + " phút " + (second % 60) + " giây";
    }

    private void goToCongrat() {
        Intent intent = new Intent(ExamActivity.this, CongratActivity.class);
        startActivity(intent);
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

    private void addControl() {
        btnFinish = (Button) findViewById(R.id.btnFinish);
        llResult = (LinearLayout) findViewById(R.id.llResult);
        tvChucMung = (TextView) findViewById(R.id.tvChucMung);
        tvScore = (TextView) findViewById(R.id.tvScore);
        ivChucMung = (ImageView) findViewById(R.id.ivChucMung);
        cbShowWrong = (CheckBox) findViewById(R.id.cbShowWrong);
        tvTimer = (TextView) findViewById(R.id.tvTimer);
    }
}
