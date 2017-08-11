package com.example.minhnt.thibanglaixeoto.learn;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.minhnt.thibanglaixeoto.R;
import com.example.minhnt.thibanglaixeoto.object.Question;

import java.util.List;


/**
 * Created by minh.nt on 8/9/2017.
 */

public class ExamAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Question> questions;

    public ExamAdapter(List<Question> ques) {
        this.questions = ques;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View photoSingle = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_learn, parent, false);
        return new MyViewHolder(photoSingle);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder) holder).setData(questions.get(position));
    }

    @Override
    public int getItemCount() {
        return questions == null ? 0 : questions.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener {
        private TextView tvNumber, tvQuestionContent;
        private ImageView ivQuestion;
        private CheckBox cbAnswer1, cbAnswer2, cbAnswer3, cbAnswer4;
        private Question question;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            tvQuestionContent = itemView.findViewById(R.id.tvQuestionContent);
            ivQuestion = itemView.findViewById(R.id.ivQuestion);
            cbAnswer1 = itemView.findViewById(R.id.cbAnswer1);
            cbAnswer2 = itemView.findViewById(R.id.cbAnswer2);
            cbAnswer3 = itemView.findViewById(R.id.cbAnswer3);
            cbAnswer4 = itemView.findViewById(R.id.cbAnswer4);
            cbAnswer1.setOnCheckedChangeListener(this);
            cbAnswer2.setOnCheckedChangeListener(this);
            cbAnswer3.setOnCheckedChangeListener(this);
            cbAnswer4.setOnCheckedChangeListener(this);
        }

        public void setData(Question data) {
            Log.d("hihi", "Câu " + (getLayoutPosition() + 1) + "");
            Log.d("hihi", data.correctAnswer1 + "");
            Log.d("hihi", data.correctAnswer2 + "");
            Log.d("hihi", data.correctAnswer3 + "");
            Log.d("hihi", data.correctAnswer4 + "");
            question = data;
            tvNumber.setText((getLayoutPosition() + 1) + " / " + getItemCount());
            tvQuestionContent.setText(removePrefix(data.questionContent));
            cbAnswer1.setText(data.answerContent1);
            cbAnswer2.setText(data.answerContent2);
            cbAnswer3.setText(data.answerContent3);
            cbAnswer4.setText(data.answerContent4);
            cbAnswer1.setChecked(data.myAnswer1);
            cbAnswer2.setChecked(data.myAnswer2);
            cbAnswer3.setChecked(data.myAnswer3);
            cbAnswer4.setChecked(data.myAnswer4);
            cbAnswer1.setEnabled(!data.isShowCorrectAnswer);
            cbAnswer2.setEnabled(!data.isShowCorrectAnswer);
            cbAnswer3.setEnabled(!data.isShowCorrectAnswer);
            cbAnswer4.setEnabled(!data.isShowCorrectAnswer);
            cbAnswer1.setVisibility(data.answerContent1.equals("") ? View.GONE : View.VISIBLE);
            cbAnswer2.setVisibility(data.answerContent2.equals("") ? View.GONE : View.VISIBLE);
            cbAnswer3.setVisibility(data.answerContent3.equals("") ? View.GONE : View.VISIBLE);
            cbAnswer4.setVisibility(data.answerContent4.equals("") ? View.GONE : View.VISIBLE);

            if (data.isShowCorrectAnswer) {
                if (data.correctAnswer1) {
                    cbAnswer1.setTextColor(Color.BLUE);
                } else {
                    cbAnswer1.setTextColor(Color.GRAY);
                }

                if (data.correctAnswer2) {
                    cbAnswer2.setTextColor(Color.BLUE);
                } else {
                    cbAnswer2.setTextColor(Color.GRAY);
                }

                if (data.correctAnswer3) {
                    cbAnswer3.setTextColor(Color.BLUE);
                } else {
                    cbAnswer3.setTextColor(Color.GRAY);
                }

                if (data.correctAnswer4) {
                    cbAnswer4.setTextColor(Color.BLUE);
                } else {
                    cbAnswer4.setTextColor(Color.GRAY);
                }
            } else {
                cbAnswer1.setTextColor(Color.BLACK);
                cbAnswer2.setTextColor(Color.BLACK);
                cbAnswer3.setTextColor(Color.BLACK);
                cbAnswer4.setTextColor(Color.BLACK);
            }

            Glide.with(itemView.getContext()).load(data.image).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(ivQuestion);
        }

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            switch (compoundButton.getId()) {
                case R.id.cbAnswer1:
                    question.myAnswer1 = b;
                    break;
                case R.id.cbAnswer2:
                    question.myAnswer2 = b;
                    break;
                case R.id.cbAnswer3:
                    question.myAnswer3 = b;
                    break;
                case R.id.cbAnswer4:
                    question.myAnswer4 = b;
                    break;
            }
        }
    }

    private String removePrefix(String questionContent) {
        if (questionContent.startsWith("Câu")) {
            return questionContent.substring(questionContent.indexOf(".") + 2);
        }

        return questionContent;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
        notifyDataSetChanged();
    }

    public void clear() {

        for (int i = 0; i < questions.size(); i++) {
            questions.get(i).isShowCorrectAnswer = false;
            questions.get(i).myAnswer1 = false;
            questions.get(i).myAnswer2 = false;
            questions.get(i).myAnswer3 = false;
            questions.get(i).myAnswer4 = false;
        }

        notifyDataSetChanged();
    }
}
