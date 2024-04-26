package com.startnet.service;

import com.mysql.cj.xdevapi.JsonValue;
import com.startnet.bean.SysUser;

import java.util.Collection;
import java.util.List;

/**
 * <p>Description: 用户Service</p >
 * <p>Copyright: Copyright (c)2024</p >
 * <p>Company: starNet</p >
 * <P>Created Date: 2024年04月24日</P>
 *
 * @author LiuYuHan
 * @version 1.0
 */
public interface ISysUserService {

    /**
     * 添加用户
     * @param sysUser   用户信息
     * @return          0 成功
     */
    public int addSysUser(SysUser sysUser);

    /**
     * 查询用户
     *
     * @return allUser
     */
    public List<SysUser> listAll();

    /**
     * 用户登录
     * @param userName  用户名
     * @param pwd       密码
     * @return          0 成功 -1 失败
     */
    public int login(String userName, String pwd);

    /**
     * 删除用户
     * @param userIds   用户id
     * @return          0 成功 -1 失败
     */
    public int deleteUsers(List<Long> userIds);
}