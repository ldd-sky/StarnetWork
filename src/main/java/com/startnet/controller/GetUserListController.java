package com.startnet.controller;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.startnet.bean.SysUser;
import com.startnet.service.ISysUserService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>Description: 获取用户信息列表</p >
 * <p>Copyright: Copyright (c)2024</p >
 * <p>Company: starNet</p >
 * <P>Created Date: 2024年04月24日</P>
 *
 * @author LiuYuHan
 * @version 1.0
 */
public class GetUserListController implements Controller {
    private ISysUserService sysUserService;

    public void setSysUserService(ISysUserService sysUserService){
        this.sysUserService = sysUserService;
    }


    @Override
    public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
        arg1.setHeader("Content-type", "text/json;charset=UTF-8");
        List<SysUser> userList = sysUserService.listAll();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(userList);

        byte[] retArr = jsonString.getBytes();
        arg1.getOutputStream().write(retArr);
        return null;
    }
}