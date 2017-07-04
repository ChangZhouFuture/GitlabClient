package com.example.administrator.myapplication.data;

/**
 * Created by Administrator on 2017/6/25.
 */

public class TestRes {
    private String gitUrl;
    private Boolean complieSucceeded;
    private Boolean tested;
    private TestCase[] testCases;

    public TestRes(String gitUrl, Boolean complieSucceeded, Boolean tested, TestCase[] testCases){
        this.gitUrl = gitUrl;
        this.complieSucceeded = complieSucceeded;
        this.tested = tested;
        this.testCases = testCases;
    }

    public String getGitUrl() {
        return gitUrl;
    }

    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
    }

    public Boolean getComplieSucceeded() {
        return complieSucceeded;
    }

    public void setComplieSucceeded(Boolean complieSucceeded) {
        this.complieSucceeded = complieSucceeded;
    }

    public Boolean getTested() {
        return tested;
    }

    public void setTested(Boolean tested) {
        this.tested = tested;
    }

    public TestCase[] getTestCases() {
        return testCases;
    }

    public void setTestCases(TestCase[] testCases) {
        this.testCases = testCases;
    }
}
