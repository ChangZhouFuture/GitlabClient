package com.example.administrator.myapplication.data;

/**
 * Created by Administrator on 2017/6/23.
 */

public class QuestionInfo {
    private int id;
    private String title;
    private String description;
    private String difficulty;
    private String gitUrl;
    private String type;
    private UserInfo ui;

    public QuestionInfo(int id, String title, String description, String difficulty, String gitUrl, String type, UserInfo ui){
        this.id = id;
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.gitUrl = gitUrl;
        this.type = type;
        this.ui = ui;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getGitUrl() {
        return gitUrl;
    }

    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UserInfo getUi() {
        return ui;
    }

    public void setUi(UserInfo ui) {
        this.ui = ui;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getLink() {
        return link;
    }

    public void setLink(int link) {
        this.link = link;
    }

    public String getKnowledgeVos() {
        return knowledgeVos;
    }

    public void setKnowledgeVos(String knowledgeVos) {
        this.knowledgeVos = knowledgeVos;
    }

    private int duration;
    private int link;
    private String knowledgeVos;


}
