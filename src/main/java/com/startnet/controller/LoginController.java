package com.startnet.controller;

import com.startnet.service.ISysUserService;
import com.startnet.utils.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Description: 登陆</p >
 * <p>Copyright: Copyright (c)2024</p >
 * <p>Company: starNet</p >
 * <P>Created Date: 2024年04月24日</P>
 *
 * @author LiuYuHan
 * @version 1.0
 */
public class LoginController implements Controller {

    private ISysUserService sysUserService;

    public void setSysUserService(ISysUserService sysUserService){
        this.sysUserService = sysUserService;
    }
    @Override
    public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
        arg1.setHeader("Content-type", "text/html;charset=UTF-8");
        String userName = arg0.getParameter("userName");
        String password = arg0.getParameter("passwd");

        // 验证用户名和密码不为空
        if(StringUtils.isEmpty(userName)){
            arg1.getOutputStream().write("-1".getBytes());
            return null;
        }

        if(StringUtils.isEmpty(password)){
            arg1.getOutputStream().write("-2".getBytes());
            return null;
        }

        int ret = sysUserService.login(userName, password);
        arg1.getOutputStream().write(String.valueOf(ret).getBytes());
        return null;
    }
}