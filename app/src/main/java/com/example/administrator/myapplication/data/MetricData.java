package com.example.administrator.myapplication.data;

/**
 * Created by Administrator on 2017/6/25.
 */

public class MetricData {
    private String gitUrl;
    private Boolean measured;
    private int totalLine;
    private int commentLine;
    private int fieldCount;
    private int methodCount;
    private int maxCoc;

    public MetricData(String gitUrl, Boolean measured, int totalLine, int commentLine, int fieldCount, int methodCount, int maxCoc){
        this.gitUrl = gitUrl;
        this.measured = measured;
        this.totalLine = totalLine;
        this.commentLine = commentLine;
        this.fieldCount = fieldCount;
        this.methodCount = methodCount;
        this.maxCoc = maxCoc;
    }

    public Boolean getMeasured() {
        return measured;
    }

    public void setMeasured(Boolean measured) {
        this.measured = measured;
    }

    public int getTotalLine() {
        return totalLine;
    }

    public void setTotalLine(int totalLine) {
        this.totalLine = totalLine;
    }

    public String getGitUrl() {
        return gitUrl;
    }

    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
    }

    public int getCommentLine() {
        return commentLine;
    }

    public void setCommentLine(int commentLine) {
        this.commentLine = commentLine;
    }

    public int getFieldCount() {
        return fieldCount;
    }

    public void setFieldCount(int fieldCount) {
        this.fieldCount = fieldCount;
    }

    public int getMethodCount() {
        return methodCount;
    }

    public void setMethodCount(int methodCount) {
        this.methodCount = methodCount;
    }

    public int getMaxCoc() {
        return maxCoc;
    }

    public void setMaxCoc(int maxCoc) {
        this.maxCoc = maxCoc;
    }
}
