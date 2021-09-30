package com.sundaohan.server.service.impl;

import com.sundaohan.server.pojo.Admin;
import com.sundaohan.server.pojo.Menu;
import com.sundaohan.server.mapper.MenuMapper;
import com.sundaohan.server.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sundaohan.server.utils.AdminUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sundaohan
 * @since 2021-07-21
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @Title getMenusByAdminId
     * @Description 根据用户id获取用户菜单列表
     * @Author sundaohan
     * @Params []
     * @return java.util.List<com.sundaohan.server.pojo.Menu>
     */
    @Override
    public List<Menu> getMenusByAdminId() {
        Integer adminId = AdminUtil.getCurrentAdmin().getId();
        System.out.println("adminId = " + adminId);
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        //从redis获取菜单数据
        List<Menu> menus=  (List<Menu>) valueOperations.get("menu_" + adminId);
        //如果为空去数据库获取
        if(CollectionUtils.isEmpty(menus)){
           menus = menuMapper.getMenusByAdminId(adminId);
           valueOperations.set("menu_" + adminId,menus);
        }
        return menus;
    }
    /**
     * @Title getMenusWithRole
     * @Description 根据角色获取菜单列表
     * @Author sundaohan
     * @Params []
     * @return java.util.List<com.sundaohan.server.pojo.Menu>
     */
    @Override
    public List<Menu> getMenusWithRole() {
        return menuMapper.getMenusWithRole();
    }

    @Override
    /**
     * @Title getAllMenus
     * @Description 查询所有菜单
     * @Author sundaohan
     * @Params []
     * @return java.util.List<com.sundaohan.server.pojo.Menu>
     */
    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }
}
