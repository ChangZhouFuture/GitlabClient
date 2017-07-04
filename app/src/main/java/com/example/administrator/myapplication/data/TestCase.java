package com.example.administrator.myapplication.data;

/**
 * Created by Administrator on 2017/6/25.
 */

public class TestCase {
    private String name;
    private Boolean passed;

    public TestCase(String name, Boolean passed){
        this.name = name;
        this.passed = passed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getPassed() {
        return passed;
    }

    public void setPassed(Boolean passed) {
        this.passed = passed;
    }
}
