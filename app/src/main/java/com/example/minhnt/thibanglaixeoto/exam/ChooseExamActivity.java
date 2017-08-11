package com.example.minhnt.thibanglaixeoto.exam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.minhnt.thibanglaixeoto.R;
import com.example.minhnt.thibanglaixeoto.util.Constants;

public class ChooseExamActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12, btn13, btn14, btn15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_exam);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addControl();
    }

    private void addControl() {
        btn1 = (Button) findViewById(R.id.btnDe1);
        btn2 = (Button) findViewById(R.id.btnDe2);
        btn3 = (Button) findViewById(R.id.btnDe3);
        btn4 = (Button) findViewById(R.id.btnDe4);
        btn5 = (Button) findViewById(R.id.btnDe5);
        btn6 = (Button) findViewById(R.id.btnDe6);
        btn7 = (Button) findViewById(R.id.btnDe7);
        btn8 = (Button) findViewById(R.id.btnDe8);
        btn9 = (Button) findViewById(R.id.btnDe9);
        btn10 = (Button) findViewById(R.id.btnDe10);
        btn11 = (Button) findViewById(R.id.btnDe11);
        btn12 = (Button) findViewById(R.id.btnDe12);
        btn13 = (Button) findViewById(R.id.btnDe13);
        btn14 = (Button) findViewById(R.id.btnDe14);
        btn15 = (Button) findViewById(R.id.btnDe15);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn10.setOnClickListener(this);
        btn11.setOnClickListener(this);
        btn12.setOnClickListener(this);
        btn13.setOnClickListener(this);
        btn14.setOnClickListener(this);
        btn15.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnDe1:
                goToExam(3);
                break;
            case R.id.btnDe2:
                goToExam(1);
                break;
            case R.id.btnDe3:
                goToExam(8);
                break;
            case R.id.btnDe4:
                goToExam(2);
                break;
            case R.id.btnDe5:
                goToExam(6);
                break;
            case R.id.btnDe6:
                goToExam(9);
                break;
            case R.id.btnDe7:
                goToExam(5);
                break;
            case R.id.btnDe8:
                goToExam(4);
                break;
            case R.id.btnDe9:
                goToExam(15);
                break;
            case R.id.btnDe10:
                goToExam(14);
                break;
            case R.id.btnDe11:
                goToExam(13);
                break;
            case R.id.btnDe12:
                goToExam(10);
                break;
            case R.id.btnDe13:
                goToExam(11);
                break;
            case R.id.btnDe14:
                goToExam(12);
                break;
            case R.id.btnDe15:
                goToExam(7);
                break;
        }
    }

    private void goToExam(int lesson) {
        Intent intent = new Intent(ChooseExamActivity.this, ExamActivity.class);
        intent.putExtra(Constants.LESSON_NUMBER, lesson);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return true;
    }
}
