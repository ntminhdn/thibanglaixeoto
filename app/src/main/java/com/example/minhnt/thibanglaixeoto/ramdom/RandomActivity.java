package com.example.minhnt.thibanglaixeoto.ramdom;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.minhnt.thibanglaixeoto.R;
import com.example.minhnt.thibanglaixeoto.object.Question;
import com.example.minhnt.thibanglaixeoto.util.ArrayRandom;
import com.example.minhnt.thibanglaixeoto.util.Constants;
import com.example.minhnt.thibanglaixeoto.util.Sound;
import com.example.minhnt.thibanglaixeoto.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RandomActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivPre, ivNext;
    private TextView tvNumber, tvTimer;
    private ViewPager vpRandom;
    private Button btnKiemtra;
    private RandomPagerAdapter randomPagerAdapter;
    private List<Question> questions = new ArrayList<>();
    private int position = 0;
    private CountDownTimer timer;
    private String[] paths = new String[]{
            Constants.ASSET_PATH_DE1,
            Constants.ASSET_PATH_DE2,
            Constants.ASSET_PATH_DE3,
            Constants.ASSET_PATH_DE4,
            Constants.ASSET_PATH_DE5,
            Constants.ASSET_PATH_DE6,
            Constants.ASSET_PATH_DE7,
            Constants.ASSET_PATH_DE8,
            Constants.ASSET_PATH_DE9,
            Constants.ASSET_PATH_DE10,
            Constants.ASSET_PATH_DE11,
            Constants.ASSET_PATH_DE12,
            Constants.ASSET_PATH_DE13,
            Constants.ASSET_PATH_DE14,
            Constants.ASSET_PATH_DE15,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);
        addControl();
        addAllQuestions();
        questions = ArrayRandom.get((ArrayList<Question>) questions);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        randomPagerAdapter = new RandomPagerAdapter(questions);
        vpRandom.setAdapter(randomPagerAdapter);
        vpRandom.beginFakeDrag();
        ivNext.setOnClickListener(this);
        ivPre.setOnClickListener(this);
        btnKiemtra.setOnClickListener(this);
        tvNumber.setText("Câu 1");
        hidden(position);
        timer = new CountDownTimer(40 * 1000, 1000) {
            @Override
            public void onTick(long l) {
                tvTimer.setText(String.valueOf(l / 1000) + " giây");
            }

            @Override
            public void onFinish() {
                if (!checkCorrect(questions.get(position))) {
                    showCorrect(position);
                    timer.cancel();
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.sai);
                    mediaPlayer.start();
                } else {
                    goNext();
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.dung);
                    mediaPlayer.start();
                }
            }
        }.start();
    }

    private void addAllQuestions() {
        for (int i = 0; i < paths.length; i++) {
            questions.addAll(loadDataFromAsset(this, paths[i]));
        }
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

    private void addControl() {
        ivPre = (ImageView) findViewById(R.id.ivPre);
        ivNext = (ImageView) findViewById(R.id.ivNext);
        tvNumber = (TextView) findViewById(R.id.tvNumberRandom);
        tvTimer = (TextView) findViewById(R.id.tvTimerRandom);
        vpRandom = (ViewPager) findViewById(R.id.vpRandom);
        btnKiemtra = (Button) findViewById(R.id.btnKiemtra);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivNext:
                goNext();
                break;
            case R.id.ivPre:
                goPre();
                break;
            case R.id.btnKiemtra:
                if (!checkCorrect(questions.get(position))) {
                    showCorrect(position);
                    timer.cancel();
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.sai);
                    mediaPlayer.start();
                } else {
                    goNext();
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.dung);
                    mediaPlayer.start();
                }
                break;
        }
    }

    private boolean checkCorrect(Question q) {
        if (q.myAnswer1 == q.correctAnswer1 && q.myAnswer2 == q.correctAnswer2 && q.myAnswer3 == q.correctAnswer3 && q.myAnswer4 == q.correctAnswer4) {
            return true;
        }

        return false;
    }

    private void showCorrect(int position) {
        questions.get(position).isShowCorrectAnswer = true;
        randomPagerAdapter.notifyDataSetChanged();
    }

    private void goPre() {
        timer.cancel();
        timer.start();
        position--;
        hidden(position);
        vpRandom.setCurrentItem(position, true);
        tvNumber.setText("Câu " + (position + 1));
    }

    private void goNext() {
        timer.cancel();
        timer.start();
        position++;
        hidden(position);
        vpRandom.setCurrentItem(position, true);
        tvNumber.setText("Câu " + (position + 1));
    }

    public void hidden(int pos) {
        if (pos == 0) {
            ivPre.setVisibility(View.INVISIBLE);
            ivPre.setEnabled(false);
        } else {
            ivPre.setVisibility(View.VISIBLE);
            ivPre.setEnabled(true);
        }

        if (pos == questions.size() - 1) {
            ivNext.setVisibility(View.INVISIBLE);
            ivNext.setEnabled(false);
        } else {
            ivNext.setVisibility(View.VISIBLE);
            ivNext.setEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return true;
    }
}
