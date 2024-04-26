package com.startnet.utils;

/**
 * <p>Description: todo</p >
 * <p>Copyright: Copyright (c)2024</p >
 * <p>Company: todo</p >
 * <P>Created Date: 2024年04月24日</P>
 *
 * @author LiuYuHan
 * @version 1.0
 */
public class StringUtils {
    // 验证是否为空
    public static final boolean isEmpty(String str){
        return (null==str) || str.trim().isEmpty();
    }

    // 验证非空
    public static final boolean isNotEmpty(String str){
        return !StringUtils.isEmpty(str);
    }
}