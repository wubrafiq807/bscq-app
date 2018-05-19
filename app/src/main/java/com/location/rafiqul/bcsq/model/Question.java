package com.location.rafiqul.bcsq.model;

import java.util.Date;

/**
 * Created by RAFIQUL on 2018-05-19.
 */

public class Question extends CommonProp{
    private int subCategoryId;
    private String question;
    private String description;
    private String answer;
    private int isMultipleAns;

    public Question(int createdBy, int modifiedBy, Date modifiedDate, int satus, int id, int subCategoryId, String question, String description, String answer, int isMultipleAns) {
        super(createdBy, modifiedBy, modifiedDate, satus, id);
        this.subCategoryId = subCategoryId;
        this.question = question;
        this.description = description;
        this.answer = answer;
        this.isMultipleAns = isMultipleAns;
    }

    public int getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getIsMultipleAns() {
        return isMultipleAns;
    }

    public void setIsMultipleAns(int isMultipleAns) {
        this.isMultipleAns = isMultipleAns;
    }
}
