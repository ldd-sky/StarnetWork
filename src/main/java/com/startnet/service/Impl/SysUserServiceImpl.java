package com.startnet.service.Impl;

import com.startnet.bean.SysUser;
import com.startnet.dao.SysUserDao;
import com.startnet.service.ISysUserService;
import java.util.List;

/**
 * <p>Description: 用户相关功能实现</p >
 * <p>Copyright: Copyright (c)2024</p >
 * <p>Company: starNet</p >
 * <P>Created Date: 2024年04月24日</P>
 *
 * @author LiuYuHan
 * @version 1.0
 */
public class SysUserServiceImpl implements ISysUserService {
    private SysUserDao sysUserDao;

    public void setSysUserDao(SysUserDao sysUserDao){
        this.sysUserDao = sysUserDao;
    }
    @Override
    public int addSysUser(SysUser sysUser) {
        return sysUserDao.addSysUser(sysUser);
    }

    @Override
    public List<SysUser> listAll() {
        return sysUserDao.listAll();
    }

    @Override
    public int login(String userName, String pwd) {
        return sysUserDao.findByUserNameAndPwd(userName, pwd);
    }

    @Override
    public int deleteUsers(List<Long> userIds) {
        return sysUserDao.deleteUserByIds(userIds);
    }
}