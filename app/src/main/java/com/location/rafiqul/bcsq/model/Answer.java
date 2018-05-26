package com.location.rafiqul.bcsq.model;

import java.util.Date;

/**
 * Created by RAFIQUL on 2018-05-19.
 */

public class Answer extends CommonProp{
    private int questionId;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private int curAnswer;
public Answer(){}

    public Answer(int questionId, String answer1, String answer2, String answer3, String answer4, int curAnswer,Integer answerID,int status) {
        super(answerID, status);
        this.questionId = questionId;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.curAnswer = curAnswer;
    }

    public Answer(int createdBy, int modifiedBy, Date modifiedDate, int satus, int id, int questionId, String answer1, String answer2, String answer3, String answer4, int curAnswer) {
        super(createdBy, modifiedBy, modifiedDate, satus, id);
        this.questionId = questionId;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.curAnswer = curAnswer;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public int getCurAnswer() {
        return curAnswer;
    }

    public void setCurAnswer(int curAnswer) {
        this.curAnswer = curAnswer;
    }
}
