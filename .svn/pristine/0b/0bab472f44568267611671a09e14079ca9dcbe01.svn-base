package com.ry.editor.test;

import java.util.Iterator;
import java.util.List;

/*
 * 测试类
 */
public class TestStudent {
    public static void main(String[] args) {
        StudentDAO sd = new StudentDAOImpl();
        List<Student> students = sd.getAllStudents();
        System.out.println(students.toString());
    }


    public void mains(String[] args) {
        //添加学生
        StudentDAO sd = new StudentDAOImpl();
        sd.addStudent(new Student("tom", 20, "男", "110", "本科"));

        //删除学生
        sd.deleteStudent(2);

        //修改学生
        sd.updateStudent(new Student(5, "汤姆", 25, "男", "112", "研究生"));

        //查询所有学生
        List<Student> students = sd.getAllStudents();
        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            Student stu = it.next();
            System.out.println(stu.getId() + "," + stu.getName() + "," + stu.getEducation());
        }

        //根据编号查询学生
        Student stu = sd.getStudentById(5);
        System.out.println(stu.getId() + "," + stu.getName() + "," + stu.getEducation());

        //模糊查找
        List<Student> stus = sd.getStudentsByCondition("姆", "男");
        Iterator<Student> its = stus.iterator();
        while (it.hasNext()) {
            Student sts = its.next();
            System.out.println(sts.getId() + "," + sts.getName() + "," + sts.getEducation());
        }
    }
}