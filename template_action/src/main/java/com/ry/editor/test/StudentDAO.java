package com.ry.editor.test;

import java.util.List;

/*
 * StudentDAO接口，定义学生相关的操作
 */
public interface StudentDAO {

    //添加学生
    public void addStudent(Student stu);

    //删除学生
    public void deleteStudent(int id);

    //修改学生
    public void updateStudent(Student stu);

    //查询所有学生
    public List<Student> getAllStudents();

    //根据学号查询学生
    public Student getStudentById(int id);

    //根据条件模糊查询
    public List<Student> getStudentsByCondition(String name, String gender);
}