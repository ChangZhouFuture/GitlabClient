package com.example.administrator.myapplication.data;

/**
 * Created by Administrator on 2017/6/24.
 */

public class ScoreInfo {
    private int assignmentId;
    private QuestionSimpleInfo[] questions;

    public ScoreInfo(int assignmentId, QuestionSimpleInfo[] questions){
        this.assignmentId = assignmentId;
        this.questions = questions;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public QuestionSimpleInfo[] getQuestions() {
        return questions;
    }

    public void setQuestions(QuestionSimpleInfo[] questions) {
        this.questions = questions;
    }
}
