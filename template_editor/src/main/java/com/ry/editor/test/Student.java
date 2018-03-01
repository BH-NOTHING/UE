package com.ry.editor.test;

/*
 * 实体类Student，POJO
 */
public class Student {
    private int id;
    private String name;
    private int age;
    private String gender;
    private String phone;
    private String education;

    public Student() {
        super();
    }

    public Student(int id, String name, int age, String gender, String phone, String education) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.education = education;
    }

    public Student(String name, int age, String gender, String phone, String education) {
        super();
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.education = education;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
