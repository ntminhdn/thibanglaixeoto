package com.example.minhnt.thibanglaixeoto.object;

/**
 * Created by minh.nt on 8/8/2017.
 */

public class Question {
    public String type;
    public String questionContent;
    public String image = "";
    public String answerContent1 = "";
    public String answerContent2 = "";
    public String answerContent3 = "";
    public String answerContent4 = "";
    public boolean correctAnswer1 = false;
    public boolean correctAnswer2 = false;
    public boolean correctAnswer3 = false;
    public boolean correctAnswer4 = false;

    public boolean myAnswer1 = false;
    public boolean myAnswer2 = false;
    public boolean myAnswer3 = false;
    public boolean myAnswer4 = false;

    public boolean isShowCorrectAnswer = false;

    public Question(String type, String questionContent, String image, String answerContent1, String answerContent2, String answerContent3, String answerContent4, boolean correctAnswer1, boolean correctAnswer2, boolean correctAnswer3, boolean correctAnswer4) {
        this.type = type;
        this.questionContent = questionContent;
        this.image = image;
        this.answerContent1 = answerContent1;
        this.answerContent2 = answerContent2;
        this.answerContent3 = answerContent3;
        this.answerContent4 = answerContent4;
        this.correctAnswer1 = correctAnswer1;
        this.correctAnswer2 = correctAnswer2;
        this.correctAnswer3 = correctAnswer3;
        this.correctAnswer4 = correctAnswer4;
    }
}
