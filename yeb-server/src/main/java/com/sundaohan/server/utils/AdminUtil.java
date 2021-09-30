package com.sundaohan.server.utils;

import com.sundaohan.server.pojo.Admin;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Auther sundaohan
 * @Package com.sundaohan.server.utils
 * @Title AdminUtils
 * @Description TODO
 * @Date 2021/8/1 下午9:35
 */
public class AdminUtil {

    /**
     * @Title getCurrentAdmin
     * @Description 获取当前登录操作员
     * @Author sundaohan
     * @Params []
     * @return com.sundaohan.server.pojo.Admin
     */
    public static Admin getCurrentAdmin(){
        return (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
