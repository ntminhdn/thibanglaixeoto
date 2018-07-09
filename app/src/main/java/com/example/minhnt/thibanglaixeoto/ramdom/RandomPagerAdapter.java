package com.example.minhnt.thibanglaixeoto.ramdom;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
 * Created by PC on 8/12/2017.
 */

public class RandomPagerAdapter extends SmartFragmentStatePagerAdapter {
    private List<Question> questions;

    public RandomPagerAdapter(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public int getCount() {
        return questions != null ? questions.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public Object instantiateItem(ViewGroup collection, final int position) {
        Question data = questions.get(position);
        LayoutInflater inflater = LayoutInflater.from(collection.getContext());
        ViewGroup itemView = (ViewGroup) inflater.inflate(R.layout.item_test, collection, false);
        TextView tvQuestionContent = itemView.findViewById(R.id.tvQuestionContent);
        ImageView ivQuestion = itemView.findViewById(R.id.ivQuestion);
        CheckBox cbAnswer1 = itemView.findViewById(R.id.cbAnswer1);
        CheckBox cbAnswer2 = itemView.findViewById(R.id.cbAnswer2);
        CheckBox cbAnswer3 = itemView.findViewById(R.id.cbAnswer3);
        CheckBox cbAnswer4 = itemView.findViewById(R.id.cbAnswer4);
        tvQuestionContent.setText(removePrefix(data.questionContent));
        Glide.with(itemView.getContext()).load(data.image).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(ivQuestion);
        cbAnswer1.setText(data.answerContent1);
        cbAnswer2.setText(data.answerContent2);
        cbAnswer3.setText(data.answerContent3);
        cbAnswer4.setText(data.answerContent4);
        cbAnswer1.setChecked(data.myAnswer1);
        cbAnswer2.setChecked(data.myAnswer2);
        cbAnswer3.setChecked(data.myAnswer3);
        cbAnswer4.setChecked(data.myAnswer4);
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

        cbAnswer1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                questions.get(position).myAnswer1 = b;
            }
        });

        cbAnswer2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                questions.get(position).myAnswer2 = b;
            }
        });

        cbAnswer3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                questions.get(position).myAnswer3 = b;
            }
        });

        cbAnswer4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                questions.get(position).myAnswer4 = b;
            }
        });

        collection.addView(itemView);
        return itemView;
    }

    private String removePrefix(String questionContent) {
        if (questionContent.startsWith("Câu")) {
            return questionContent.substring(questionContent.indexOf(".") + 2);
        }

        return questionContent;
    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
