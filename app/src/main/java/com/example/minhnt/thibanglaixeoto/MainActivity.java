package com.example.minhnt.thibanglaixeoto;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.minhnt.thibanglaixeoto.exam.ChooseExamActivity;
import com.example.minhnt.thibanglaixeoto.learn.LearnActivity;
import com.example.minhnt.thibanglaixeoto.practice.PracticeActivity;
import com.example.minhnt.thibanglaixeoto.ramdom.RandomActivity;
import com.example.minhnt.thibanglaixeoto.util.Util;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLearn, btnPractice, btnExam, btnRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Realm.init(this);
        if (!getSharedPreferences("oto", MODE_PRIVATE).getBoolean("never", false)) {
            Util.showMessage(this, "Chú ý: Bạn cần kết nối Internet để tải các câu hỏi có hình ảnh", null, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    SharedPreferences preferences = getSharedPreferences("oto", MODE_PRIVATE);
                    SharedPreferences.Editor edit = preferences.edit();
                    edit.putBoolean("never", true);
                    edit.commit();
                }
            });
        }

        addControl();
    }

    private void addControl() {
        btnLearn = (Button) findViewById(R.id.btnLearn);
        btnPractice = (Button) findViewById(R.id.btnPractice);
        btnExam = (Button) findViewById(R.id.btnExam);
        btnRandom = (Button) findViewById(R.id.btnRandom);
        btnLearn.setOnClickListener(this);
        btnPractice.setOnClickListener(this);
        btnExam.setOnClickListener(this);
        btnRandom.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLearn:
                Intent intent = new Intent(MainActivity.this, LearnActivity.class);
                startActivity(intent);
                break;
            case R.id.btnPractice:
                Intent intent2 = new Intent(MainActivity.this, PracticeActivity.class);
                startActivity(intent2);
                break;
            case R.id.btnExam:
                Intent intent1 = new Intent(MainActivity.this, ChooseExamActivity.class);
                startActivity(intent1);
                break;
            case R.id.btnRandom:
                Intent intent3 = new Intent(MainActivity.this, RandomActivity.class);
                startActivity(intent3);
                break;
        }
    }
}
