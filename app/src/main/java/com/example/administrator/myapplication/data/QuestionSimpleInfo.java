package com.example.administrator.myapplication.data;

/**
 * Created by Administrator on 2017/6/24.
 */

public class QuestionSimpleInfo {
    private int id;
    private String title;
    private String description;
    private String type;
    private String studentsJson;

    public QuestionSimpleInfo(int id, String title, String description, String type, String studentsJson){
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.studentsJson = studentsJson;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStudents() {
        return studentsJson;
    }

    public void setStudents(String studentsJson) {
        this.studentsJson = studentsJson;
    }
}
