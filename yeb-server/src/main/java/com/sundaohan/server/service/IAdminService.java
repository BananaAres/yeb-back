package com.sundaohan.server.service;

import com.sundaohan.server.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sundaohan.server.pojo.Menu;
import com.sundaohan.server.pojo.RespBean;
import com.sundaohan.server.pojo.Role;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sundaohan
 * @since 2021-07-21
 */
public interface IAdminService extends IService<Admin> {
    /**
     * @Title login
     * @Description 登陆之后返回token
     * @Author sundaohan
     * @Params [username, password, request]
     * @return com.sundaohan.server.pojo.RespBean
     */
    RespBean login(String username, String password, String code, HttpServletRequest request);
    /**
     * @Title getAdminByUserName
     * @Description 根据用户名获取用户信息
     * @Author sundaohan
     * @Params [username]
     * @return com.sundaohan.server.pojo.Admin
     */
    Admin getAdminByUserName(String username);
    /**
     * @Title getRoles
     * @Description 根据用户名获取角色列表
     * @Author sundaohan
     * @Params [adminId]
     * @return java.util.List<com.sundaohan.server.pojo.Role>
     */
    List<Role> getRoles(Integer adminId);
    /**
     * @Title getaAllAdmins
     * @Description 获取所有操作员
     * @Author sundaohan
     * @Params [keywords]
     * @return java.util.List<com.sundaohan.server.pojo.Admin>
     */
    List<Admin> getAllAdmins(String keywords);
    /**
     * @Title updateAdminRole
     * @Description 更新操作员角色
     * @Author sundaohan
     * @Params [adminId, rids]
     * @return com.sundaohan.server.pojo.RespBean
     */
    RespBean updateAdminRole(Integer adminId, Integer[] rids);

    /**
     * @Title updateAdminPassword
     * @Description 更新用户密码
     * @Author sundaohan
     * @Params [oldPass, pass, adminId]
     * @return com.sundaohan.server.pojo.RespBean
     */
    RespBean updateAdminPassword(String oldPass, String pass, Integer adminId);

    /**
     * @Title updateAdminUserFace
     * @Description 更新用户头像
     * @Author sundaohan
     * @Params [url, id, authentication]
     * @return com.sundaohan.server.pojo.RespBean
     */
    RespBean updateAdminUserFace(String url, Integer id, Authentication authentication);
}
