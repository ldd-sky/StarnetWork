package com.startnet.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * <p>Description: 密码验证工具类</p >
 * <p>Copyright: Copyright (c)2024</p >
 * <p>Company: starNet</p >
 * <P>Created Date: 2024年05月16日</P>
 *
 * @author LiuYuHan
 * @version 1.0
 */
public class SecurityUtils {
    /**
     * 加密
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }


    /**
     * 密码匹配
     *
     * @param realPassword 真实密码
     * @param encodedPassword 加密后字符
     * @return true(密码正确)  false(密码错误)
     */
    public static boolean matchesPassword(String realPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(realPassword, encodedPassword);
    }


    public static void main(String[] args) {
        System.out.println(encryptPassword("1"));
    }
}