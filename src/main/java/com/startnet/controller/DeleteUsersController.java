package com.startnet.controller;

import com.startnet.service.ISysUserService;
import com.startnet.utils.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Description: 删除用户</p >
 * <p>Copyright: Copyright (c)2024</p >
 * <p>Company: starNet</p >
 * <P>Created Date: 2024年04月26日</P>
 *
 * @author LiuYuHan
 * @version 1.0
 */
public class DeleteUsersController implements Controller {
    private ISysUserService sysUserService;

    public void setSysUserService(ISysUserService sysUserService){
        this.sysUserService = sysUserService;
    }
    @Override
    public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) {
        String userIds = arg0.getParameter("userIds");
        if (StringUtils.isEmpty(userIds)){
            return null;
        }

        String[] userIdArr = userIds.split(",");
        List<Long> userIdList = Arrays.stream(userIdArr)
                .map(Long::valueOf)
                .collect(Collectors.toList());

        try{
            int ret = sysUserService.deleteUsers(userIdList);
            arg1.getOutputStream().write(String.valueOf(ret).getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}