package com.example.minhnt.thibanglaixeoto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.minhnt.thibanglaixeoto.exam.ChooseExamActivity;
import com.example.minhnt.thibanglaixeoto.learn.LearnActivity;
import com.example.minhnt.thibanglaixeoto.practice.PracticeActivity;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLearn, btnPractice, btnExam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Realm.init(this);

        addControl();
    }

    private void addControl() {
        btnLearn = (Button) findViewById(R.id.btnLearn);
        btnPractice = (Button) findViewById(R.id.btnPractice);
        btnExam = (Button) findViewById(R.id.btnExam);
        btnLearn.setOnClickListener(this);
        btnPractice.setOnClickListener(this);
        btnExam.setOnClickListener(this);
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
        }
    }
}
