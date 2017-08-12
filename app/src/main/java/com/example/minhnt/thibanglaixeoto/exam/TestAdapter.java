package com.example.minhnt.thibanglaixeoto.exam;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.minhnt.thibanglaixeoto.R;
import com.example.minhnt.thibanglaixeoto.object.Question;
import com.example.minhnt.thibanglaixeoto.object.QuestionDao;
import com.example.minhnt.thibanglaixeoto.practice.PracticeAdapter;
import com.example.minhnt.thibanglaixeoto.util.Util;

import java.util.List;

import io.realm.Realm;

/**
 * Created by PC on 8/12/2017.
 */

public class TestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Question> questions;

    public TestAdapter(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View photoSingle = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_learn, parent, false);
        return new TestAdapter.MyViewHolder(photoSingle);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((TestAdapter.MyViewHolder) holder).setData(questions.get(position));
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
        private Button btnSave;

        public MyViewHolder(final View itemView) {
            super(itemView);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            tvQuestionContent = itemView.findViewById(R.id.tvQuestionContent);
            ivQuestion = itemView.findViewById(R.id.ivQuestion);
            cbAnswer1 = itemView.findViewById(R.id.cbAnswer1);
            cbAnswer2 = itemView.findViewById(R.id.cbAnswer2);
            cbAnswer3 = itemView.findViewById(R.id.cbAnswer3);
            cbAnswer4 = itemView.findViewById(R.id.cbAnswer4);
            btnSave = itemView.findViewById(R.id.btnSave);
            cbAnswer1.setOnCheckedChangeListener(this);
            cbAnswer2.setOnCheckedChangeListener(this);
            cbAnswer3.setOnCheckedChangeListener(this);
            cbAnswer4.setOnCheckedChangeListener(this);

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Realm realm = Realm.getDefaultInstance();
                    Question question = questions.get(getLayoutPosition());
                    QuestionDao quesDao = realm.where(QuestionDao.class).equalTo("questionContent", question.questionContent).equalTo("image", question.image).equalTo("answerContent1", question.answerContent1).findFirst();
                    if (quesDao != null) {
                        Util.showMessage(itemView.getContext(), "Câu hỏi này đã được lưu rồi", null);
                    } else {
                        realm.beginTransaction();
                        int nextId = 1;
                        if (realm.where(QuestionDao.class).max("id") != null)
                            nextId = (realm.where(QuestionDao.class).max("id").intValue() + 1);
                        QuestionDao questionDao = realm.createObject(QuestionDao.class, nextId);

                        Log.d("hehe", nextId + "");
                        questionDao.questionContent = question.questionContent;
                        questionDao.answerContent1 = question.answerContent1;
                        questionDao.answerContent2 = question.answerContent2;
                        questionDao.answerContent3 = question.answerContent3;
                        questionDao.answerContent4 = question.answerContent4;
                        questionDao.image = question.image;
                        questionDao.type = question.type;
                        questionDao.correctAnswer1 = question.correctAnswer1;
                        questionDao.correctAnswer2 = question.correctAnswer2;
                        questionDao.correctAnswer3 = question.correctAnswer3;
                        questionDao.correctAnswer4 = question.correctAnswer4;
                        realm.commitTransaction();
                        Toast.makeText(itemView.getContext(), "Lưu thành công!", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

        public void setData(Question data) {
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
            btnSave.setVisibility(data.isShowCorrectAnswer ? View.VISIBLE : View.GONE);

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
