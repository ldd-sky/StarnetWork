package com.startnet.controller;

import com.startnet.bean.SysUser;
import com.startnet.service.ISysUserService;
import com.startnet.utils.SecurityUtils;
import com.startnet.utils.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Description: 添加用户</p >
 * <p>Copyright: Copyright (c)2024</p >
 * <p>Company: starNet</p >
 * <P>Created Date: 2024年04月24日</P>
 *
 * @author LiuYuHan
 * @version 1.0
 */
public class AddUserController implements Controller {
    private ISysUserService sysUserService;

    public void setSysUserService(ISysUserService sysUserService){
        this.sysUserService = sysUserService;
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
        arg1.setHeader("Content-type", "text/html;charset=UTF-8");
        String userName = arg0.getParameter("userName");
        String password = arg0.getParameter("password");
        String age = arg0.getParameter("age");
        String sex = arg0.getParameter("sex");
        String mobilePhone = arg0.getParameter("mobilePhone");
        String address = arg0.getParameter("address");

        // 验证用户名和密码不为空
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            arg1.getOutputStream().write("-2".getBytes());
            return null;
        }

        // 创建对象
        SysUser sysUser = new SysUser();
        sysUser.setUsername(userName);
        // 使用BCryptPasswordEncoder对密码进行加密
        sysUser.setPassword(SecurityUtils.encryptPassword(password));
        sysUser.setAge(StringUtils.isNotEmpty(age) ? Integer.parseInt(age) : 0);
        sysUser.setSex(StringUtils.isNotEmpty(sex) && sex.equals("男") ? 0 : 1);
        sysUser.setMobilePhone(mobilePhone);
        sysUser.setAddress(address);

        int ret = sysUserService.addSysUser(sysUser);
        arg1.getOutputStream().write(String.valueOf(ret).getBytes());

        return null;
    }
}