package com.startnet.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * <p>Description: todo</p >
 * <p>Copyright: Copyright (c)2024</p >
 * <p>Company: todo</p >
 * <P>Created Date: 2024年04月24日</P>
 *
 * @author LiuYuHan
 * @version 1.0
 */
public class DBUtils {

    public static DBUtils dbUtils = new DBUtils();
    public static String dbUrl;
    private static String user;
    private static String password;

    public static DBUtils getInstance(){
        return dbUtils;
    }

    @SuppressWarnings("unused")
    private void init(){
        Properties properties = new Properties();
        try{
            properties.load(this.getClass().getClassLoader().getResourceAsStream("dbconfig.properties"));
            String jdbcUrl = properties.getProperty("jdbc.jdbcUrl");
            String driverClass = properties.getProperty("jdbc.driverClass");
            user = properties.getProperty("jdbc.user");
            password = properties.getProperty("jdbc.password");
            Class.forName(driverClass);
            DBUtils.dbUrl = jdbcUrl;
        } catch (Exception e) {
            throw new RuntimeException("DBUtils-init error", e);
        }
    }

    public Connection getConn(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(dbUrl, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void releaseAll(Connection conn, PreparedStatement pstm, ResultSet rs) {
        if(rs != null){
            try {
                // 关闭结果集
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if(pstm != null){
            try{
                // 关闭预编译的SQL语句对象
                pstm.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if (conn != null){
            try {
                // 关闭数据库
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}