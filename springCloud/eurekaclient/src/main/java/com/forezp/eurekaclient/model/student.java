package com.forezp.eurekaclient.model;

public class student {
    private Integer id;

    private String studentName;

    private Integer age;

    private String studentWork;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentWork() {
        return studentWork;
    }

    public void setStudentWork(String studentWork) {
        this.studentWork = studentWork;
    }
}