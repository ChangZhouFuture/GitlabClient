package com.example.administrator.myapplication.data;

/**
 * Created by Administrator on 2017/6/23.
 */

public class TaskInfo {
    private int id;
    private String title;
    private String description;
    private String startAt;
    private String endAt;
    private String questionInfoArray;
    private int course;
    private String status;
    private String currentTime;

    public TaskInfo(int id, String title, String description, String startAt, String endAt, String qis, int course, String status, String currentTime){
        this.id = id;
        this.title = title;
        this.description = description;
        this.startAt = startAt;
        this.endAt = endAt;
        this.questionInfoArray = qis;
        this.course = course;
        this.status = status;
        this.currentTime = currentTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public String getQuestions() {
        return questionInfoArray;
    }

    public void setQuestions(String questions) {
        this.questionInfoArray = questions;
    }

    public String getEndAt() {
        return endAt;
    }

    public void setEndAt(String endAt) {
        this.endAt = endAt;
    }

    public String getStartAt() {
        return startAt;
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
