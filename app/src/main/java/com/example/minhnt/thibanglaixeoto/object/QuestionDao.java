package com.example.minhnt.thibanglaixeoto.object;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by minh.nt on 8/11/2017.
 */

public class QuestionDao extends RealmObject {
    @PrimaryKey
    public int id;
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

    public Question convert() {
        return new Question(type, questionContent, image, answerContent1, answerContent2, answerContent3, answerContent4, correctAnswer1, correctAnswer2, correctAnswer3, correctAnswer4);
    }
}
