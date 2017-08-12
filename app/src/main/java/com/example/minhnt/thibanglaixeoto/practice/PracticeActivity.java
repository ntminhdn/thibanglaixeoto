package com.example.minhnt.thibanglaixeoto.practice;

import android.graphics.Rect;
import android.os.Bundle;
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
import com.example.minhnt.thibanglaixeoto.object.Question;
import com.example.minhnt.thibanglaixeoto.object.QuestionDao;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class PracticeActivity extends AppCompatActivity {

    private RecyclerView rvLearn;
    private PracticeAdapter examAdapter;
    private List<Question> questions = new ArrayList<>();
    private int countCorrect = 0;
    private LinearLayout llResult;
    private TextView tvChucMung, tvScore;
    private ImageView ivChucMung;
    private CheckBox cbShowWrong;
    private Button btnFinish;
    private List<Question> wrongList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addControl();
        RealmResults<QuestionDao> questionDaoRealmResults = Realm.getDefaultInstance().where(QuestionDao.class).findAll();
        for (int i = 0; i < questionDaoRealmResults.size(); i++) {
            questions.add(questionDaoRealmResults.get(i).convert());
        }
        if (questions.size() == 0) {
            findViewById(R.id.tvNothing).setVisibility(View.VISIBLE);
            btnFinish.setVisibility(View.GONE);
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

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnFinish.getText().equals("Nộp bài")) {
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
                    examAdapter.setQuestions(PracticeActivity.this.questions);

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

    private void addControl() {
        btnFinish = (Button) findViewById(R.id.btnFinish);
        llResult = (LinearLayout) findViewById(R.id.llResult);
        tvChucMung = (TextView) findViewById(R.id.tvChucMung);
        tvScore = (TextView) findViewById(R.id.tvScore);
        ivChucMung = (ImageView) findViewById(R.id.ivChucMung);
        cbShowWrong = (CheckBox) findViewById(R.id.cbShowWrong);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return true;
    }
}
