package com.example.administrator.myapplication.data;

/**
 * Created by Administrator on 2017/6/21.
 */

public class ClassInfo {
    private int id;
    private String name;

    public ClassInfo(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

}
