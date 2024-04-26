package com.startnet.dao;

import com.startnet.bean.SysUser;
import com.startnet.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: 用户表Dao层</p >
 * <p>Copyright: Copyright (c)2024</p >
 * <p>Company: starNet</p >
 * <P>Created Date: 2024年04月24日</P>
 *
 * @author LiuYuHan
 * @version 1.0
 */
public class SysUserDao {
    // 数据库连接
    private DBUtils dbUtils;

    public void setDbUtils(DBUtils dbUtils) {
        this.dbUtils = dbUtils;
    }

    /**
     * 获取当前最大id
     * @return  最大id
     */
    public long getMaxId() {
        String sql = "SELECT MAX(id) FROM sys_user";
        Connection conn = dbUtils.getConn();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                long maxId = rs.getInt(1);
                return maxId;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while retrieving max id", e);
        } finally {
            dbUtils.releaseAll(conn, pstm, rs);
        }

        return 0;
    }

    /**
     * 添加用户
     * @param sysUser   用户对象
     * @return          0 成功 -1 失败
     */
    public int addSysUser(SysUser sysUser){
        String sql = "insert into sys_user(id, userName,sex,age,mobile_phone,address,password) values(?,?,?,?,?,?,?)";
        Connection conn = dbUtils.getConn();
        PreparedStatement pstm = null;
        try {
            // 添加字段
            pstm = conn.prepareStatement(sql);
            pstm.setLong(1, getMaxId()+1);
            pstm.setString(2, sysUser.getUsername());
            pstm.setInt(3, sysUser.getSex());
            pstm.setInt(4, sysUser.getAge());
            pstm.setString(5, sysUser.getMobilePhone());
            pstm.setString(6, sysUser.getAddress());
            pstm.setString(7, sysUser.getPassword());
            pstm.executeUpdate();
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException("SysUserDao-addSysUser error", e);
        } finally {
            dbUtils.releaseAll(conn, pstm, null);
        }
    }

    /**
     * 查询所有用户
     * @return  List<SysUser>
     */
    public List<SysUser> listAll(){
        List<SysUser> dataList = new ArrayList<SysUser>();
        String sql = "select * from sys_user";
        Connection conn = dbUtils.getConn();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // 查询
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()){
                SysUser sysUser = new SysUser();
                sysUser.setId(rs.getLong("id"));
                sysUser.setUsername(rs.getString("userName"));
                sysUser.setSex(rs.getInt("sex"));
                sysUser.setAge(rs.getInt("age"));
                sysUser.setMobilePhone(rs.getString("mobile_phone"));
                sysUser.setAddress(rs.getString("address"));
                dataList.add(sysUser);
            }
        } catch (SQLException e) {
            throw new RuntimeException("SysUserDao-listAll error", e);
        } finally {
            dbUtils.releaseAll(conn, pstm, rs);
        }
        return dataList;
    }

    /**
     * 查询用户信息
     * @param userName  用户名
     * @param password  密码
     * @return          0 正确 -1 不正确
     */
    public int findByUserNameAndPwd(String userName, String password){
        String sql = "select * from sys_user t where t.userName=? and t.password=?";
        Connection conn = dbUtils.getConn();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // 查询
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, userName);
            pstm.setString(2, password);
            rs = pstm.executeQuery();
            if(rs.next()){
                return 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("SysUserDao-findByUserNameAndPwd error", e);
        } finally {
            dbUtils.releaseAll(conn, pstm, rs);
        }
        return -1;
    }

    /**
     * 根据id删除用户
     * @param userIds   用户id
     * @return          0 成功 -1 失败
     */
    public int deleteUserByIds(List<Long> userIds){
        String sql = "delete from sys_user where id in(?)";
        Connection conn = dbUtils.getConn();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // 查询
            pstm = conn.prepareStatement(sql);
            for (Long id : userIds){
                pstm.setLong(1, id);
                pstm.addBatch();
            }
            int[] ret = pstm.executeBatch();
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException("SysUserDao-deleteUserByIds error", e);
        } finally {
            dbUtils.releaseAll(conn, pstm, rs);
        }
    }
}