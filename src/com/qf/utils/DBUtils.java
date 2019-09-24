package com.qf.utils;

import java.sql.*;

public class DBUtils {

    private static String driverClass = DBConfig.getValues("driverClass");
    private static String url = DBConfig.getValues("url");
    private static String username = DBConfig.getValues("username");
    private static String password = DBConfig.getValues("password");

    //注册驱动
    static{
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //获取数据库连接对象
    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(url,username,password);
        return conn;
    }

    //释放资源
    public static void closeConnection(ResultSet rs, Statement stmt,Connection conn){
        try {
            if (rs!=null){
                rs.close();
            }
            if (stmt!=null){
                stmt.close();
            }
            if (conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static void closeConnection( PreparedStatement pstmt,Connection conn){
        try {
            if (pstmt!=null){
                pstmt.close();
            }
            if (conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
