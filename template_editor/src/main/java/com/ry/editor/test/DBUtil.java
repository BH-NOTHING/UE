package com.ry.editor.test;

import com.ry.editor.template.ueditor.ConfigManager;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/*
 * 数据库工具类
 */
public class DBUtil {
    //获取数据库连接
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Properties prop = new Properties();
            InputStream in = ConfigManager.class.getResourceAsStream("/conf/jdbc.properties");
            prop.load(in);
            String driver = prop.getProperty("jdbc.driverClassName");
            String url = prop.getProperty("jdbc.url");
            String username = prop.getProperty("jdbc.username");
            String password = prop.getProperty("jdbc.password");
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    //关闭所有资源
    public static void closeAll(ResultSet res, Statement stmt, Connection conn) {
        try {
            if (res != null)
                res.close();
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}