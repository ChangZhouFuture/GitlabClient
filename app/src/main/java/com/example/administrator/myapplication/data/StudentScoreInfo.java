package com.example.administrator.myapplication.data;

/**
 * Created by Administrator on 2017/6/24.
 */

public class StudentScoreInfo {
    private int studentId;
    private String studentName;
    private String studentNumber;
    private int score;
    private Boolean scored;

    public StudentScoreInfo(int studentId, String studentName, String studentNumber, int score, Boolean scored){
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentNumber = studentNumber;
        this.score = score;
        this.scored = scored;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
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
