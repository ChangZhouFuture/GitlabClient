package com.example.administrator.myapplication.data;

/**
 * Created by Administrator on 2017/6/25.
 */

public class QuestionRes {
    private int questionId;
    private String questionTitle;
    private ScoreRes scoreRes;
    private TestRes testRes;
    private MetricData metricData;

    public QuestionRes(int questionId, String questionTitle, ScoreRes scoreRes, TestRes testRes, MetricData metricData){
        this.questionId = questionId;
        this.questionTitle = questionTitle;
        this.scoreRes = scoreRes;
        this.testRes = testRes;
        this.metricData = metricData;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public TestRes getTestRes() {
        return testRes;
    }

    public void setTestRes(TestRes testRes) {
        this.testRes = testRes;
    }

    public ScoreRes getScoreRes() {
        return scoreRes;
    }

    public void setScoreRes(ScoreRes scoreRes) {
        this.scoreRes = scoreRes;
    }

    public MetricData getMetricData() {
        return metricData;
    }

    public void setMetricData(MetricData metricData) {
        this.metricData = metricData;
    }
}
