package com.example.administrator.myapplication.data;

/**
 * Created by Administrator on 2017/6/25.
 */

public class ScoreRes {
    private String gitUrl;
    private int score;
    private Boolean scored;

    public ScoreRes(String gitUrl, int score, Boolean scored){
        this.gitUrl = gitUrl;
        this.score = score;
        this.scored = scored;
    }

    public String getGitUrl() {
        return gitUrl;
    }

    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Boolean getScored() {
        return scored;
    }

    public void setScored(Boolean scored) {
        this.scored = scored;
    }
}
