package com.ry.editor.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * StudentDAOImpl实现类，实现相关的操作
 */
public class StudentDAOImpl implements StudentDAO {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<Student>();
        String sql = "select * from student";
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Student stu = new Student(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6));
                students.add(stu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(rs, pstmt, conn);
        }
        return students;
    }

    @Override
    public void addStudent(Student stu) {
        String sql = "insert into student values (null,?,?,?,?,?)";
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, stu.getName());
            pstmt.setInt(2, stu.getAge());
            pstmt.setString(3, stu.getGender());
            pstmt.setString(4, stu.getPhone());
            pstmt.setString(5, stu.getEducation());
            pstmt.executeUpdate();
            System.out.println("添加學生成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(rs, pstmt, conn);
        }
    }

    @Override
    public void deleteStudent(int id) {
        String sql = "delete from student where id=?";
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1, id);
            pstmt.executeUpdate();
            System.out.println("删除學生成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(rs, pstmt, conn);
        }
    }

    @Override
    public void updateStudent(Student stu) {
        String sql = "update student set name=?,age=?,gender=?,phone=?,education=? where id=?";
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, stu.getName());
            pstmt.setInt(2, stu.getAge());
            pstmt.setString(3, stu.getGender());
            pstmt.setString(4, stu.getPhone());
            pstmt.setString(5, stu.getEducation());
            pstmt.setInt(6, stu.getId());
            pstmt.executeUpdate();
            System.out.println("修改學生成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(rs, pstmt, conn);
        }
    }


    @Override
    public Student getStudentById(int id) {
        Student stu = null;
        String sql = "select * from student where id=?";
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                stu = new Student(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(rs, pstmt, conn);
        }
        return stu;
    }

    @Override
    public List<Student> getStudentsByCondition(String name, String gender) {
        List<Student> students = new ArrayList<Student>();
        String sql = "select * from student where name like ? and gender=?";
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1, "%" + name + "%");//注意此写法
            pstmt.setObject(2, gender);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Student stu = new Student(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6));
                students.add(stu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(rs, pstmt, conn);
        }
        return students;
    }
}