package com.sundaohan.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.sundaohan.server.config.security.component.JwtTokenUtils;
import com.sundaohan.server.mapper.AdminRoleMapper;
import com.sundaohan.server.mapper.RoleMapper;
import com.sundaohan.server.pojo.Admin;
import com.sundaohan.server.mapper.AdminMapper;
import com.sundaohan.server.pojo.AdminRole;
import com.sundaohan.server.pojo.RespBean;
import com.sundaohan.server.pojo.Role;
import com.sundaohan.server.service.IAdminRoleService;
import com.sundaohan.server.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sundaohan.server.utils.AdminUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sundaohan
 * @since 2021-07-21
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Value("${jwt.tokenHead}")
    private String tokenHead;
    /**
     * @Title login
     * @Description 登陆之后返回token
     * @Author sundaohan
     * @Params [username, password, request]
     * @return com.sundaohan.server.pojo.RespBean
     */
    @Override
    public RespBean login(String username, String password, String code, HttpServletRequest request) {
        String captcha = (String)request.getSession().getAttribute("captcha");
        System.out.println("captcha = " + captcha);
        System.out.println("code = " + code);
        if(StringUtils.isEmpty(code) || !captcha.equalsIgnoreCase(code)){
            return RespBean.error("验证码输入错误，请重新输入");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        //用户名不存在或者密码不匹配
        if(userDetails == null || !passwordEncoder.matches(password, userDetails.getPassword())){
            return RespBean.error("用户名或密码不正确");
        }
        if(!userDetails.isEnabled()){
            return RespBean.error("账号被禁用，请联系管理员");
        }
        //更新security登录用户对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //生成token
        String token = jwtTokenUtils.generateToken(userDetails);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);
        return RespBean.success("登陆成功",tokenMap);
    }
    /**
     * @Title getAdminByUserName
     * @Description 根据用户名获取用户
     * @Author sundaohan
     * @Params [username]
     * @return com.sundaohan.server.pojo.Admin
     */
    @Override
    public Admin getAdminByUserName(String username) {
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("username",username).eq("enabled",true));
    }
    /**
     * @Title getRoles
     * @Description 根据用户id查询角色列表
     * @Author sundaohan
     * @Params [adminId]
     * @return java.util.List<com.sundaohan.server.pojo.Role>
     */
    public List<Role> getRoles(Integer adminId){
        return roleMapper.getRoles(adminId);
    }

    @Override
    public List<Admin> getAllAdmins(String keywords) {
        //System.out.println(AdminUtil.getCurrentAdmin().getId() + " " + keywords);
        return adminMapper.getAllAdmins(AdminUtil.getCurrentAdmin().getId(),keywords);
    }

    /**
     * @Title updateAdminRole
     * @Description 更新操作员角色
     * @Author sundaohan
     * @Params [adminId, rids]
     * @return com.sundaohan.server.pojo.RespBean
     */
    @Override
    @Transactional
    public RespBean updateAdminRole(Integer adminId, Integer[] rids){
        adminRoleMapper.delete(new QueryWrapper<AdminRole>().eq("adminId",adminId));
        Integer result = adminRoleMapper.updateAdminRole(adminId,rids);
        if(rids.length == result){
            return RespBean.success("更新成功!");
        }
        return RespBean.error("更新失败！");
    }
    /**
     * @Title updateAdminPassword
     * @Description 更新用户密码
     * @Author sundaohan
     * @Params [oldPass, pass, adminId]
     * @return com.sundaohan.server.pojo.RespBean
     */
    @Override
    public RespBean updateAdminPassword(String oldPass, String pass, Integer adminId) {
        //找到amdin,比较旧密码和数据库中密码是否相同，相同才允许更改
        Admin admin = adminMapper.selectById(adminId);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //判断旧密码是否正确
        if(encoder.matches(oldPass,admin.getPassword())){
            admin.setPassword(encoder.encode(pass));
            int result = adminMapper.updateById(admin);
            if(result == 1){
                return RespBean.success("更新成功！");
            }
        }
        return RespBean.error("更新失败！");
    }

    /**
     * @Title updateAdminUserFace
     * @Description 更新用户头像
     * @Author sundaohan
     * @Params [url, id, authentication]
     * @return com.sundaohan.server.pojo.RespBean
     */
    @Override
    public RespBean updateAdminUserFace(String url, Integer id, Authentication authentication) {
        Admin admin = adminMapper.selectById(id);
        admin.setUserFace(url);
        int result= adminMapper.updateById(admin);
        if(result == 1){
            Admin principal = (Admin) authentication.getPrincipal();
            principal.setUserFace(url);
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(admin,null,authentication.getAuthorities()));
            return RespBean.success("更新成功！",url);
        }
        return RespBean.error("更新失败！");
    }

}
