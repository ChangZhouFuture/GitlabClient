package com.example.administrator.myapplication.data;

/**
 * Created by Administrator on 2017/6/22.
 */

public class UserInfo {
    private int id;
    private String username;
    private String name;
    private String type;
    private String avatar;
    private String gender;
    private String email;
    private String gitId;
    private int schoolId;
    private String gitUsername;
    private String number;
    private String groupId;

    public UserInfo(int id, String username, String name, String type, String avatar, String gender, String email, String gitId, int schoolId, String gitUsername, String number,
                    String groupId){
        this.id = id;
        this.username = username;
        this.name = name;
        this.type = type;
        this.avatar = avatar;
        this.gender = gender;
        this.email = email;
        this.gitId = gitId;
        this.schoolId = schoolId;
        this.gitUsername = gitUsername;
        this.number = number;
        this.groupId = groupId;
    }

    public UserInfo(int id, String username, String name, String type, String gender, String email, String gitId, int schoolId,
                    String gitUsername, String number, String groupId){
        this.id = id;
        this.username = username;
        this.name = name;
        this.type = type;
        this.gender = gender;
        this.email = email;
        this.gitId = gitId;
        this.schoolId = schoolId;
        this.gitUsername = gitUsername;
        this.number = number;
        this.groupId = groupId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGitId() {
        return gitId;
    }

    public void setGitId(String gitId) {
        this.gitId = gitId;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public String getGitUsername() {
        return gitUsername;
    }

    public void setGitUsername(String gitUsername) {
        this.gitUsername = gitUsername;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
