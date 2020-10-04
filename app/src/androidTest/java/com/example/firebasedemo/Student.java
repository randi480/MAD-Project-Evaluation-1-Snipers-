package com.example.firebasedemo;

public class Student {
    private  String id;
    private String name;
    private String add;
    private Integer conNo;

    public Student() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public Integer getConNo() {
        return conNo;
    }

    public void setConNo(Integer conNo) {
        this.conNo = conNo;
    }
}
