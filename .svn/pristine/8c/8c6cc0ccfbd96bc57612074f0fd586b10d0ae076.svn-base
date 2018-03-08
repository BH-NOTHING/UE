package com.ry.editor.test;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KING on 2017/11/8.
 */
public class MostUsedSpechars implements ApplicationContextAware {
    public List<Student> cacheSpechars = new ArrayList<Student>();

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.cacheSpechars = getAllStudents();
    }

    public List<Student> getAllStudents() {
        List<Student> Students = new ArrayList<Student>();
        try {


            String sql = "select * from Student";
            try {

                conn = DBUtil.getConnection();
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    Student stu = new Student(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6));
                    Students.add(stu);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                DBUtil.closeAll(rs, pstmt, conn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return Students;
    }

}
