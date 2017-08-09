package com.example.minhnt.thibanglaixeoto.learn;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.minhnt.thibanglaixeoto.R;
import com.example.minhnt.thibanglaixeoto.object.Answer;
import com.example.minhnt.thibanglaixeoto.object.Question;
import com.example.minhnt.thibanglaixeoto.util.Constants;

import java.util.List;


/**
 * Created by minh.nt on 8/9/2017.
 */

public class ExamAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Question> questions;
    private final int NO_PHOTO_MULTI = 0;
    private final int PHOTO_MULTI = 1;
    private final int NO_PHOTO_SINGLE = 2;
    private final int PHOTO_SINGLE = 3;

    public ExamAdapter(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case NO_PHOTO_MULTI:
                View noPhotoMulti = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_learn_no_photo_multi, parent, false);
                return new NoPhoToMultiHolder(noPhotoMulti);
            case PHOTO_MULTI:
                View photoMulti = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_learn_photo_multi, parent, false);
                return new PhotoMultiHolder(photoMulti);
            case NO_PHOTO_SINGLE:
                View noPhotoSingle = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_learn_no_photo_single, parent, false);
                return new NoPhotoSingleHolder(noPhotoSingle);
            default:
                View photoSingle = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_learn_photo_single, parent, false);
                return new PhotoSingleHolder(photoSingle);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NoPhoToMultiHolder) {
            ((NoPhoToMultiHolder) holder).setData(questions.get(position));
        } else if (holder instanceof PhotoMultiHolder) {
            ((PhotoMultiHolder) holder).setData(questions.get(position));
        } else if (holder instanceof NoPhotoSingleHolder) {
            ((NoPhotoSingleHolder) holder).setData(questions.get(position));
        } else {
            ((PhotoSingleHolder) holder).setData(questions.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        Question question = questions.get(position);
        if (question.image.equals("") && question.type.equals(Constants.MULTI_CHOICE)) {
            return NO_PHOTO_MULTI;
        } else if (question.image.equals("") && question.type.equals(Constants.SINGLE_CHOICE)) {
            return NO_PHOTO_SINGLE;
        } else if (!question.image.equals("") && question.type.equals(Constants.MULTI_CHOICE)) {
            return PHOTO_MULTI;
        } else {
            return PHOTO_SINGLE;
        }
    }

    @Override
    public int getItemCount() {
        return questions == null ? 0 : questions.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNumber, tvQuestionContent;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            tvQuestionContent = itemView.findViewById(R.id.tvQuestionContent);
        }

        public void setData(Question data) {
            tvNumber.setText((getLayoutPosition() + 1) + " / 30");
            tvQuestionContent.setText(removePrefix(data.questionContent));
        }
    }

    class NoPhoToMultiHolder extends MyViewHolder {
        private CheckBox cbAnswer1, cbAnswer2, cbAnswer3, cbAnswer4;

        public NoPhoToMultiHolder(View itemView) {
            super(itemView);
            cbAnswer1 = itemView.findViewById(R.id.cbAnswer1);
            cbAnswer2 = itemView.findViewById(R.id.cbAnswer2);
            cbAnswer3 = itemView.findViewById(R.id.cbAnswer3);
            cbAnswer4 = itemView.findViewById(R.id.cbAnswer4);
        }

        public void setData(Question data) {
            super.setData(data);
            setAnswerText(data.answer, cbAnswer1, cbAnswer2, cbAnswer3, cbAnswer4);
        }
    }

    class PhotoMultiHolder extends MyViewHolder {
        private ImageView ivQuestion;
        private CheckBox cbAnswer1, cbAnswer2, cbAnswer3, cbAnswer4;

        public PhotoMultiHolder(View itemView) {
            super(itemView);
            cbAnswer1 = itemView.findViewById(R.id.cbAnswer1);
            cbAnswer2 = itemView.findViewById(R.id.cbAnswer2);
            cbAnswer3 = itemView.findViewById(R.id.cbAnswer3);
            cbAnswer4 = itemView.findViewById(R.id.cbAnswer4);
            ivQuestion = itemView.findViewById(R.id.ivQuestion);
        }

        public void setData(Question data) {
            super.setData(data);
            setAnswerText(data.answer, cbAnswer1, cbAnswer2, cbAnswer3, cbAnswer4);
            Glide.with(itemView.getContext()).load(data.image).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(ivQuestion);
        }
    }

    class NoPhotoSingleHolder extends MyViewHolder {
        private RadioButton rbAnswer1, rbAnswer2, rbAnswer3, rbAnswer4;

        public NoPhotoSingleHolder(View itemView) {
            super(itemView);
            rbAnswer1 = itemView.findViewById(R.id.rbAnswer1);
            rbAnswer2 = itemView.findViewById(R.id.rbAnswer2);
            rbAnswer3 = itemView.findViewById(R.id.rbAnswer3);
            rbAnswer4 = itemView.findViewById(R.id.rbAnswer4);
        }

        @Override
        public void setData(Question data) {
            super.setData(data);
            setAnswerText(data.answer, rbAnswer1, rbAnswer2, rbAnswer3, rbAnswer4);
        }
    }

    class PhotoSingleHolder extends MyViewHolder {
        private ImageView ivQuestion;
        private RadioButton rbAnswer1, rbAnswer2, rbAnswer3, rbAnswer4;

        public PhotoSingleHolder(View itemView) {
            super(itemView);
            rbAnswer1 = itemView.findViewById(R.id.rbAnswer1);
            rbAnswer2 = itemView.findViewById(R.id.rbAnswer2);
            rbAnswer3 = itemView.findViewById(R.id.rbAnswer3);
            rbAnswer4 = itemView.findViewById(R.id.rbAnswer4);
            ivQuestion = itemView.findViewById(R.id.ivQuestion);
        }

        @Override
        public void setData(Question data) {
            super.setData(data);
            setAnswerText(data.answer, rbAnswer1, rbAnswer2, rbAnswer3, rbAnswer4);
            Glide.with(itemView.getContext()).load(data.image).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(ivQuestion);
        }
    }

    private String removePrefix(String questionContent) {
        if (questionContent.startsWith("Câu")) {
            return questionContent.substring(questionContent.indexOf(".") + 2);
        }

        return questionContent;
    }

    private void setAnswerText(List<Answer> answer, CompoundButton view1, CompoundButton view2, CompoundButton view3, CompoundButton view4) {
        if (answer.size() >= 2) {
            view1.setVisibility(View.VISIBLE);
            view2.setVisibility(View.VISIBLE);
            view1.setText(answer.get(0).content);
            view2.setText(answer.get(1).content);
        }
        if (answer.size() >= 3) {
            view3.setVisibility(View.VISIBLE);
            view3.setText(answer.get(2).content);
        }
        if (answer.size() >= 4) {
            view4.setVisibility(View.VISIBLE);
            view4.setText(answer.get(3).content);
        }
    }
}
